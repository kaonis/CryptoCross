#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
INDEX_FILE="$ROOT_DIR/docs/roadmap_index.md"

if [[ ! -f "$INDEX_FILE" ]]; then
  echo "roadmap index not found: $INDEX_FILE" >&2
  exit 1
fi

if ! command -v gh >/dev/null 2>&1; then
  echo "GitHub CLI (gh) is required for this script." >&2
  exit 1
fi

ACTIVE_VERSION="$(awk -F'|' '
/^\| v[0-9]+ / {
  version = $2
  status = $4
  gsub(/ /, "", version)
  gsub(/ /, "", status)
  sub(/^v/, "", version)
  if (status == "Active") {
    print version
  }
}
' "$INDEX_FILE")"

if [[ -z "$ACTIVE_VERSION" ]]; then
  echo "Could not determine active roadmap version from $INDEX_FILE" >&2
  exit 1
fi

ROADMAP_FILE="$ROOT_DIR/docs/roadmap_v${ACTIVE_VERSION}.md"
if [[ ! -f "$ROADMAP_FILE" ]]; then
  echo "active roadmap file not found: $ROADMAP_FILE" >&2
  exit 1
fi

ISSUE_IDS="$(awk '
/^## v[0-9]+ Priorities/ {in_priorities=1; next}
in_priorities && /^## / {in_priorities=0}
in_priorities {print}
' "$ROADMAP_FILE" \
  | grep -oE '#[0-9]+' \
  | tr -d '#' \
  | awk '!seen[$0]++' || true)"

echo "# Roadmap Open Issue Status"
echo
echo "Active roadmap: v$ACTIVE_VERSION"
echo "Source: docs/roadmap_v${ACTIVE_VERSION}.md"
echo

if [[ -z "$ISSUE_IDS" ]]; then
  echo "No issue IDs found in the priorities section."
  exit 0
fi

echo "| Issue | State | Title | URL |"
echo "|---|---|---|---|"

open_count=0
while IFS= read -r issue_id; do
  if [[ -z "$issue_id" ]]; then
    continue
  fi

  ISSUE_LINE="$(gh issue view "$issue_id" --json number,state,title,url \
    --jq '[("#" + (.number|tostring)), .state, .title, .url] | @tsv')"

  state="$(printf '%s' "$ISSUE_LINE" | awk -F'\t' '{print $2}')"
  if [[ "$state" != "OPEN" ]]; then
    continue
  fi

  issue="$(printf '%s' "$ISSUE_LINE" | awk -F'\t' '{print $1}')"
  title="$(printf '%s' "$ISSUE_LINE" | awk -F'\t' '{print $3}')"
  url="$(printf '%s' "$ISSUE_LINE" | awk -F'\t' '{print $4}')"

  echo "| $issue | $state | $title | $url |"
  open_count=$((open_count + 1))
done <<< "$ISSUE_IDS"

if [[ "$open_count" -eq 0 ]]; then
  echo "| (none) | - | No open roadmap priority issues found | - |"
fi
