# CryptoCross Roadmap v12

## Date
- 2026-02-15

## v11 Completion Summary
- `#106` completed: added PR template checklist with roadmap/issue linkage and local validation reminders.
- `#105` completed: added `scripts/summarize_daily_merges.sh` for compact date-based roadmap merge summaries.
- `#104` completed: added `docs/gameplay_settings_snapshot.md` and linked it from `README.md`.
- Validation baseline remains green locally (`ant clean run-junit5-tests`, `ant clean jar`).

## v12 Priorities
- [ ] #111 Add roadmap-doc link checker script.
- [ ] #112 Document manual-only workflow runbook and Actions budget controls.
- [ ] #114 Add reusable PR snippet for concise smoke-test evidence.

## v12 Success Criteria
- A script exists to check roadmap/index/changelog linkage consistency.
- Manual-only workflow runbook exists with budget-conscious guidance.
- A reusable, concise smoke-evidence snippet is available for PR bodies.
- Each item follows repo workflow:
  - one issue -> one branch -> one PR
  - full local validations before merge
  - final review before merge to `master`

## Next Candidate Ideas (Post-v12)
- Add a changelog formatting checker for new entry structure.
- Add a quick command to print open roadmap issues with status.
- Add docs for dictionary-switch testing flows in manual smoke checklist.
