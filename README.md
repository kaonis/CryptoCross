# cryptocross

[![Build and Test](https://github.com/kaonis/CryptoCross/actions/workflows/build-test.yml/badge.svg)](https://github.com/kaonis/CryptoCross/actions/workflows/build-test.yml)

A Java implementation of a word game where a user forms words from letters on a board and is scored based on the words sum of the individual letters values

## Requirements
- Java 17 or higher (OpenJDK recommended)

## Building and Running
```bash
cd CryptoCross
ant clean jar
java -jar dist/CryptoCross.jar
```

## Testing
```bash
cd CryptoCross
# Cross-platform way using Ant
ant run-junit5-tests

# Or manually (use ; instead of : on Windows)
ant compile-test
java -jar lib/junit-platform-console-standalone-1.10.1.jar --class-path build/classes:build/test/classes --scan-class-path
```

Strict-mode focused checks can be run from the repository root with:
```bash
./scripts/run_strict_mode_checks.sh
```

Daily roadmap merge summaries (defaults to today) can be generated with:
```bash
./scripts/summarize_daily_merges.sh [YYYY-MM-DD]
```

Roadmap/index/changelog consistency can be checked with:
```bash
./scripts/check_roadmap_links.sh
```

Roadmap changelog entry formatting can be checked with:
```bash
./scripts/check_roadmap_changelog_format.sh
```

Gameplay settings/defaults snapshot:
- `docs/gameplay_settings_snapshot.md`

## Continuous Integration
This project uses GitHub Actions for building and testing with manual triggering only (`workflow_dispatch`) to control Actions usage.
Run the workflow from the [Actions tab](https://github.com/kaonis/CryptoCross/actions) when needed. It performs:
- Compilation with Java 17
- Execution of all unit tests
- JAR artifact generation

Manual-only workflow and Actions budget guidance:
- `docs/manual_workflow_runbook.md`
