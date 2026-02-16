# CryptoCross Roadmap v13

## Date
- 2026-02-16

## v12 Completion Summary
- `#111` completed: added roadmap link consistency checker script and README usage.
- `#112` completed: added manual-only workflow runbook with Actions budget controls.
- `#114` completed: added reusable smoke-evidence snippet for PR bodies.
- Validation baseline remains green locally (`ant clean run-junit5-tests`, `ant clean jar`).

## v13 Priorities
- [ ] #119 Add changelog formatting checker for roadmap entries.
- [ ] #121 Add command/script to print open roadmap issues with status.
- [ ] #122 Add dictionary-switch smoke steps to manual checklist.

## v13 Success Criteria
- A checker exists for roadmap changelog entry structure consistency.
- A simple command/script exists for quick open-roadmap-issue status output.
- Manual smoke checklist includes explicit dictionary-switch verification steps.
- Each item follows repo workflow:
  - one issue -> one branch -> one PR
  - full local validations before merge
  - final review before merge to `master`

## Next Candidate Ideas (Post-v13)
- Add a tiny helper to suggest the next roadmap version number automatically.
- Add a docs index entry for operational/runbook documents.
- Add a short PR body lint check script for required sections.
