# CryptoCross Roadmap v4

## Date
- 2026-02-15

## v3 Completion Summary
- `#41` completed: stabilized dictionary normalization for combined Greek diacritics; intermittent unknown-character board-generation failure addressed.
- `#38` completed: `Board.checkWordValidity` now enforces adjacency between consecutive selected letters.
- `#39` completed: duplicate completed words are rejected and no longer score twice.
- `#40` completed: README CI section now matches manual-only workflow policy.
- Validation baseline remains green locally (`ant clean run-junit5-tests`, `ant clean jar`).

## v4 Priorities
- [ ] #48 Extract word submission decisions into testable service.
- [ ] #49 Add immediate adjacency feedback during letter selection.
- [ ] #50 Add help-tool interaction tests for score/current-word state.

## v4 Success Criteria
- Word submission decisions are testable outside Swing event handlers.
- Players get immediate feedback when selecting non-adjacent letters.
- Help-tool interactions have explicit regression coverage for score/current-word state.
- Each item follows repo workflow:
  - one issue -> one branch -> one PR
  - full local validations before merge
  - final review before merge to `master`

## Next Candidate Ideas (Post-v4)
- Add a deterministic board fixture mode for higher-confidence gameplay tests.
- Add localized status-message constants to avoid hardcoded UI strings in action handlers.
- Add a manual smoke-test checklist document for release readiness.
