# Manual Smoke Evidence Template

Use this template to capture concise smoke-test evidence for release notes and PRs.

## Build
- Command(s): `<build/test commands run>`
- Result: `PASS` | `FAIL`
- Notes: `<only if unexpected>`

## Run
- Launch command: `<how the app was started>`
- Result: `PASS` | `FAIL`
- Notes: `<startup/runtime observations>`

## Core Checks
- [ ] Start new game and verify board renders.
- [ ] Submit one valid word and verify score/status updates.
- [ ] Perform one help action and verify expected board/state change.
- [ ] Stop game and verify controls/state reset.

## Issues Found
- `None` or list concise issue bullets with reproduction notes.

## Final Outcome
- Overall result: `PASS` | `FAIL`
- Tester/date: `<name>, <YYYY-MM-DD>`
