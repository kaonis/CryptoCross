# Manual CI Workflow Runbook

## Purpose
Use this runbook to keep GitHub Actions usage budget-conscious while maintaining release confidence.

## Policy
- CI workflows are manual-only (`workflow_dispatch`).
- Do not enable automatic triggers (`push`, `pull_request`) unless explicitly approved.
- Run Actions only when local validation cannot provide enough confidence.

## Issue, Branch, and PR Conventions
- Create a GitHub issue for each chunk of work before implementation starts.
- Use one issue -> one branch -> one PR; do not combine multiple issues in one PR.
- Branch naming format: `codex/issue-<issue-number>-<short-kebab-summary>`.
- PR title format: `<type>: <short summary> (#<issue-number>)`.
- Include required PR body sections (`## Summary`, `## Validation`) and close the linked issue from the PR body.

## Local Validation Baseline (Before Any Manual CI Run)
Run from `/Users/phantom/projects/CryptoCross/CryptoCross`:

```bash
ant clean run-junit5-tests
ant clean jar
```

If a change is targeted, also run focused checks for touched areas (for example helper scripts in `/Users/phantom/projects/CryptoCross/scripts`).

## When To Trigger Manual GitHub Actions
Trigger the workflow from the Actions tab only when at least one of these applies:
- You need a clean hosted-runner confirmation before a release merge.
- You need confirmation for environment differences not reproducible locally.
- You need an artifact from the workflow run.

If none of the above apply and local checks are green, skip manual CI.

## Budget Controls
- Prefer one manual run per PR after final local review.
- Avoid reruns unless there is a concrete failure signal to investigate.
- Avoid repeated runs for docs-only changes unless explicitly required.
- Keep workflow scope minimal (use existing `Build and Test` workflow only when needed).

## Quick Execution
1. Complete local checks and final PR self-review.
2. Open Actions -> `Build and Test` -> `Run workflow`.
3. Wait for completion and verify:
   - build/test pass
   - artifact upload success when expected
4. Record run link in PR comments only if it adds decision value.

## Current Workflow File
- `/Users/phantom/projects/CryptoCross/.github/workflows/build-test.yml`
