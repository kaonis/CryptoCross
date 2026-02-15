# Roadmap Changelog

Chronological record of merged roadmap issues.
Add new entries at the top using `docs/roadmap_changelog_template.md`.

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
