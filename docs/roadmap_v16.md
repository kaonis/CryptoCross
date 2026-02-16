# CryptoCross Roadmap v16

## Date
- 2026-02-16

## v15 Completion Summary
- `#136` completed: added script to enforce manual-only GitHub workflow triggers.
- `#137` completed: added release packaging sanity-check checklist doc.
- `#138` completed: added gameplay bug/backlog snapshot doc and linked it in operations docs.
- Validation baseline remains green locally (`ant clean run-junit5-tests`, `ant clean jar`).

## v16 Priorities
- [x] #144 Add operations-index link checker script.
- [x] #145 Add manual smoke evidence template doc.
- [x] #146 Validate selected dictionary file against active board size.

## v16 Success Criteria
- A script exists to verify markdown docs listed in `docs/operations_index.md` all exist.
- A reusable manual smoke evidence template is available in docs and linked from operations index.
- Dictionary picker validation uses the active board size (no hardcoded board size) with focused test coverage.
- Each item follows repo workflow:
  - one issue -> one branch -> one PR
  - full local validations before merge
  - final review before merge to `master`

## Next Candidate Ideas (Post-v16)
- Add a one-command local pre-merge check that runs all roadmap and docs guard scripts.
- Add a compact runbook section for issue-to-branch naming and PR title conventions.
- Add a focused gameplay integration test covering dictionary switch + first submission on non-default board size.
