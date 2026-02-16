#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
WORKFLOW_DIR="$ROOT_DIR/.github/workflows"

if [[ ! -d "$WORKFLOW_DIR" ]]; then
  echo "workflow directory not found: $WORKFLOW_DIR" >&2
  exit 1
fi

shopt -s nullglob
workflow_files=( "$WORKFLOW_DIR"/*.yml "$WORKFLOW_DIR"/*.yaml )
shopt -u nullglob

if [[ "${#workflow_files[@]}" -eq 0 ]]; then
  echo "No workflow files found in $WORKFLOW_DIR" >&2
  exit 1
fi

parse_events() {
  local file="$1"
  local in_on=0
  local on_indent=-1
  local first_indent=-1

  while IFS= read -r raw_line || [[ -n "$raw_line" ]]; do
    line="${raw_line%%#*}"

    if [[ "$in_on" -eq 0 ]]; then
      if [[ "$line" =~ ^([[:space:]]*)on[[:space:]]*:[[:space:]]*(.*)$ ]]; then
        on_indent=${#BASH_REMATCH[1]}
        rhs="${BASH_REMATCH[2]}"
        rhs="${rhs#"${rhs%%[![:space:]]*}"}"
        rhs="${rhs%"${rhs##*[![:space:]]}"}"

        if [[ -z "$rhs" ]]; then
          in_on=1
          first_indent=-1
          continue
        fi

        if [[ "$rhs" =~ ^\[(.*)\]$ ]]; then
          IFS=',' read -r -a inline_events <<< "${BASH_REMATCH[1]}"
          for event in "${inline_events[@]}"; do
            event="${event#"${event%%[![:space:]]*}"}"
            event="${event%"${event##*[![:space:]]}"}"
            event="${event%\"}"
            event="${event#\"}"
            [[ -n "$event" ]] && printf '%s\n' "$event"
          done
        else
          rhs="${rhs%\"}"
          rhs="${rhs#\"}"
          [[ -n "$rhs" ]] && printf '%s\n' "$rhs"
        fi
      fi
      continue
    fi

    if [[ "$line" =~ ^[[:space:]]*$ ]]; then
      continue
    fi

    stripped="${line#"${line%%[![:space:]]*}"}"
    indent=$(( ${#line} - ${#stripped} ))
    if (( indent <= on_indent )); then
      in_on=0
      continue
    fi

    if [[ "$line" =~ ^[[:space:]]*([^[:space:]:]+)[[:space:]]*: ]]; then
      key="${BASH_REMATCH[1]}"
      if (( first_indent < 0 )); then
        first_indent=$indent
      fi
      if (( indent == first_indent )); then
        key="${key%\"}"
        key="${key#\"}"
        [[ -n "$key" ]] && printf '%s\n' "$key"
      fi
    fi
  done < "$file"
}

errors=0

for file in "${workflow_files[@]}"; do
  events=()
  while IFS= read -r event; do
    [[ -z "$event" ]] && continue
    already_seen=0
    for existing_event in "${events[@]:-}"; do
      if [[ "$existing_event" == "$event" ]]; then
        already_seen=1
        break
      fi
    done
    if [[ "$already_seen" -eq 0 ]]; then
      events+=( "$event" )
    fi
  done < <(parse_events "$file")

  if [[ "${#events[@]}" -eq 0 ]]; then
    echo "No workflow events found in $file" >&2
    errors=1
    continue
  fi

  has_manual=0
  disallowed_events=()
  for event in "${events[@]}"; do
    if [[ "$event" == "workflow_dispatch" ]]; then
      has_manual=1
    else
      disallowed_events+=( "$event" )
    fi
  done

  if [[ "$has_manual" -ne 1 ]]; then
    echo "Missing workflow_dispatch trigger in $file" >&2
    errors=1
  fi

  if [[ "${#disallowed_events[@]}" -gt 0 ]]; then
    echo "Found non-manual triggers in $file:" >&2
    printf '  - %s\n' "${disallowed_events[@]}" >&2
    errors=1
  fi
done

if [[ "$errors" -ne 0 ]]; then
  exit 1
fi

echo "All workflow files are manual-only (workflow_dispatch)."
