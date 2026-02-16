# CryptoCross Roadmap v14

## Date
- 2026-02-16

## v13 Completion Summary
- `#119` completed: added roadmap changelog formatting checker script and README usage.
- `#121` completed: added active-roadmap open issue status helper script and README usage.
- `#122` completed: expanded manual smoke checklist with explicit dictionary-switch verification steps.
- Validation baseline remains green locally (`ant clean run-junit5-tests`, `ant clean jar`).

## v14 Priorities
- [ ] #128 Add helper script to suggest next roadmap version number.
- [ ] #129 Add docs index for operational/runbook documents.
- [ ] #130 Add PR body lint check script for required sections.

## v14 Success Criteria
- A helper script exists to print the next roadmap version based on `docs/roadmap_index.md`.
- Operational/runbook documentation has a dedicated index page linked from `README.md`.
- A PR body lint script exists to validate required workflow sections.
- Each item follows repo workflow:
  - one issue -> one branch -> one PR
  - full local validations before merge
  - final review before merge to `master`

## Next Candidate Ideas (Post-v14)
- Add a script to verify all workflow files are manual-only (`workflow_dispatch`).
- Add a compact release packaging sanity-check checklist document.
- Add a gameplay bug/backlog snapshot document derived from current code/test gaps.
