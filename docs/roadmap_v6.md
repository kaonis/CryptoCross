# CryptoCross Roadmap v6

## Date
- 2026-02-15

## v5 Completion Summary
- `#56` completed: added deterministic board fixture support for tests in `Board` with focused fixture validation tests.
- `#57` completed: localized gameplay status strings via `CryptoCrossMessages.properties` and added resource-bundle coverage.
- `#58` completed: added `docs/manual_smoke_checklist.md` for release-time gameplay sanity checks.
- Validation baseline remains green locally (`ant clean run-junit5-tests`, `ant clean jar`).

## v6 Priorities
- [ ] #63 Add integration tests for help-action and submission interplay.
- [ ] #64 Add changelog entry template for merged roadmap issues.
- [ ] #66 Add strict selection mode toggle for non-terminal deselection.

## v6 Success Criteria
- Help-action + submission sequences are covered by deterministic, focused tests.
- A lightweight changelog process exists and is easy to apply per merged issue.
- Strict selection mode is optional and does not change default behavior unless enabled.
- Each item follows repo workflow:
  - one issue -> one branch -> one PR
  - full local validations before merge
  - final review before merge to `master`

## Next Candidate Ideas (Post-v6)
- Add a small headless gameplay scenario harness for repeatable UI-free behavior checks.
- Add a compact troubleshooting guide for common dictionary-format failures.
- Add a release checklist that references both automated tests and manual smoke steps.
