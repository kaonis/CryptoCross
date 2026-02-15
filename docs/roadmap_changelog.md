# Roadmap Changelog

Chronological record of merged roadmap issues.
Add new entries at the top using `docs/roadmap_changelog_template.md`.

## 2026-02-15 - #104 - Document current gameplay settings and defaults snapshot
- PR: #110
- Summary:
  - added `docs/gameplay_settings_snapshot.md` with startup defaults, targets, help limits, and strict-mode behavior
  - included strict-mode default/persistence details and related control behavior
  - linked the snapshot doc from `README.md`
- Validation:
  - ant clean run-junit5-tests
  - ant clean jar
- Risk/Notes: snapshot must be updated when gameplay defaults change in code

## 2026-02-15 - #105 - Add script to summarize merges from today's roadmap changelog entries
- PR: #109
- Summary:
  - added `scripts/summarize_daily_merges.sh` for compact daily roadmap merge summaries
  - script defaults to current date and supports optional `YYYY-MM-DD` override
  - documented usage in `README.md`
- Validation:
  - ./scripts/summarize_daily_merges.sh
  - ant clean run-junit5-tests
  - ant clean jar
- Risk/Notes: script relies on current roadmap changelog heading format

## 2026-02-15 - #106 - Add PR template checklist that links related roadmap/issue IDs
- PR: #108
- Summary:
  - added `.github/pull_request_template.md` with issue and roadmap linkage fields
  - added required local validation checklist entries
  - added manual-only workflow reminder for controlled Actions usage
- Validation:
  - ant clean run-junit5-tests
  - ant clean jar
- Risk/Notes: checklist quality depends on PR authors filling template fields accurately

## 2026-02-15 - #103 - Create roadmap_v11 after v10 completion
- PR: #107
- Summary:
  - added `docs/roadmap_v11.md` with v10 completion summary and v11 priorities
  - marked all v10 priorities complete in `docs/roadmap_v10.md`
  - updated `docs/roadmap_index.md` to mark v10 completed and v11 active
- Validation:
  - ant clean run-junit5-tests
  - ant clean jar
- Risk/Notes: roadmap state accuracy depends on continuing to update index/checklists each cycle

## 2026-02-15 - #98 - Add strict-mode persistence smoke-test notes
- PR: #102
- Summary:
  - added `docs/strict_mode_persistence_smoke_notes.md` with concise strict-mode persistence smoke steps
  - documented same-session new-game checks and full app restart checks
  - linked strict-mode smoke notes from `docs/manual_smoke_checklist.md`
- Validation:
  - ant clean run-junit5-tests
  - ant clean jar
- Risk/Notes: smoke notes are manual procedures and depend on tester environment/state cleanup

## 2026-02-15 - #97 - Add one-command script for targeted strict-mode checks
- PR: #101
- Summary:
  - added `scripts/run_strict_mode_checks.sh` to run strict-mode focused tests with one command
  - script compiles tests and runs only strict-mode related JUnit classes
  - documented script usage in `README.md`
- Validation:
  - ./scripts/run_strict_mode_checks.sh
  - ant clean run-junit5-tests
  - ant clean jar
- Risk/Notes: script assumes Unix shell (`bash`) and `:` classpath separator

## 2026-02-15 - #95 - Add docs index for roadmap versions and completion state
- PR: #100
- Summary:
  - added `docs/roadmap_index.md` for quick roadmap version navigation
  - recorded roadmap version/date/status and concise completed-item notes
  - documented update rules for active/completed roadmap status tracking
- Validation:
  - ant clean run-junit5-tests
  - ant clean jar
- Risk/Notes: index requires manual updates when new roadmap versions are created

## 2026-02-15 - #96 - Create roadmap_v10 after v9 completion
- PR: #99
- Summary:
  - added `docs/roadmap_v10.md`
  - documented v9 completion and queued v10 priorities
- Validation:
  - ant clean run-junit5-tests
  - ant clean jar
- Risk/Notes: none

## 2026-02-15 - #90 - Add small release-notes generator from roadmap changelog entries
- PR: #94
- Summary:
  - added `scripts/generate_release_notes.sh` to generate compact release notes from roadmap changelog
  - added `docs/release_notes_generator.md` with usage and examples
- Validation:
  - ./scripts/generate_release_notes.sh 3
  - ant clean run-junit5-tests
  - ant clean jar
- Risk/Notes: generator summarizes changelog entries and currently omits validation/risk sections by design

## 2026-02-15 - #89 - Show strict-mode enabled indicator in gameplay status area
- PR: #93
- Summary:
  - added a dedicated strict-mode indicator label in the gameplay status area
  - indicator is shown only when strict mode is enabled and hidden when disabled
  - localized indicator message and expanded resource-bundle key validation
- Validation:
  - ant compile-test
  - java -jar lib/junit-platform-console-standalone-1.10.1.jar --class-path build/classes:build/test/classes --select-class cryptocross.MenuItemTest --select-class cryptocross.ResourceBundleTest --select-class cryptocross.HtmlMessageIntegrationTest
  - ant clean run-junit5-tests
  - ant clean jar
- Risk/Notes: indicator reflects runtime toggle state only for active game session UI

## 2026-02-15 - #88 - Add strict-mode persistence integration test around startup defaults
- PR: #92
- Summary:
  - added `StrictSelectionStartupIntegrationTest` for startup strict-mode behavior
  - verifies default off behavior and persisted-value restoration across simulated app runs
- Validation:
  - ant compile-test
  - java -jar lib/junit-platform-console-standalone-1.10.1.jar --class-path build/classes:build/test/classes --select-class cryptocross.StrictSelectionStartupIntegrationTest --select-class cryptocross.StrictSelectionPreferenceServiceTest
  - ant clean run-junit5-tests
  - ant clean jar
- Risk/Notes: integration uses preference-service startup simulation rather than full Swing startup

## 2026-02-15 - #87 - Create roadmap_v9 after v8 completion
- PR: #91
- Summary:
  - added `docs/roadmap_v9.md`
  - documented v8 completion and queued v9 priorities
- Validation:
  - ant clean run-junit5-tests
  - ant clean jar
- Risk/Notes: none

## 2026-02-15 - #80 - Add automated UI-flow check for strict mode toggle behavior
- PR: #86
- Summary:
  - added `StrictSelectionToggleFlowHarnessTest` for automated strict-mode toggle flow verification
  - verifies runtime toggle updates non-terminal deselection behavior in an active selection flow
- Validation:
  - ant compile-test
  - java -jar lib/junit-platform-console-standalone-1.10.1.jar --class-path build/classes:build/test/classes --select-class cryptocross.StrictSelectionToggleFlowHarnessTest --select-class cryptocross.StrictSelectionFlowIntegrationTest --select-class cryptocross.WordSelectionServiceTest
  - ant clean run-junit5-tests
  - ant clean jar
- Risk/Notes: harness is service-level and does not drive Swing components directly

## 2026-02-15 - #82 - Persist strict selection mode preference across sessions
- PR: #85
- Summary:
  - added `StrictSelectionPreferenceService` to store strict-mode preference
  - load/apply saved strict-mode state at startup and save on toggle changes
  - added focused tests for default and save/load preference behavior
- Validation:
  - ant compile-test
  - java -jar lib/junit-platform-console-standalone-1.10.1.jar --class-path build/classes:build/test/classes --select-class cryptocross.StrictSelectionPreferenceServiceTest --select-class cryptocross.WordSelectionServiceTest --select-class cryptocross.StrictSelectionFlowIntegrationTest --select-class cryptocross.ResourceBundleTest
  - ant clean run-junit5-tests
  - ant clean jar
- Risk/Notes: preference is user-local and not synced across machines

## 2026-02-15 - #79 - Add cycle status command/doc to show roadmap progression
- PR: #84
- Summary:
  - added `docs/roadmap_cycle_status.md` with repeatable cycle status commands
  - documented a quick checklist for roadmap progression checks before starting the next issue
- Validation:
  - ant clean run-junit5-tests
  - ant clean jar
- Risk/Notes: none

## 2026-02-15 - #81 - Create roadmap_v8 after v7 completion
- PR: #83
- Summary:
  - added `docs/roadmap_v8.md`
  - documented v7 completion and queued v8 priorities
- Validation:
  - ant clean run-junit5-tests
  - ant clean jar
- Risk/Notes: none

## 2026-02-15 - #71 - Add UI-accessible strict selection mode toggle
- PR: #78
- Summary:
  - added gameplay UI checkbox to toggle strict selection mode at runtime
  - connected checkbox state to strict selection logic while keeping default mode disabled
  - disabled strict-mode checkbox with other gameplay controls when game ends/stops
- Validation:
  - ant compile-test
  - java -jar lib/junit-platform-console-standalone-1.10.1.jar --class-path build/classes:build/test/classes --select-class cryptocross.MenuItemTest --select-class cryptocross.ResourceBundleTest --select-class cryptocross.HtmlMessageIntegrationTest --select-class cryptocross.WordSelectionServiceTest
  - ant clean run-junit5-tests
  - ant clean jar
- Risk/Notes: UI wiring is in place; persistence of toggle setting across sessions is not yet implemented

## 2026-02-15 - #74 - Create roadmap_v7 after v6 completion
- PR: #75
- Summary:
  - added `docs/roadmap_v7.md`
  - documented v6 completion and queued v7 priorities
- Validation:
  - ant clean run-junit5-tests
  - ant clean jar
- Risk/Notes: none

## 2026-02-15 - #66 - Add strict selection mode toggle for non-terminal deselection
- PR: #70
- Summary:
  - added optional strict deselection mode support in selection logic
  - wired strict-mode deselection guard into `CryptoCross`
  - added test coverage for strict/non-strict behavior and status key presence
- Validation:
  - ant compile-test
  - java -jar lib/junit-platform-console-standalone-1.10.1.jar --class-path build/classes:build/test/classes --select-class cryptocross.WordSelectionServiceTest --select-class cryptocross.ResourceBundleTest --select-class cryptocross.HtmlMessageIntegrationTest --select-class cryptocross.MenuItemTest
  - ant clean run-junit5-tests
  - ant clean jar
- Risk/Notes: default mode remains non-strict; UI toggle exposure is tracked separately

## 2026-02-15 - #65 - Create roadmap_v6 after v5 completion
- PR: #67
- Summary:
  - added `docs/roadmap_v6.md`
  - documented v5 completion and queued v6 priorities
- Validation:
  - ant clean run-junit5-tests
  - ant clean jar
- Risk/Notes: none

## 2026-02-15 - #64 - Add changelog entry template for merged roadmap issues
- PR: #69
- Summary:
  - added reusable roadmap changelog template and canonical changelog file
  - established process location for cycle merge records
- Validation:
  - ant clean run-junit5-tests
  - ant clean jar
- Risk/Notes: none

## 2026-02-15 - #63 - Add integration tests for help-action and submission interplay
- PR: #68
- Summary:
  - added deterministic integration tests for help mutation + submission flow
  - verified selection reset behavior and duplicate tracking consistency
- Validation:
  - ant compile-test
  - java -jar lib/junit-platform-console-standalone-1.10.1.jar --class-path build/classes:build/test/classes --select-class cryptocross.GameplayFlowIntegrationTest --select-class cryptocross.WordSubmissionServiceTest --select-class cryptocross.HelpActionStateServiceTest
  - ant clean run-junit5-tests
  - ant clean jar
- Risk/Notes: none
