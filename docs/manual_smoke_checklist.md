# CryptoCross Manual Smoke Checklist

## Scope
Use this checklist before merging gameplay-impacting changes or creating a release build.
The goal is to quickly verify that core gameplay behavior still works end-to-end.
For strict-mode persistence specific coverage, also run:
- `docs/strict_mode_persistence_smoke_notes.md`

## Preconditions
- Run from `/Users/phantom/projects/CryptoCross/CryptoCross`.
- Build succeeds: `ant clean jar`.
- Use default dictionary unless a step says otherwise.

## Quick Pass Steps

1. Start and initialize a game
- Launch app (`java -jar dist/CryptoCross.jar` or through IDE).
- Enter a player name and choose a board size.
- Expected:
  - Board renders with correct dimensions.
  - Score labels initialize without errors.
  - No exceptions in console.

2. Letter selection flow
- Click one letter, then a neighboring letter.
- Click a non-neighboring letter after a selection.
- Expected:
  - Neighboring selection is accepted and word points update.
  - Non-neighboring selection is rejected with the status hint.

3. Word submission states
- Submit with no selected letters.
- Submit one invalid word.
- Submit one valid word, then submit the same word again.
- Expected:
  - Empty selection shows warning dialog.
  - Invalid word shows invalid status and clears current selection.
  - Valid word increases total score and found-words counter.
  - Duplicate valid word is rejected with duplicate status.

4. Help actions and counters
- Trigger each help once if available:
  - Delete row
  - Reorder row
  - Reorder column
  - Reorder board
  - Swap letters
- Expected:
  - Board updates after each action.
  - Help usage counters increment correctly.
  - Current-word selection/points are reset after board-mutating actions.
  - Swap mode status updates are shown and clear correctly.

5. Stop game flow
- Use menu action to stop/cancel game and confirm.
- Expected:
  - Game controls disable.
  - Cancel status text appears.
  - Termination info dialog is shown.

## Optional Extended Checks
- Custom dictionary file selection accepts valid `.txt` files and rejects invalid format.
- Restart with a different board size to confirm board reinitialization remains stable.

## Pass Criteria
- No crashes, uncaught exceptions, or frozen UI.
- Status messages and counters reflect actions correctly.
- Core loop (select -> submit -> score/help updates) behaves consistently.
