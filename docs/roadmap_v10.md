# CryptoCross Roadmap v10

## Date
- 2026-02-15

## v9 Completion Summary
- `#87` completed: created `docs/roadmap_v9.md` and advanced planning cycle.
- `#88` completed: added startup-focused strict-mode persistence integration tests.
- `#89` completed: added strict-mode enabled indicator in gameplay status area.
- `#90` completed: added changelog-based release-notes generator script and usage docs.
- Validation baseline remains green locally (`ant clean run-junit5-tests`, `ant clean jar`).

## v10 Priorities
- [ ] #95 Add docs index for roadmap versions and completion state.
- [ ] #97 Add one-command script for targeted strict-mode checks.
- [ ] #98 Add strict-mode persistence smoke-test notes.

## v10 Success Criteria
- Roadmap versions are quickly navigable from a single index doc.
- Strict-mode focused checks can be run through one command.
- Manual smoke notes cover strict-mode persistence behavior in practical flows.
- Each item follows repo workflow:
  - one issue -> one branch -> one PR
  - full local validations before merge
  - final review before merge to `master`

## Next Candidate Ideas (Post-v10)
- Add CI/PR templates that link the relevant roadmap entry automatically.
- Add a tiny summary script for “what merged today” from roadmap changelog.
- Add snapshot docs for current gameplay settings and defaults.
