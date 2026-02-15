#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
APP_DIR="$ROOT_DIR/CryptoCross"
JUNIT_JAR="$APP_DIR/lib/junit-platform-console-standalone-1.10.1.jar"

if [[ ! -f "$JUNIT_JAR" ]]; then
  echo "JUnit console jar not found: $JUNIT_JAR" >&2
  exit 1
fi

cd "$APP_DIR"

ant compile-test
java -jar "$JUNIT_JAR" \
  --class-path build/classes:build/test/classes \
  --select-class cryptocross.StrictSelectionPreferenceServiceTest \
  --select-class cryptocross.StrictSelectionStartupIntegrationTest \
  --select-class cryptocross.StrictSelectionFlowIntegrationTest \
  --select-class cryptocross.StrictSelectionToggleFlowHarnessTest \
  --select-class cryptocross.WordSelectionServiceTest \
  --fail-if-no-tests
