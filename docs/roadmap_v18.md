# CryptoCross Roadmap v18

## Date
- 2026-02-16

## v17 Completion Summary
- `#152` completed: documented issue/branch/PR naming conventions in the manual workflow runbook.
- `#153` completed: added one-command local pre-merge guard checks script and README usage.
- `#154` completed: added integration coverage for dictionary switch behavior on non-default board size.
- Validation baseline remains green locally (`ant clean run-junit5-tests`, `ant clean jar`).

## v18 Priorities
- [x] #159 Add PR title format lint script.
- [x] #161 Add manual workflow trigger decision checklist.
- [x] #162 Add integration coverage for dictionary path persistence boundary.

## v18 Success Criteria
- A PR title lint script validates issue-linked title format and is documented for local usage.
- A short workflow-trigger decision checklist exists under `docs/` and is indexed.
- Integration coverage documents/locks current dictionary path persistence boundaries without UI refactors.
- Each item follows repo workflow:
  - one issue -> one branch -> one PR
  - full local validations before merge
  - final review before merge to `master`

## Next Candidate Ideas (Post-v18)
- Add lightweight lint to ensure roadmap issue IDs in active roadmap are open/closed as expected.
- Add a script to verify branch names match issue IDs before PR creation.
- Add targeted coverage for dictionary-switch failure messaging with invalid file content.
