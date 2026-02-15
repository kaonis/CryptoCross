# Release Notes Generator

## Purpose
Generate compact release notes from the latest roadmap changelog entries.

## Command
From `/Users/phantom/projects/CryptoCross`:

```bash
./scripts/generate_release_notes.sh [entry_count]
```

- `entry_count` is optional and defaults to `5`.
- The script reads `docs/roadmap_changelog.md` and prints notes to stdout.

## Examples

```bash
./scripts/generate_release_notes.sh
./scripts/generate_release_notes.sh 3
./scripts/generate_release_notes.sh 10 > /tmp/release_notes.md
```
