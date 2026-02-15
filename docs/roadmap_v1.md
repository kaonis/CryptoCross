# CryptoCross Roadmap v1

## Date
- 2026-02-15

## Current State Snapshot
- Core gameplay, board operations, menu actions, dictionary-file selection, and HTML/resource externalization are implemented.
- CI workflow is manual-only (`workflow_dispatch`) to control GitHub Actions usage.
- Unit/integration test suite is present and currently green locally (`62` tests).
- Recent fixes addressed dictionary-generation hangs and Greek diaeresis normalization.

## Missing / Incomplete Areas (Observed)
- `Board.randomArrayGen` has coordinate-generation bias risk (off-by-one bound and non-random first element), which can skew colored-letter placement.
- `Letter.assignPoints` does not fail fast for unsupported characters; invalid input can create partially initialized letter state.
- `WordPilot` adjacency helper is currently not integrated into gameplay selection flow (feature decision pending).
- Runtime telemetry/debug controls are minimal (board generation prints are still hardcoded in board creation flow).

## Brainstormed Improvement Ideas
- Game rules and UX:
  - Decide and enforce adjacency behavior (strict neighbor-based vs free-form selection), then align help text and tests.
  - Prevent duplicate word submissions within the same game session.
  - Add optional “undo last letter selection” and clearer invalid-word feedback.
- Data integrity:
  - Add stricter custom dictionary validation (character set, minimum viable content).
  - Add deterministic hooks for random-dependent logic to improve testability.
- Developer experience:
  - Reduce console noise from board debug rendering.
  - Expand targeted tests around board generation invariants and error paths.

## v1 Scope (Now)
- [ ] #26 Fix colored-letter coordinate generation bias in `Board`.
- [ ] #27 Throw on unknown characters in `Letter.assignPoints`.

## Execution Plan
- Complete v1 one issue at a time:
  - one branch per issue
  - one PR per issue
  - full local validations before merge (`ant clean run-junit5-tests`, `ant clean jar`)
  - final review, then merge to `master`
- After all v1 items are merged:
  - publish `docs/roadmap_v2.md`
  - continue with next prioritized items.
