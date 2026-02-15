# CryptoCross Roadmap v8

## Date
- 2026-02-15

## v7 Completion Summary
- `#71` completed: added a UI-accessible strict selection mode checkbox (default off) and linked it to selection behavior.
- `#72` completed: enforced cycle-time roadmap changelog recording with complete issue/PR/test evidence entries.
- `#73` completed: added strict-mode deselection integration test coverage.
- `#74` completed: created `docs/roadmap_v7.md` and advanced the roadmap cycle.
- Validation baseline remains green locally (`ant clean run-junit5-tests`, `ant clean jar`).

## v8 Priorities
- [ ] #79 Add cycle status command/doc to show roadmap progression.
- [ ] #80 Add automated UI-flow check for strict mode toggle behavior.
- [ ] #82 Persist strict selection mode preference across sessions.

## v8 Success Criteria
- Cycle status can be quickly inspected by contributors.
- Strict-mode UI behavior has an automated regression check.
- Strict-mode preference persists across app restarts with safe default behavior.
- Each item follows repo workflow:
  - one issue -> one branch -> one PR
  - full local validations before merge
  - final review before merge to `master`

## Next Candidate Ideas (Post-v8)
- Add a lightweight release notes generator from roadmap changelog entries.
- Add visual indicator in the status area when strict mode is enabled.
- Add targeted tests around new game/reset behavior with strict mode toggled.
