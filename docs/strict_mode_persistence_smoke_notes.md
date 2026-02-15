# Strict-Mode Persistence Smoke Notes

## Scope
Concise manual checks for strict-mode preference persistence behavior.
Focus areas:
- new game flow in the same app session
- full app restart persistence

## Preconditions
- Run from `/Users/phantom/projects/CryptoCross/CryptoCross`.
- Build succeeds: `ant clean jar`.
- Launch app: `java -jar dist/CryptoCross.jar`.

## Smoke Steps

1. Verify startup default when no prior preference is set
- If needed, clear the app preference store for strict selection mode.
- Start app and begin a new game.
- Expected:
  - strict-mode checkbox starts unchecked.
  - non-terminal deselection is allowed.

2. Verify runtime strict-mode behavior
- Enable strict mode from the gameplay UI.
- Select a 3-letter path, then try to deselect the first selected letter.
- Expected:
  - deselection is blocked for non-terminal letters.
  - strict-mode indicator/status reflects enabled state.

3. Verify persistence across new game in the same session
- With strict mode still enabled, start another new game without exiting app.
- Expected:
  - strict-mode checkbox remains checked.
  - strict-mode behavior stays active in the new board.

4. Verify persistence across full app restart
- Close the app completely.
- Launch app again and start a new game.
- Expected:
  - strict-mode checkbox restores the last saved value (enabled from prior run).
  - strict-mode behavior remains active immediately.

5. Verify disabling also persists
- Disable strict mode.
- Close and relaunch app, then start a new game.
- Expected:
  - strict-mode checkbox is unchecked after restart.
  - non-terminal deselection is allowed again.

## Pass Criteria
- Startup value is deterministic (default off when unset).
- Last user-selected strict-mode state is restored after restart.
- Behavior matches checkbox state in both same-session new games and post-restart runs.
