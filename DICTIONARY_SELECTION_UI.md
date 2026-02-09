# Dictionary File Selection - UI Documentation

## Feature Overview

This document describes the enhanced dictionary file selection feature in CryptoCross.

## Menu Location

**Path**: Αρχείο → Επιλογή Αρχείου Λέξεων (File → Select Word File)

## File Selection Dialog

When you select "Επιλογή Αρχείου Λέξεων" from the menu, a file chooser dialog opens with the following features:

### Left Side: File Browser
- Standard file navigation interface
- Filters to show only `.txt` files
- Directory navigation

### Right Side: Format Information Panel
The dialog now displays an accessory panel with supported format information:

```
╔═══════════════════════════════════╗
║ Υποστηριζόμενη Μορφή:           ║
║ • Αρχεία κειμένου (.txt)         ║
║ • Μία λέξη ανά γραμμή            ║
║ • Ελληνικά κεφαλαία γράμματα     ║
║ • Χωρίς τόνους (Α, Ε, Η, Ι, Ο,  ║
║   Υ, Ω)                          ║
╚═══════════════════════════════════╝
```

**Translation**:
- Supported Format:
- Text files (.txt)
- One word per line
- Greek uppercase letters
- Without tones (Α, Ε, Η, Ι, Ο, Υ, Ω)

## Success Message

After successfully selecting a valid dictionary file, you'll see:

```
╔═════════════════════════════════════════╗
║        Αρχείο Λέξεων                   ║
╠═════════════════════════════════════════╣
║ Επιλέχθηκε το αρχείο: [filename].txt  ║
║                                         ║
║ Η αλλαγή του αρχείου λέξεων θα ισχύσει ║
║ στο επόμενο παιχνίδι.                  ║
║ Το τρέχον παιχνίδι χρησιμοποιεί ακόμα  ║
║ το προηγούμενο αρχείο.                 ║
╚═════════════════════════════════════════╝
```

**Translation**:
- Word File
- Selected file: [filename].txt
- The word file change will take effect in the next game.
- The current game still uses the previous file.

## Error Message

If you select an invalid file, you'll see:

```
╔═════════════════════════════════════════╗
║           Σφάλμα Αρχείου              ║
╠═════════════════════════════════════════╣
║ Σφάλμα κατά τη φόρτωση του αρχείου    ║
║ λέξεων: [filename].txt                ║
║                                         ║
║ Παρακαλώ βεβαιωθείτε ότι το αρχείο:   ║
║ • Είναι αρχείο κειμένου (.txt)        ║
║ • Περιέχει μία λέξη ανά γραμμή        ║
║ • Χρησιμοποιεί Ελληνικά κεφαλαία      ║
║   γράμματα                             ║
║ • Δεν έχει τόνους (χρησιμοποιήστε     ║
║   Α, Ε, Η, Ι, Ο, Υ, Ω)               ║
╚═════════════════════════════════════════╝
```

**Translation**:
- File Error
- Error loading word file: [filename].txt
- Please ensure that the file:
  - Is a text file (.txt)
  - Contains one word per line
  - Uses Greek uppercase letters
  - Has no tones (use Α, Ε, Η, Ι, Ο, Υ, Ω)

## Key Improvements

### Before This Change
- File selection dialog opened but had no effect
- No validation of selected files
- No user guidance on file format
- Comment in code indicated feature was incomplete

### After This Change
✅ File selection actually changes the dictionary for new games
✅ Real-time validation when file is selected
✅ Clear format documentation visible in dialog
✅ Informative success and error messages
✅ Maintains backward compatibility with default dictionary

## User Workflow

1. **Launch CryptoCross** and start a game (uses default dictionary)
2. **Select custom dictionary**: Αρχείο → Επιλογή Αρχείου Λέξεων
3. **Browse and select** your `.txt` dictionary file
4. **See confirmation** that file is loaded
5. **Start new game**: Αρχείο → Νέο Παιχνίδι
6. **New game uses** your custom dictionary!

## Technical Implementation

- Static field `str_dictionaryPath` in `CryptoCross` class stores selected path
- Validation creates test `Dictionary` object to verify file format
- `Board` class now accepts custom dictionary path via overloaded constructor
- Maintains default behavior for backward compatibility
- File path persists across game sessions (within same application instance)

## Testing

Three new automated tests verify:
1. ✅ Custom dictionary files can be loaded via `Board` constructor
2. ✅ Default dictionary constructor still works (backward compatibility)
3. ✅ Absolute paths work correctly for custom dictionaries

Total test count increased from 45 to 50 tests, all passing.
