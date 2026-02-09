# CryptoCross AI Development Guide

## Project Overview

CryptoCross (Κρυπτόλεξο) is a Java 17 Swing-based word game using Greek letters. Players form words on a board where letters have color-coded point multipliers.

## Architecture

### Letter Type System (Strategy Pattern)

- **Base class**: `Letter` (abstract) with Greek character point mapping
- **Subclasses**: `WhiteLetter` (base points), `RedLetter` (2x points), `BlueLetter`, `BalandeurLetter`
- Point assignment happens in `Letter.assignPoints()` via long if-else chain mapping Greek chars (Α=1, Β=8, etc.)
- **RedLetter pattern**: Calls `super.assignPoints()` then doubles via `setPoints(2 * int_points)`
- All letters track coordinates (x,y) and `belongsToWord` flag

### Board Generation Algorithm

[Board.java](CryptoCross/src/cryptocross/Board.java):

1. Dictionary generates word list to fill board (see `Dictionary.generateBoardWords()`)
2. Colored letters distributed: 5x5 has 2 red/1 blue, 8x8 has 3 red/2 blue, 10x10 has 4 red/3 blue
3. `randomArrayGen()` picks coordinates for colored letters
4. Words laid out sequentially, remaining spaces filled with random Greek chars

### Dictionary Word Selection

[Dictionary.java](CryptoCross/src/cryptocross/Dictionary.java):

- Uses greedy algorithm: `lettersToGet()` calculates remaining space, `getNuWord()` picks random word of that length
- Reads from `el-dictionary.txt` (production) or `test-dictionary.txt` (testing)
- All words capitalized via `Capitalize()` helper (Greek uppercase)
- **Important**: Use `SecureRandom` consistently (already established pattern)

## Build System (Ant, not Maven/Gradle)

### Key Commands

Working directory is **always** `CryptoCross/` subdirectory:

```bash
cd CryptoCross
ant clean compile    # Build
ant jar              # Create dist/CryptoCross.jar
ant run-junit5-tests # Run all tests (custom target)
java -jar dist/CryptoCross.jar  # Run game
```

### Test Execution

Use custom Ant target instead of direct JUnit commands:

```bash
ant run-junit5-tests  # Preferred method
```

Manual test run (cross-platform classpath):

```bash
java -jar lib/junit-platform-console-standalone-1.10.1.jar \
  --class-path build/classes:build/test/classes \  # Use ; on Windows
  --scan-class-path
```

## Coding Conventions

### Naming Patterns

- **Hungarian notation used**: `int_playerScore`, `str_playerName`, `tf_deleteRow`, `btn_checkWord`
- **Boolean flags**: `tf_` prefix (e.g., `tf_belongsToWord`)
- **Arrays**: `ar_` prefix (e.g., `ar_letters`)
- **Interfaces**: Suffix with `Interface` ([PlayerInterface.java](CryptoCross/src/cryptocross/PlayerInterface.java), [BoardInterface.java](CryptoCross/src/cryptocross/BoardInterface.java))

### Known Issue: UknownCharacterException

Typo in [UknownCharacterException.java](CryptoCross/src/cryptocross/UknownCharacterException.java) (should be "Unknown"). **Maintain spelling for consistency** - do not rename.

### Greek Character Handling

- All game logic uses **uppercase Greek letters** (Α, Β, Γ, Δ...)
- Point values hardcoded in `Letter.assignPoints()` (137 lines of if-else)
- When adding letters, follow existing pattern - never use switch statement

## Testing Patterns

### JUnit 5 Structure

Example from [PlayerTest.java](CryptoCross/test/cryptocross/PlayerTest.java):

```java
@BeforeEach
public void setUp() {
    player = new Player();
}

@Test
public void testPlayerInitialization() {
    assertEquals(0, player.getPlayerScore(),
        "Initial score should be 0");  // Descriptive messages on second line
}
```

### Test Coverage (24 tests total)

- **PlayerTest**: 6 tests (initialization, getters/setters, word completion)
- **DictionaryTest**: 9 tests (board generation for 5x5, 8x8, word validation)
- **LetterTest**: 9 tests (point assignment, color validation, Greek character handling)
- **BoardTest**: Exists but minimal

## Integration Points

### File Dependencies

- **Dictionary files**: `el-dictionary.txt` (production), `test-dictionary.txt` (testing)
  - Loaded from project root, not resources folder
  - One word per line, case-insensitive
- **Images**: Located in `src/images/` and `build/classes/images/`

### Swing GUI Structure

[CryptoCross.java](CryptoCross/src/cryptocross/CryptoCross.java) (815 lines):

- Main JFrame with nested panel hierarchy
- Letter buttons in 2D array: `btnArray_letter[x][y]`
- Help system with limited uses (tracked via `int_UsedDeleteRow`, etc.)
- Game state in `Player`, `Board`, and `WordPilot` objects

## Critical Workflows

### Adding New Letter Types

1. Extend `Letter` abstract class
2. Implement `setPoints(Integer)` and override `assignPoints()`
3. Set color in constructor via `setColor(Color)`
4. Update `Board.decideColor()` to instantiate new type
5. Add tests in `LetterTest.java` following existing patterns

### Modifying Board Generation

- Edit [Board.java](CryptoCross/src/cryptocross/Board.java) `generateBoard()` for size/color rules
- Edit [Dictionary.java](CryptoCross/src/cryptocross/Dictionary.java) `lettersToGet()` for word selection logic
- **Always test with multiple board sizes** (5x5, 8x8, 10x10)

### Java 17 Migration Complete

Per [MIGRATION.md](MIGRATION.md):

- Source/target set to Java 17 in `nbproject/project.properties`
- No Java 8 constructs remain (e.g., `new String()` replaced with `""`)
- All code compiles with zero warnings
