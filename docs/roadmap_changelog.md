# Roadmap Changelog

Chronological record of merged roadmap issues.
Add new entries at the top using `docs/roadmap_changelog_template.md`.

## 2026-02-16 - #160 - Create roadmap_v18 after v17 completion
- PR: #163
- Summary:
  - marked all v17 priorities complete in `docs/roadmap_v17.md`
  - added `docs/roadmap_v18.md` with priorities `#159`, `#161`, and `#162`
  - updated `docs/roadmap_index.md` to set v17 completed and v18 active, and added `docs/roadmap_v18.md` to file list
- Validation:
  - ./scripts/check_roadmap_links.sh
  - ant clean run-junit5-tests
  - ant clean jar
- Risk/Notes: roadmap state remains process-sensitive and requires each cycle to keep issue mapping aligned

## 2026-02-16 - #154 - Add integration coverage for dictionary switch on non-default board size
- PR: #158
- Summary:
  - added `CryptoCross/test/cryptocross/DictionarySwitchIntegrationTest.java`
  - covers dictionary-validation path with board size `8` and verifies first submission acceptance on deterministic `8x8` board
  - keeps scope minimal by exercising existing validation helper and submission service without UI refactors
- Validation:
  - ant compile-test && java -jar lib/junit-platform-console-standalone-1.10.1.jar --class-path build/classes:build/test/classes --select-class cryptocross.DictionarySwitchIntegrationTest
  - ant clean run-junit5-tests
  - ant clean jar
- Risk/Notes: integration test exercises non-default board path with controlled fixture, but does not automate Swing UI event flow

## 2026-02-16 - #153 - Add one-command local pre-merge guard checks script
- PR: #157
- Summary:
  - added `scripts/run_pre_merge_guard_checks.sh` to run existing guard checks in one command
  - included roadmap links, changelog format, operations-index references, and manual-only workflow trigger checks
  - documented usage in `README.md`
- Validation:
  - ./scripts/run_pre_merge_guard_checks.sh
  - ant clean run-junit5-tests
  - ant clean jar
- Risk/Notes: script sequence assumes guard scripts remain executable and keep current command interfaces

## 2026-02-16 - #152 - Document issue/branch/PR naming conventions in runbook
- PR: #156
- Summary:
  - added `Issue, Branch, and PR Conventions` guidance to `docs/manual_workflow_runbook.md`
  - documented branch naming (`codex/issue-<id>-...`) and PR title format (`<type>: ... (#<id>)`)
  - aligned `docs/roadmap_v17.md` issue-number text so `#152` maps to conventions and `#153` to pre-merge checks
- Validation:
  - manual runbook review
  - ./scripts/check_roadmap_links.sh
  - ant clean run-junit5-tests
  - ant clean jar
- Risk/Notes: conventions are process guidance and depend on contributor adherence

## 2026-02-16 - #151 - Create roadmap_v17 after v16 completion
- PR: #155
- Summary:
  - marked all v16 priorities complete in `docs/roadmap_v16.md`
  - added `docs/roadmap_v17.md` with priorities `#152`, `#153`, and `#154`
  - updated `docs/roadmap_index.md` to set v16 completed and v17 active, and added `docs/roadmap_v17.md` to file list
- Validation:
  - ./scripts/check_roadmap_links.sh
  - ant clean run-junit5-tests
  - ant clean jar
- Risk/Notes: roadmap correctness depends on keeping status/checklist/changelog transitions synchronized each cycle

## 2026-02-16 - #146 - Validate selected dictionary file against active board size
- PR: #150
- Summary:
  - updated dictionary selection validation in `CryptoCross` to use the active `gameBoard` size instead of hardcoded `5`
  - introduced `validateDictionaryFileForBoardSize(...)` helper for focused validation logic reuse/testing
  - added dictionary-selection tests plus `test-dictionary-6-letter-only.txt` fixture to prove size-dependent behavior
- Validation:
  - ant compile-test && java -jar lib/junit-platform-console-standalone-1.10.1.jar --class-path build/classes:build/test/classes --select-class cryptocross.DictionaryFileSelectionTest
  - ant clean run-junit5-tests
  - ant clean jar
- Risk/Notes: validation still checks dictionary load/generation only and does not alter existing runtime dictionary-switch timing (next game)

## 2026-02-16 - #145 - Add manual smoke evidence template doc
- PR: #149
- Summary:
  - added `docs/manual_smoke_evidence_template.md` with concise build/run/core-checks/issues sections
  - template is reusable for release notes and PR smoke evidence logging
  - linked template from `docs/operations_index.md`
- Validation:
  - manual template review
  - ant clean run-junit5-tests
  - ant clean jar
- Risk/Notes: template quality still depends on consistently filling concrete evidence details

## 2026-02-16 - #144 - Add operations-index link checker script
- PR: #148
- Summary:
  - added `scripts/check_operations_index_links.sh` to validate `docs/*.md` references in `docs/operations_index.md`
  - checker fails if any referenced documentation file is missing
  - documented script usage in `README.md`
- Validation:
  - ./scripts/check_operations_index_links.sh
  - ant clean run-junit5-tests
  - ant clean jar
- Risk/Notes: checker currently validates markdown file existence only (not section anchors or external URLs)

## 2026-02-16 - #143 - Create roadmap_v16 after v15 completion
- PR: #147
- Summary:
  - marked all v15 priorities completed in `docs/roadmap_v15.md`
  - added `docs/roadmap_v16.md` with priorities `#144`, `#145`, and `#146`
  - updated `docs/roadmap_index.md` with v15 completed/v16 active status and added `docs/roadmap_v16.md` to file list
- Validation:
  - ./scripts/check_roadmap_links.sh
  - ant clean run-junit5-tests
  - ant clean jar
- Risk/Notes: roadmap state remains process-dependent and requires each cycle to keep priorities/index/changelog aligned

## 2026-02-16 - #138 - Add gameplay bug/backlog snapshot doc
- PR: #142
- Summary:
  - added `docs/gameplay_backlog_snapshot.md` with evidence-backed gameplay gaps and deferred improvements
  - captured follow-up candidates for dictionary behavior, validation, localization consistency, and UI smoke
  - linked snapshot from `docs/operations_index.md`
- Validation:
  - manual doc review
  - ant clean run-junit5-tests
  - ant clean jar
- Risk/Notes: snapshot is point-in-time and should be updated when gameplay behavior changes

## 2026-02-16 - #137 - Add release packaging sanity-check checklist doc
- PR: #141
- Summary:
  - added `docs/release_packaging_checklist.md` with compact pre-release packaging checks
  - checklist covers build/test, artifact presence, launch check, and quick functional smoke
  - linked checklist from `docs/operations_index.md`
- Validation:
  - manual checklist/doc review
  - ant clean run-junit5-tests
  - ant clean jar
- Risk/Notes: checklist is guidance-only and depends on manual execution discipline

## 2026-02-16 - #136 - Add script to enforce manual-only workflow triggers
- PR: #140
- Summary:
  - added `scripts/check_manual_workflow_triggers.sh` to verify workflow files remain manual-only
  - checker validates presence of `workflow_dispatch` and rejects non-manual triggers
  - documented script usage in `README.md`
- Validation:
  - ./scripts/check_manual_workflow_triggers.sh
  - ant clean run-junit5-tests
  - ant clean jar
- Risk/Notes: parser targets current workflow formatting conventions (`on:` block and standard key layout)

## 2026-02-16 - #135 - Create roadmap_v15 after v14 completion
- PR: #139
- Summary:
  - added `docs/roadmap_v15.md` with v14 completion summary and v15 priorities
  - marked all v14 priorities complete in `docs/roadmap_v14.md`
  - updated `docs/roadmap_index.md` to mark v14 completed and v15 active
- Validation:
  - ./scripts/check_roadmap_links.sh
  - ant clean run-junit5-tests
  - ant clean jar
- Risk/Notes: roadmap state accuracy depends on keeping index/checklist transitions synchronized each cycle

## 2026-02-16 - #130 - Add PR body lint check script for required sections
- PR: #134
- Summary:
  - added `scripts/check_pr_body_sections.sh` to validate required PR body sections
  - checker supports both file input and stdin input for CLI/`gh` workflows
  - documented usage in `README.md`, including direct `gh pr view ... |` piping
- Validation:
  - ./scripts/check_pr_body_sections.sh /tmp/pr_body_valid.md
  - cat /tmp/pr_body_valid.md | ./scripts/check_pr_body_sections.sh
  - ./scripts/check_pr_body_sections.sh /tmp/pr_body_invalid.md (expected failure)
  - ant clean run-junit5-tests
  - ant clean jar
- Risk/Notes: checker enforces exact heading format (`## Summary`, `## Validation`) and will fail alternative heading styles

## 2026-02-16 - #129 - Add docs index for operational/runbook documents
- PR: #133
- Summary:
  - added `docs/operations_index.md` as a central index for operational and runbook docs
  - grouped links by workflow/delivery, manual validation, and release/reporting categories
  - linked the operations index from `README.md`
- Validation:
  - manual link review (`docs/operations_index.md` references)
  - ant clean run-junit5-tests
  - ant clean jar
- Risk/Notes: operations index requires maintenance as docs are added or renamed

## 2026-02-16 - #128 - Add helper script to suggest next roadmap version number
- PR: #132
- Summary:
  - added `scripts/suggest_next_roadmap_version.sh` to print the next roadmap version from index data
  - script validates missing/invalid roadmap index parsing scenarios
  - documented usage in `README.md`
- Validation:
  - ./scripts/suggest_next_roadmap_version.sh
  - ant clean run-junit5-tests
  - ant clean jar
- Risk/Notes: parser assumes roadmap index table retains `| vN |` version formatting

## 2026-02-16 - #127 - Create roadmap_v14 after v13 completion
- PR: #131
- Summary:
  - added `docs/roadmap_v14.md` with v13 completion summary and v14 priorities
  - marked all v13 priorities complete in `docs/roadmap_v13.md`
  - updated `docs/roadmap_index.md` to mark v13 completed and v14 active
- Validation:
  - ./scripts/check_roadmap_links.sh
  - ant clean run-junit5-tests
  - ant clean jar
- Risk/Notes: roadmap state accuracy depends on keeping roadmap index and checklists synchronized each cycle

## 2026-02-16 - #122 - Add dictionary-switch smoke steps to manual checklist
- PR: #126
- Summary:
  - updated `docs/manual_smoke_checklist.md` with dictionary-switch focused manual smoke steps
  - documented expected dictionary label/status updates and post-switch submission check
- Validation:
  - ant clean run-junit5-tests
  - ant clean jar
- Risk/Notes: smoke checklist remains manual and relies on tester discipline

## 2026-02-16 - #121 - Add command/script to print open roadmap issues with status
- PR: #125
- Summary:
  - added `scripts/roadmap_open_issue_status.sh` to print open issue status for active-roadmap priorities
  - script derives active roadmap version from `docs/roadmap_index.md`
  - documented usage in `README.md`
- Validation:
  - ./scripts/roadmap_open_issue_status.sh
  - ant clean run-junit5-tests
  - ant clean jar
- Risk/Notes: script requires authenticated `gh` access to read issue status

## 2026-02-16 - #119 - Add changelog formatting checker for roadmap entries
- PR: #124
- Summary:
  - added `scripts/check_roadmap_changelog_format.sh` to validate changelog entry structure
  - validates required sections (`PR`, `Summary`, `Validation`, `Risk/Notes`) and heading format
  - documented usage in `README.md`
- Validation:
  - ./scripts/check_roadmap_changelog_format.sh
  - ant clean run-junit5-tests
  - ant clean jar
- Risk/Notes: checker relies on current markdown heading and section naming conventions

## 2026-02-16 - #120 - Create roadmap_v13 after v12 completion
- PR: #123
- Summary:
  - added `docs/roadmap_v13.md` with v12 completion summary and v13 priorities
  - marked all v12 priorities complete in `docs/roadmap_v12.md`
  - updated `docs/roadmap_index.md` to mark v12 completed and v13 active
- Validation:
  - ant clean run-junit5-tests
  - ant clean jar
- Risk/Notes: roadmap state accuracy depends on continuing to update index/checklists each cycle

## 2026-02-15 - #114 - Add reusable PR snippet for concise smoke-test evidence
- PR: #118
- Summary:
  - added concise smoke-test evidence section to `.github/pull_request_template.md`
  - added reusable snippet file `.github/pull_request_smoke_evidence_snippet.md`
- Validation:
  - ant clean run-junit5-tests
  - ant clean jar
- Risk/Notes: snippet usefulness depends on PR authors filling the section with concrete evidence

## 2026-02-15 - #112 - Document manual-only workflow runbook and Actions budget controls
- PR: #117
- Summary:
  - added `docs/manual_workflow_runbook.md` with manual workflow execution and budget control guidance
  - documented local validation baseline before manual workflow runs
  - linked runbook from `README.md`
- Validation:
  - ant clean run-junit5-tests
  - ant clean jar
- Risk/Notes: runbook is guidance-only and depends on team discipline to follow consistently

## 2026-02-15 - #111 - Add roadmap-doc link checker script
- PR: #116
- Summary:
  - added `scripts/check_roadmap_links.sh` to validate roadmap/index/changelog consistency
  - verifies version alignment across roadmap files, roadmap index table, and roadmap index file list
  - verifies changelog roadmap references point to existing roadmap files
- Validation:
  - ./scripts/check_roadmap_links.sh
  - ant clean run-junit5-tests
  - ant clean jar
- Risk/Notes: checker logic depends on current markdown structure conventions in roadmap docs

## 2026-02-15 - #113 - Create roadmap_v12 after v11 completion
- PR: #115
- Summary:
  - added `docs/roadmap_v12.md` with v11 completion summary and v12 priorities
  - marked all v11 priorities complete in `docs/roadmap_v11.md`
  - updated `docs/roadmap_index.md` to mark v11 completed and v12 active
- Validation:
  - ant clean run-junit5-tests
  - ant clean jar
- Risk/Notes: roadmap state accuracy depends on continuing to update index/checklists each cycle

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
