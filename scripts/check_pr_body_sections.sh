#!/usr/bin/env bash
set -euo pipefail

usage() {
  cat <<'EOF'
Usage:
  scripts/check_pr_body_sections.sh <path-to-pr-body.md>
  scripts/check_pr_body_sections.sh - < pr_body.md
  gh pr view <number> --json body --jq .body | scripts/check_pr_body_sections.sh

Checks that required PR sections exist and are non-empty:
  - ## Summary
  - ## Validation
EOF
}

if [[ "${1:-}" == "-h" || "${1:-}" == "--help" ]]; then
  usage
  exit 0
fi

if [[ "$#" -gt 1 ]]; then
  usage >&2
  exit 1
fi

if [[ "$#" -eq 0 && -t 0 ]]; then
  echo "Provide a PR body file path or pipe PR body content on stdin." >&2
  usage >&2
  exit 1
fi

if [[ "$#" -eq 1 && "$1" != "-" ]]; then
  if [[ ! -f "$1" ]]; then
    echo "PR body file not found: $1" >&2
    exit 1
  fi
  BODY_CONTENT="$(cat "$1")"
else
  BODY_CONTENT="$(cat)"
fi

if [[ -z "${BODY_CONTENT//[[:space:]]/}" ]]; then
  echo "PR body content is empty." >&2
  exit 1
fi

required_sections=("## Summary" "## Validation")
missing_sections=()
empty_sections=()

for section in "${required_sections[@]}"; do
  if ! grep -Fxq "$section" <<< "$BODY_CONTENT"; then
    missing_sections+=("$section")
    continue
  fi

  section_has_content="$(
    awk -v target="$section" '
    BEGIN { in_section=0; has_content=0 }
    $0 == target { in_section=1; next }
    in_section && /^## / { exit }
    in_section && $0 ~ /[^[:space:]]/ { has_content=1; exit }
    END {
      if (has_content) {
        print "yes"
      } else {
        print "no"
      }
    }
    ' <<< "$BODY_CONTENT"
  )"

  if [[ "$section_has_content" != "yes" ]]; then
    empty_sections+=("$section")
  fi
done

if (( ${#missing_sections[@]} > 0 )); then
  echo "Missing required section headings:" >&2
  printf '  - %s\n' "${missing_sections[@]}" >&2
fi

if (( ${#empty_sections[@]} > 0 )); then
  echo "Required sections are present but empty:" >&2
  printf '  - %s\n' "${empty_sections[@]}" >&2
fi

if (( ${#missing_sections[@]} > 0 || ${#empty_sections[@]} > 0 )); then
  exit 1
fi

echo "PR body lint passed."
