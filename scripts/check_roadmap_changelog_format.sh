#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
CHANGELOG_FILE="$ROOT_DIR/docs/roadmap_changelog.md"

if [[ ! -f "$CHANGELOG_FILE" ]]; then
  echo "roadmap changelog not found: $CHANGELOG_FILE" >&2
  exit 1
fi

awk '
BEGIN {
  entry = 0
  errors = 0
}

function flush_entry() {
  if (!entry) return

  if (!has_pr) {
    print "Missing - PR: line for entry: " title > "/dev/stderr"
    errors = 1
  }
  if (!has_summary) {
    print "Missing - Summary: section for entry: " title > "/dev/stderr"
    errors = 1
  } else if (summary_items < 1) {
    print "Summary section has no bullet items for entry: " title > "/dev/stderr"
    errors = 1
  }
  if (!has_validation) {
    print "Missing - Validation: section for entry: " title > "/dev/stderr"
    errors = 1
  } else if (validation_items < 1) {
    print "Validation section has no bullet items for entry: " title > "/dev/stderr"
    errors = 1
  }
  if (!has_risk) {
    print "Missing - Risk/Notes: line for entry: " title > "/dev/stderr"
    errors = 1
  }
}

/^## / {
  flush_entry()

  if ($0 !~ /^## [0-9]{4}-[0-9]{2}-[0-9]{2} - #[0-9]+ - .+/) {
    print "Invalid entry heading format: " $0 > "/dev/stderr"
    errors = 1
  }

  entry = 1
  title = $0
  has_pr = 0
  has_summary = 0
  has_validation = 0
  has_risk = 0
  summary_items = 0
  validation_items = 0
  in_summary = 0
  in_validation = 0
  next
}

entry && /^- PR:/ {
  has_pr = 1
  in_summary = 0
  in_validation = 0
  next
}

entry && /^- Summary:/ {
  has_summary = 1
  in_summary = 1
  in_validation = 0
  next
}

entry && /^- Validation:/ {
  has_validation = 1
  in_validation = 1
  in_summary = 0
  next
}

entry && /^- Risk\/Notes:/ {
  has_risk = 1
  in_summary = 0
  in_validation = 0
  next
}

entry && /^- / {
  in_summary = 0
  in_validation = 0
  next
}

entry && in_summary && /^  - / {
  summary_items++
  next
}

entry && in_validation && /^  - / {
  validation_items++
  next
}

END {
  flush_entry()
  if (errors) {
    exit 1
  }
  print "Roadmap changelog entry format looks consistent."
}
' "$CHANGELOG_FILE"
