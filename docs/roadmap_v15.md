# CryptoCross Roadmap v15

## Date
- 2026-02-16

## v14 Completion Summary
- `#128` completed: added helper script to suggest the next roadmap version number.
- `#129` completed: added operations/runbook documentation index and README linkage.
- `#130` completed: added PR body section lint script and README usage examples.
- Validation baseline remains green locally (`ant clean run-junit5-tests`, `ant clean jar`).

## v15 Priorities
- [x] #136 Add script to enforce manual-only workflow triggers.
- [x] #137 Add release packaging sanity-check checklist doc.
- [x] #138 Add gameplay bug/backlog snapshot doc.

## v15 Success Criteria
- A script exists to verify workflow files stay manual-only (`workflow_dispatch`).
- A compact release packaging checklist is documented and easy to follow.
- A gameplay backlog snapshot documents known limitations and next gameplay improvements.
- Each item follows repo workflow:
  - one issue -> one branch -> one PR
  - full local validations before merge
  - final review before merge to `master`

## Next Candidate Ideas (Post-v15)
- Add a one-command script to check all docs listed in `docs/operations_index.md` exist.
- Add a short template for logging manual smoke-test evidence per release.
- Add a focused issue for selected gameplay backlog item implementation (smallest/high-confidence first).
