# Manual Workflow Trigger Decision Checklist

Use this checklist before running a GitHub Actions workflow manually.

## Preconditions
- [ ] Local baseline checks are green:
  - `ant clean run-junit5-tests`
  - `ant clean jar`
- [ ] Final PR self-review is complete.

## Trigger Only If Needed
- [ ] You need hosted-runner confirmation before merge/release.
- [ ] You need validation for environment differences not reproducible locally.
- [ ] You need workflow-generated artifacts for delivery/review.

## Skip Trigger If All True
- [ ] Change is docs-only or low-risk and local checks are sufficient.
- [ ] No release gate depends on hosted-runner evidence.
- [ ] No artifact is required.

## Budget Guardrails
- Prefer one manual run per PR.
- Avoid reruns unless there is a concrete failure signal.
- Record workflow run link in PR comments only when it materially supports merge decisions.
