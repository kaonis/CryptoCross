# CryptoCross Roadmap v5

## Date
- 2026-02-15

## v4 Completion Summary
- `#48` completed: extracted submission decisions into `WordSubmissionService` with dedicated unit tests.
- `#49` completed: added immediate adjacency feedback during letter selection via `WordSelectionService`.
- `#50` completed: introduced `HelpActionStateService` and wired board-mutating help paths to reset selection state consistently.
- Validation baseline remains green locally (`ant clean run-junit5-tests`, `ant clean jar`).

## v5 Priorities
- [ ] #56 Add deterministic board fixture support for tests.
- [ ] #57 Localize gameplay status strings via resource bundle.
- [ ] #58 Add manual smoke-test checklist for gameplay sanity.

## v5 Success Criteria
- Deterministic board fixtures enable higher-confidence gameplay tests.
- Gameplay status strings are sourced from `CryptoCrossMessages.properties` rather than hardcoded literals.
- A concise manual smoke checklist exists for release sanity checks.
- Each item follows repo workflow:
  - one issue -> one branch -> one PR
  - full local validations before merge
  - final review before merge to `master`

## Next Candidate Ideas (Post-v5)
- Add targeted integration tests for help-action + submission interplay in the same turn.
- Add a lightweight changelog entry process in docs for each merged roadmap issue.
- Add optional strict mode that disallows deselecting non-terminal letters in an active word path.
