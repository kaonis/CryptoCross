#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
DOCS_DIR="$ROOT_DIR/docs"
INDEX_FILE="$DOCS_DIR/roadmap_index.md"
CHANGELOG_FILE="$DOCS_DIR/roadmap_changelog.md"

if [[ ! -f "$INDEX_FILE" ]]; then
  echo "roadmap index not found: $INDEX_FILE" >&2
  exit 1
fi

if [[ ! -f "$CHANGELOG_FILE" ]]; then
  echo "roadmap changelog not found: $CHANGELOG_FILE" >&2
  exit 1
fi

FILES_VERSIONS="$(find "$DOCS_DIR" -maxdepth 1 -type f -name 'roadmap_v*.md' \
  | sed -E 's|.*/roadmap_v([0-9]+)\.md$|\1|' \
  | sort -n)"

if [[ -z "$FILES_VERSIONS" ]]; then
  echo "no roadmap_v*.md files found in $DOCS_DIR" >&2
  exit 1
fi

TABLE_VERSIONS="$(awk -F'|' '
/^\| v[0-9]+ / {
  version = $2
  gsub(/ /, "", version)
  sub(/^v/, "", version)
  print version
}
' "$INDEX_FILE" | sort -n | uniq)"

FILES_SECTION_VERSIONS="$(sed -n '/^## Files/,/^## /p' "$INDEX_FILE" \
  | grep -oE 'roadmap_v[0-9]+\.md' \
  | sed -E 's/roadmap_v([0-9]+)\.md/\1/' \
  | sort -n | uniq || true)"

CHANGELOG_REF_VERSIONS="$(grep -oE 'docs/roadmap_v[0-9]+\.md' "$CHANGELOG_FILE" \
  | sed -E 's|docs/roadmap_v([0-9]+)\.md|\1|' \
  | sort -n | uniq || true)"

errors=0

if ! diff -u <(printf '%s\n' "$FILES_VERSIONS") <(printf '%s\n' "$TABLE_VERSIONS") >/dev/null; then
  echo "Mismatch: roadmap files vs roadmap_index table versions" >&2
  diff -u <(printf '%s\n' "$FILES_VERSIONS") <(printf '%s\n' "$TABLE_VERSIONS") || true
  errors=1
fi

if ! diff -u <(printf '%s\n' "$FILES_VERSIONS") <(printf '%s\n' "$FILES_SECTION_VERSIONS") >/dev/null; then
  echo "Mismatch: roadmap files vs roadmap_index Files section" >&2
  diff -u <(printf '%s\n' "$FILES_VERSIONS") <(printf '%s\n' "$FILES_SECTION_VERSIONS") || true
  errors=1
fi

if [[ -n "$CHANGELOG_REF_VERSIONS" ]]; then
  UNKNOWN_CHANGELOG_VERSIONS="$(comm -13 \
    <(printf '%s\n' "$FILES_VERSIONS") \
    <(printf '%s\n' "$CHANGELOG_REF_VERSIONS"))"
  if [[ -n "$UNKNOWN_CHANGELOG_VERSIONS" ]]; then
    echo "Mismatch: changelog references missing roadmap files:" >&2
    printf '%s\n' "$UNKNOWN_CHANGELOG_VERSIONS" >&2
    errors=1
  fi
fi

ACTIVE_VERSIONS="$(awk -F'|' '
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

ACTIVE_COUNT="$(printf '%s\n' "$ACTIVE_VERSIONS" | sed '/^$/d' | wc -l | tr -d ' ')"
if [[ "$ACTIVE_COUNT" -ne 1 ]]; then
  echo "Expected exactly one Active roadmap in index table, found $ACTIVE_COUNT" >&2
  errors=1
else
  ACTIVE_VERSION="$(printf '%s\n' "$ACTIVE_VERSIONS" | sed '/^$/d')"
  LATEST_VERSION="$(printf '%s\n' "$FILES_VERSIONS" | tail -n 1)"
  if [[ "$ACTIVE_VERSION" != "$LATEST_VERSION" ]]; then
    echo "Active roadmap version ($ACTIVE_VERSION) is not the latest file version ($LATEST_VERSION)" >&2
    errors=1
  fi
fi

if [[ "$errors" -ne 0 ]]; then
  exit 1
fi

echo "Roadmap references look consistent."
echo "Versions checked: $(printf '%s\n' "$FILES_VERSIONS" | tr '\n' ' ' | sed 's/ $//')"
