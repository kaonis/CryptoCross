# CryptoCross Roadmap v2

## Date
- 2026-02-15

## v1 Completion Summary
- `#26` completed: fixed colored-letter coordinate generation bias in `Board.randomArrayGen`.
- `#27` completed: `Letter.assignPoints` now throws for unknown characters; fail-fast tests added.
- Validation baseline remains green locally (`ant clean run-junit5-tests`, `ant clean jar`).

## v2 Priorities
- [ ] #32 Disable unconditional board debug output.
- [ ] #33 Fix `WordPilot` neighbor symmetry and add dedicated tests.

## v2 Success Criteria
- Board creation should be quiet by default (no debug-board console spam during normal runs/tests).
- Neighbor logic should be deterministic, symmetric, and tested for all directions.
- Each item follows repo workflow:
  - one issue -> one branch -> one PR
  - full local validations before merge
  - final review before merge to `master`

## Next Candidate Ideas (Post-v2)
- Decide and enforce game rule for duplicate word submissions.
- Decide whether letter selection should require adjacency and align help text + UI behavior.
- Add stricter custom dictionary validation feedback (invalid rows/chars with line hints).
