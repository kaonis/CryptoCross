#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
INDEX_FILE="$ROOT_DIR/docs/operations_index.md"

if [[ ! -f "$INDEX_FILE" ]]; then
  echo "operations index not found: $INDEX_FILE" >&2
  exit 1
fi

referenced_docs=()
while IFS= read -r doc_path; do
  [[ -z "$doc_path" ]] && continue
  referenced_docs+=( "$doc_path" )
done < <(grep -oE 'docs/[A-Za-z0-9._/-]+\.md' "$INDEX_FILE" | sort -u)

if [[ "${#referenced_docs[@]}" -eq 0 ]]; then
  echo "No docs/*.md references found in $INDEX_FILE" >&2
  exit 1
fi

errors=0

for doc_path in "${referenced_docs[@]}"; do
  if [[ ! -f "$ROOT_DIR/$doc_path" ]]; then
    echo "Missing referenced doc: $doc_path" >&2
    errors=1
  fi
done

if [[ "$errors" -ne 0 ]]; then
  exit 1
fi

echo "Operations index references are valid."
echo "Docs checked: $(printf '%s\n' "${referenced_docs[@]}" | tr '\n' ' ' | sed 's/ $//')"
