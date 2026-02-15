# CryptoCross Roadmap v7

## Date
- 2026-02-15

## v6 Completion Summary
- `#63` completed: added integration tests for help-action and submission interplay with deterministic board fixtures.
- `#64` completed: added `docs/roadmap_changelog.md` and `docs/roadmap_changelog_template.md`.
- `#66` completed: introduced optional strict selection mode logic for non-terminal deselection with tests, default behavior unchanged.
- Validation baseline remains green locally (`ant clean run-junit5-tests`, `ant clean jar`).

## v7 Priorities
- [ ] #71 Add UI-accessible strict selection mode toggle.
- [ ] #72 Record merged roadmap issues in `roadmap_changelog.md` during each cycle.
- [ ] #73 Add integration test for strict mode deselection in CryptoCross flow.

## v7 Success Criteria
- Strict selection mode can be enabled/disabled through the app UI while remaining off by default.
- Cycle documentation includes a completed changelog entry for each merged roadmap issue.
- Strict-mode behavior is covered by an end-to-end integration test at the flow level.
- Each item follows repo workflow:
  - one issue -> one branch -> one PR
  - full local validations before merge
  - final review before merge to `master`

## Next Candidate Ideas (Post-v7)
- Add a lightweight developer command that prints current roadmap/issue cycle status.
- Add a minimal settings persistence mechanism for user-selected gameplay options.
- Add focused tests for dictionary-file validation error messaging paths.
