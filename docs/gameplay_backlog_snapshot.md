# Gameplay Backlog Snapshot

Snapshot date: `2026-02-16`

This document captures concrete gameplay-related gaps and follow-up ideas based on current code and docs.

## Confirmed Backlog Items

1. Dictionary selection is next-game only and not persisted across app restarts
- Evidence:
  - default path is hardcoded: `CryptoCross/src/cryptocross/CryptoCross.java:35`
  - selected dictionary path is assigned only in-memory: `CryptoCross/src/cryptocross/CryptoCross.java:877`
  - new board construction uses current in-memory path on new game: `CryptoCross/src/cryptocross/CryptoCross.java:187`
- Impact:
  - users must reselect custom dictionary after restart.
- Candidate next step:
  - add persisted dictionary-path preference with safe startup fallback.

2. Dictionary file validation is hardcoded to board size `5`
- Evidence:
  - dictionary picker validation instantiates `new Dictionary(..., 5)`: `CryptoCross/src/cryptocross/CryptoCross.java:874`
  - actual board size is user-selected (`5/8/10`): `CryptoCross/src/cryptocross/CryptoCross.java:181`
- Impact:
  - selected file may pass validation for 5x5 but fail suitability for larger board sizes.
- Candidate next step:
  - validate using the active board size (or all supported board sizes) before accepting the file.

3. Dictionary picker dialogs bypass localization bundle keys
- Evidence:
  - success/error dialog text is hardcoded in handler: `CryptoCross/src/cryptocross/CryptoCross.java:879`, `CryptoCross/src/cryptocross/CryptoCross.java:887`
  - project pattern is to store UI/help text in bundle: `CryptoCross/src/cryptocross/CryptoCrossMessages.properties`
- Impact:
  - message maintenance and consistency are harder than resource-key based flows.
- Candidate next step:
  - move picker dialog strings into bundle keys and add key-presence tests.

4. Legacy `wordPilot` field in `CryptoCross` appears unused
- Evidence:
  - field declaration and initialization exist: `CryptoCross/src/cryptocross/CryptoCross.java:48`, `CryptoCross/src/cryptocross/CryptoCross.java:168`
  - no further references in `CryptoCross.java`
- Impact:
  - dead state in main UI class increases maintenance overhead.
- Candidate next step:
  - remove unused field or explicitly route remaining adjacency checks through one service path.

5. Full gameplay smoke remains primarily manual
- Evidence:
  - manual smoke flow is documented: `docs/manual_smoke_checklist.md`
  - release-time checklist includes manual launch/flow checks: `docs/release_packaging_checklist.md`
- Impact:
  - regression detection for UI-only paths depends on manual execution.
- Candidate next step:
  - add a lightweight automated UI smoke for startup, one valid submission, and dictionary-switch flow.
