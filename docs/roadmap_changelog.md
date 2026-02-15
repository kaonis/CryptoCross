# Roadmap Changelog

Chronological record of merged roadmap issues.
Add new entries at the top using `docs/roadmap_changelog_template.md`.

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
