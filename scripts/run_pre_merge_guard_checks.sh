#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"

checks=(
  "$ROOT_DIR/scripts/check_roadmap_links.sh"
  "$ROOT_DIR/scripts/check_roadmap_changelog_format.sh"
  "$ROOT_DIR/scripts/check_operations_index_links.sh"
  "$ROOT_DIR/scripts/check_manual_workflow_triggers.sh"
)

echo "Running pre-merge guard checks..."
for check_script in "${checks[@]}"; do
  echo "- $(basename "$check_script")"
  "$check_script"
done

echo "All pre-merge guard checks passed."
