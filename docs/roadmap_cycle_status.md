# Roadmap Cycle Status Commands

Use these commands from `/Users/phantom/projects/CryptoCross` to quickly inspect roadmap progression.

## 1) Latest roadmap document
```bash
ls -1 docs/roadmap_v*.md | sort -V | tail -n 1
```

## 2) Open roadmap issues
```bash
gh issue list --state open --limit 50 --search "roadmap in:title"
```

## 3) Full open issue queue (current cycle)
```bash
gh issue list --state open --limit 50
```

## 4) Recently merged PRs (roadmap signal)
```bash
gh pr list --state merged --limit 20
```

## 5) Local branch health
```bash
git status --short --branch
```

## Quick Status Checklist
- Confirm newest roadmap file exists.
- Confirm expected open issues for the active roadmap version.
- Confirm no unexpected open PRs.
- Confirm working tree is clean before starting the next issue branch.
