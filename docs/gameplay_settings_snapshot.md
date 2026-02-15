# CryptoCross Gameplay Settings Snapshot

Current behavior snapshot from the codebase (`CryptoCross/src/cryptocross/CryptoCross.java`).

## Startup Defaults
- Player name is required at startup (empty input is rejected).
- Board size options:
  - `5x5`
  - `8x8`
  - `10x10`
- Default dictionary path: `el-dictionary.txt`.
- If a custom dictionary file is selected, it is applied on the next game session.

## Game Targets By Board Size

| Board Size | Max Words | Goal Points |
|---|---:|---:|
| `5x5` | 4 | 200 |
| `8x8` | 6 | 300 |
| `10x10` | 8 | 400 |

## Help Action Limits By Board Size

| Board Size | Delete Row | Reorder Row | Reorder Column | Reorder Board | Swap Letters |
|---|---:|---:|---:|---:|---:|
| `5x5` | 3 | 3 | 3 | 5 | 6 |
| `8x8` | 4 | 4 | 4 | 7 | 8 |
| `10x10` | 5 | 5 | 5 | 8 | 10 |

## Strict Selection Mode
- Default state when no preference exists: `disabled` (`false`).
- Preference key: `strict.selection.mode.enabled`.
- UI controls:
  - Strict mode checkbox in gameplay status row.
  - Strict-mode enabled indicator label shown when enabled.
- Behavior:
  - Disabled: any selected letter can be deselected.
  - Enabled: only the last selected letter can be deselected.
- Persistence:
  - Checkbox state is loaded at startup.
  - Checkbox changes are saved immediately.
- Control lifecycle:
  - Strict-mode checkbox is disabled when gameplay controls are disabled (win/lose/cancelled game).

## End Conditions
- Win when total score reaches goal points.
- Lose when max allowed words are used without reaching goal points.
