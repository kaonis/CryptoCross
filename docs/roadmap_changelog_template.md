# Roadmap Issue Changelog Template

Use this template when a roadmap-linked issue is merged.
Store new entries in `docs/roadmap_changelog.md` (newest first).

## Entry Template

```md
## YYYY-MM-DD - #<issue-number> - <short title>
- PR: #<pr-number>
- Summary: <1-3 concise bullets of what changed>
- Validation:
  - ant clean run-junit5-tests
  - ant clean jar
  - <any targeted tests run>
- Risk/Notes: <known limitations or `none`>
```
