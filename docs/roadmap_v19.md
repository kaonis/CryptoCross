# CryptoCross Roadmap v19

## Date
- 2026-02-16

## v18 Completion Summary
- `#159` completed: added PR title format lint script and README usage.
- `#161` completed: added manual workflow trigger decision checklist and operations-index link.
- `#162` completed: added dictionary path persistence boundary integration coverage.
- Validation baseline remains green locally (`ant clean run-junit5-tests`, `ant clean jar`).

## v19 Priorities
- [ ] #168 Add active-roadmap issue status guard script.
- [ ] #169 Add branch naming convention checker script.
- [ ] #170 Add integration coverage for invalid dictionary switch handling.

## v19 Success Criteria
- A guard script validates active roadmap issue references and reports issue states reliably.
- A local branch naming checker validates issue-linked branch conventions before PR creation.
- Integration coverage exists for invalid dictionary switch handling boundaries without UI refactors.
- Each item follows repo workflow:
  - one issue -> one branch -> one PR
  - full local validations before merge
  - final review before merge to `master`

## Next Candidate Ideas (Post-v19)
- Add a local guard to verify PR body and title checks in one command.
- Add focused integration coverage for workflow runbook decision checklist references.
- Add a script to summarize active-roadmap completion progress by issue state.
