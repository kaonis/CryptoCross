#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
CHANGELOG_FILE="$ROOT_DIR/docs/roadmap_changelog.md"
TARGET_DATE="${1:-$(date +%F)}"

if [[ ! -f "$CHANGELOG_FILE" ]]; then
  echo "roadmap changelog not found: $CHANGELOG_FILE" >&2
  exit 1
fi

if ! [[ "$TARGET_DATE" =~ ^[0-9]{4}-[0-9]{2}-[0-9]{2}$ ]]; then
  echo "usage: $(basename "$0") [YYYY-MM-DD]" >&2
  exit 1
fi

echo "# Daily Merge Summary"
echo
echo "Date: $TARGET_DATE"
echo

awk -v target_date="$TARGET_DATE" '
BEGIN {
  in_entry = 0
  found = 0
  issue = ""
  title = ""
}

$1 == "##" {
  in_entry = 0
  if ($2 == target_date && $3 == "-" && $4 ~ /^#[0-9]+$/ && $5 == "-") {
    in_entry = 1
    issue = $4
    title = substr($0, index($0, $6))
  }
  next
}

in_entry && $1 == "-" && $2 == "PR:" {
  pr = $3
  print "- " issue " - " title " (" pr ")"
  found = 1
  in_entry = 0
}

END {
  if (!found) {
    print "No roadmap changelog entries found for " target_date "."
  }
}
' "$CHANGELOG_FILE"
