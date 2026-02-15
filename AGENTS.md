# AGENTS.md

## Scope
- Applies to the full repository at `/Users/phantom/projects/CryptoCross`.
- The actual Java application project lives in `/Users/phantom/projects/CryptoCross/CryptoCross`.

## Project Snapshot
- Stack: Java 17, Swing UI, Ant build, JUnit 5 tests.
- Entry point: `/Users/phantom/projects/CryptoCross/CryptoCross/src/cryptocross/CryptoCross.java`.
- Core game logic:
  - Board generation/manipulation: `/Users/phantom/projects/CryptoCross/CryptoCross/src/cryptocross/Board.java`
  - Dictionary loading/selection: `/Users/phantom/projects/CryptoCross/CryptoCross/src/cryptocross/Dictionary.java`
  - Letter model hierarchy: `/Users/phantom/projects/CryptoCross/CryptoCross/src/cryptocross/Letter.java` and subclasses
- UI/resource assets:
  - Icons: `/Users/phantom/projects/CryptoCross/CryptoCross/src/images`
  - HTML/help strings resource bundle: `/Users/phantom/projects/CryptoCross/CryptoCross/src/cryptocross/CryptoCrossMessages.properties`
- Dictionary files:
  - Default: `/Users/phantom/projects/CryptoCross/CryptoCross/el-dictionary.txt`
  - Test dictionaries: `/Users/phantom/projects/CryptoCross/CryptoCross/test-dictionary.txt`, `/Users/phantom/projects/CryptoCross/CryptoCross/custom-test-dictionary.txt`

## Build and Test Commands
- Run all commands from `/Users/phantom/projects/CryptoCross/CryptoCross`.
- Full test run:
  - `ant clean run-junit5-tests`
- Build jar:
  - `ant clean jar`
- Targeted test class:
  - `ant compile-test`
  - `java -jar lib/junit-platform-console-standalone-1.10.1.jar --class-path build/classes:build/test/classes --select-class cryptocross.DictionaryTest`

## GitHub Delivery Workflow Policy
- Create a new GitHub issue for each chunk of work, with appropriate labels before implementation starts.
- Create a dedicated branch per issue (one issue -> one branch).
- Open a dedicated PR per issue (one issue -> one PR).
- Before requesting merge, run all relevant validations locally:
  - Full tests (`ant clean run-junit5-tests`)
  - Build (`ant clean jar`)
  - Any targeted tests related to touched areas
- Perform a final code review pass on the PR (scope, regressions, test evidence, and issue linkage).
- If review and validations pass, merge to `master`.
- CI budget rule: GitHub Actions must not run automatically; workflows are manual-only unless explicitly needed.

## Important Workflow Guardrails
- Do not run multiple Ant commands in parallel in the same worktree. `clean`/`build` targets share the same `build/` directory and can fail with delete/compile races.
- Prefer minimal, surgical changes in existing classes. Avoid broad refactors in `CryptoCross.java` unless explicitly requested.
- Keep compatibility with Java 17 (`javac.source=17`, `javac.target=17`).

## Codebase-Specific Invariants
- Board mutation methods must preserve coordinate consistency:
  - After `deleteRow`, `reorderRow`, `reorderColumn`, `reorderBoard`, and `swapLetters`, each `Letter` should have accurate `x/y` coords.
- Dictionary word generation must always terminate, including tiny custom dictionaries.
- Greek normalization in dictionary loading must preserve uppercase, no-tonos behavior and include diaeresis normalization (`Ϊ -> Ι`, `Ϋ -> Υ`).
- Custom dictionary selection via UI updates `str_dictionaryPath` and applies on the next game, not the current in-progress board.

## When Editing Specific Areas
- If changing dictionary behavior, run at least:
  - `cryptocross.DictionaryTest`
  - `cryptocross.DictionaryFileSelectionTest`
- If changing board operations or help actions, run:
  - `cryptocross.BoardTest`
- If changing menu/action handlers in `CryptoCross.java`, run:
  - `cryptocross.MenuItemTest`
- If changing resource bundle keys or help HTML, run:
  - `cryptocross.ResourceBundleTest`
  - `cryptocross.HtmlMessageIntegrationTest`
- If changing letter scoring/colors, run:
  - `cryptocross.LetterTest`

## UI/Localization Notes
- Use Greek-language UX text consistently with current app style.
- Prefer putting long formatted help text in `CryptoCrossMessages.properties` (existing pattern) and format through `MessageFormat`.
- If adding/changing resource keys, update tests that assert key presence and formatting.
