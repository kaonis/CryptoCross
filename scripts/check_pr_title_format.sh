#!/usr/bin/env bash
set -euo pipefail

usage() {
  cat <<'EOF'
Usage:
  scripts/check_pr_title_format.sh <path-to-title.txt>
  scripts/check_pr_title_format.sh - < pr_title.txt
  gh pr view <number> --json title --jq .title | scripts/check_pr_title_format.sh

Checks PR title format:
  <type>: <summary> (#<issue-number>)

Examples:
  docs: create roadmap v18 (#160)
  fix: validate dictionary file against active board size (#146)
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
  echo "Provide a PR title file path or pipe PR title content on stdin." >&2
  usage >&2
  exit 1
fi

if [[ "$#" -eq 1 && "$1" != "-" ]]; then
  if [[ ! -f "$1" ]]; then
    echo "PR title file not found: $1" >&2
    exit 1
  fi
  TITLE_CONTENT="$(cat "$1")"
else
  TITLE_CONTENT="$(cat)"
fi

TITLE_CONTENT="${TITLE_CONTENT//$'\r'/}"

NON_EMPTY_LINE_COUNT="$(
  printf '%s\n' "$TITLE_CONTENT" \
    | sed '/^[[:space:]]*$/d' \
    | wc -l \
    | tr -d ' '
)"

if [[ "$NON_EMPTY_LINE_COUNT" -eq 0 ]]; then
  echo "PR title content is empty." >&2
  exit 1
fi

if [[ "$NON_EMPTY_LINE_COUNT" -gt 1 ]]; then
  echo "Expected exactly one non-empty PR title line, found $NON_EMPTY_LINE_COUNT." >&2
  exit 1
fi

PR_TITLE="$(
  printf '%s\n' "$TITLE_CONTENT" \
    | sed '/^[[:space:]]*$/d' \
    | head -n 1
)"

TITLE_PATTERN='^[a-z][a-z0-9-]*: .+ \(#[0-9]+\)$'
if [[ ! "$PR_TITLE" =~ $TITLE_PATTERN ]]; then
  echo "Invalid PR title format: $PR_TITLE" >&2
  echo "Expected format: <type>: <summary> (#<issue-number>)" >&2
  exit 1
fi

echo "PR title lint passed."
