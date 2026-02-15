#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
CHANGELOG_FILE="$ROOT_DIR/docs/roadmap_changelog.md"
LIMIT="${1:-5}"

if [[ ! -f "$CHANGELOG_FILE" ]]; then
  echo "roadmap changelog not found: $CHANGELOG_FILE" >&2
  exit 1
fi

if ! [[ "$LIMIT" =~ ^[0-9]+$ ]] || [[ "$LIMIT" -le 0 ]]; then
  echo "usage: $(basename "$0") [positive-number-of-entries]" >&2
  exit 1
fi

awk -v max_entries="$LIMIT" '
BEGIN {
  entries = 0
  in_entry = 0
  in_summary = 0
  printed_header = 0
}

function print_header_once() {
  if (!printed_header) {
    print "# Release Notes"
    print ""
    print "Source: docs/roadmap_changelog.md"
    print ""
    printed_header = 1
  }
}

/^## / {
  if (entries >= max_entries) {
    exit
  }

  entries++
  in_entry = 1
  in_summary = 0

  print_header_once()
  if (entries > 1) {
    print ""
  }

  title = substr($0, 4)
  print "## " title
  next
}

in_entry && /^- PR:/ {
  print $0
  next
}

in_entry && /^- Summary:/ {
  in_summary = 1
  print "- Highlights:"
  next
}

in_summary && /^  - / {
  sub(/^  - /, "- ")
  print $0
  next
}

in_summary && /^- / {
  in_summary = 0
}

END {
  if (!printed_header) {
    print "# Release Notes"
    print ""
    print "No roadmap changelog entries found."
  }
}
' "$CHANGELOG_FILE"
