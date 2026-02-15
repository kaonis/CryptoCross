# CryptoCross Roadmap v3

## Date
- 2026-02-15

## v2 Completion Summary
- `#31` completed: disabled unconditional board debug output by default and added a regression test.
- `#32` completed: fixed `WordPilot.isNeighbour` symmetry and added full direction/non-neighbor tests.
- Validation baseline remains green locally (`ant clean run-junit5-tests`, `ant clean jar`).

## v3 Priorities
- [ ] #41 Stabilize board generation against unknown-character flake.
- [ ] #38 Enforce adjacency in `Board.checkWordValidity`.
- [ ] #39 Prevent duplicate word submissions from scoring twice.
- [ ] #40 Align README CI docs with manual-only workflow policy.

## v3 Success Criteria
- Non-adjacent letter sequences are rejected even if they match dictionary words.
- Duplicate valid-word submissions do not increase score or completed-word count.
- Board construction/tests remain stable across repeated runs.
- Documentation accurately reflects manual-only GitHub Actions usage.
- Each item follows repo workflow:
  - one issue -> one branch -> one PR
  - full local validations before merge
  - final review before merge to `master`

## Next Candidate Ideas (Post-v3)
- Centralize word-selection validation in a testable service class to reduce GUI coupling.
- Add explicit UX feedback for adjacency violations while selecting letters.
- Add focused tests for help-tool side effects on score and selected-word state.
