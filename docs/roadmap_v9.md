# CryptoCross Roadmap v9

## Date
- 2026-02-15

## v8 Completion Summary
- `#79` completed: added `docs/roadmap_cycle_status.md` with a repeatable status-check command set.
- `#80` completed: added automated strict-mode toggle flow harness coverage.
- `#82` completed: persisted strict selection mode preference across sessions.
- `#81` completed: created `docs/roadmap_v8.md` and advanced roadmap planning.
- Validation baseline remains green locally (`ant clean run-junit5-tests`, `ant clean jar`).

## v9 Priorities
- [ ] #88 Add strict-mode persistence integration test around startup defaults.
- [ ] #89 Show strict-mode enabled indicator in gameplay status area.
- [ ] #90 Add small release-notes generator from roadmap changelog entries.

## v9 Success Criteria
- Startup strict-mode behavior is verifiable through focused integration tests.
- Strict-mode enabled state is clearly visible in gameplay UI.
- Roadmap changelog entries can be transformed into lightweight release notes.
- Each item follows repo workflow:
  - one issue -> one branch -> one PR
  - full local validations before merge
  - final review before merge to `master`

## Next Candidate Ideas (Post-v9)
- Add a one-command developer script to run targeted strict-mode checks.
- Add smoke-test notes for strict-mode persistence verification.
- Add a tiny docs index for all roadmap versions and their completion state.
