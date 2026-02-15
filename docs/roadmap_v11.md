# CryptoCross Roadmap v11

## Date
- 2026-02-15

## v10 Completion Summary
- `#95` completed: added `docs/roadmap_index.md` for roadmap version navigation and status tracking.
- `#97` completed: added `scripts/run_strict_mode_checks.sh` and README usage docs for strict-mode checks.
- `#98` completed: added strict-mode persistence smoke-test notes and linked them from the manual checklist.
- Validation baseline remains green locally (`ant clean run-junit5-tests`, `ant clean jar`).

## v11 Priorities
- [ ] #106 Add PR template checklist that links related roadmap/issue IDs.
- [ ] #105 Add script to summarize merges from today's roadmap changelog entries.
- [ ] #104 Document current gameplay settings and defaults snapshot.

## v11 Success Criteria
- PR submissions include a lightweight checklist for roadmap/issue linkage.
- A one-command daily changelog summary script exists for quick status sharing.
- Gameplay settings/defaults snapshot documentation exists and is easy to find.
- Each item follows repo workflow:
  - one issue -> one branch -> one PR
  - full local validations before merge
  - final review before merge to `master`

## Next Candidate Ideas (Post-v11)
- Add a simple roadmap-doc link checker script.
- Add a short runbook for manual-only workflow execution and budget control.
- Add a template snippet for concise smoke-test evidence in PR bodies.
