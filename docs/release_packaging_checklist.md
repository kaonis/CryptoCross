# Release Packaging Checklist

Compact pre-release sanity checklist for packaging and validating a local build.

## 1. Clean Build
- Run from `CryptoCross/`:
  - `ant clean run-junit5-tests`
  - `ant clean jar`
- Confirm both commands finish successfully.

## 2. Artifact Presence
- Confirm jar exists at:
  - `CryptoCross/dist/CryptoCross.jar`
- Confirm jar timestamp reflects the current build run.

## 3. Basic Launch Check
- Run:
  - `java -jar CryptoCross/dist/CryptoCross.jar`
- Verify app starts without immediate exception and main window renders.
- Close app cleanly.

## 4. Quick Functional Smoke
- Start a new game.
- Select adjacent letters and submit one word.
- Confirm score/status updates.
- Confirm dictionary selection menu opens and can pick a file (no crash).

## 5. Release Readiness Notes
- If any checklist item fails, capture the command/output and stop release packaging.
- If all pass, record the command set and timestamp in PR validation evidence.
