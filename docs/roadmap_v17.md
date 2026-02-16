# CryptoCross Roadmap v17

## Date
- 2026-02-16

## v16 Completion Summary
- `#144` completed: added operations-index markdown reference checker script and README usage.
- `#145` completed: added reusable manual smoke evidence template and operations-index link.
- `#146` completed: fixed dictionary file validation to use active board size with focused test coverage.
- Validation baseline remains green locally (`ant clean run-junit5-tests`, `ant clean jar`).

## v17 Priorities
- [ ] #152 Add one-command local pre-merge guard checks script.
- [ ] #153 Document issue/branch/PR naming conventions in runbook.
- [ ] #154 Add integration coverage for dictionary switch on non-default board size.

## v17 Success Criteria
- A single script runs current docs/workflow guard checks required before merge.
- Workflow runbook clearly documents branch naming and PR title conventions used in this repo.
- Integration coverage exists for dictionary-switch behavior with a non-default board size.
- Each item follows repo workflow:
  - one issue -> one branch -> one PR
  - full local validations before merge
  - final review before merge to `master`

## Next Candidate Ideas (Post-v17)
- Add a script to check PR title format against issue-based naming conventions.
- Add focused integration coverage for dictionary path persistence behavior across app restart.
- Add a compact checklist for deciding when to trigger manual GitHub workflows.
