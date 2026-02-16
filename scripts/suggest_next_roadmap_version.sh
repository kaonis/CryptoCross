#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
INDEX_FILE="$ROOT_DIR/docs/roadmap_index.md"

if [[ ! -f "$INDEX_FILE" ]]; then
  echo "roadmap index not found: $INDEX_FILE" >&2
  exit 1
fi

LATEST_VERSION="$(awk -F'|' '
/^\| v[0-9]+ / {
  version = $2
  gsub(/ /, "", version)
  sub(/^v/, "", version)
  print version
}
' "$INDEX_FILE" | sort -n | tail -n 1)"

if [[ -z "$LATEST_VERSION" ]]; then
  echo "Could not determine latest roadmap version from $INDEX_FILE" >&2
  exit 1
fi

if [[ ! "$LATEST_VERSION" =~ ^[0-9]+$ ]]; then
  echo "Invalid roadmap version parsed from $INDEX_FILE: $LATEST_VERSION" >&2
  exit 1
fi

NEXT_VERSION=$((LATEST_VERSION + 1))
echo "v$NEXT_VERSION"
