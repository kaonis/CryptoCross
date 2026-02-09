# Dictionary File Format Guide

This document describes the format for custom dictionary files used in CryptoCross (Κρυπτόλεξο).

## File Format Requirements

To create a custom dictionary file for CryptoCross, your file must follow these requirements:

### 1. File Extension
- Must be a plain text file with `.txt` extension
- Example: `my-dictionary.txt`

### 2. Encoding
- UTF-8 encoding (to support Greek characters)

### 3. Word Format
- **One word per line**
- Each word should be in **UPPERCASE Greek letters**
- **No diacritics/tones** (tonoi) - use plain vowels:
  - Use: Α, Ε, Η, Ι, Ο, Υ, Ω
  - Don't use: Ά, Έ, Ή, Ί, Ό, Ύ, Ώ
- Only Greek alphabet characters (Α-Ω)

### 4. Word Length
- Words should vary in length
- Recommended: Include words from 3 to 10 characters
- The game will select words based on board size (5x5, 8x8, or 10x10)

## Example Dictionary File

```
ΑΓΑΠΗ
ΒΙΒΛΙΟ
ΓΑΤΑ
ΔΕΝΤΡΟ
ΕΛΠΙΔΑ
ΖΩΝΗ
ΗΛΙΟΣ
ΘΑΛΑΣΣΑ
ΙΔΕΑ
ΚΑΛΗΜΕΡΑ
ΛΟΓΟΣ
ΜΗΤΕΡΑ
ΝΕΡΟ
ΞΥΛΟ
ΟΥΡΑΝΟΣ
ΠΑΤΕΡΑΣ
ΡΟΛΟΙ
ΣΠΙΤΙ
ΤΡΑΠΕΖΙ
ΥΠΝΟΣ
ΦΙΛΟΣ
ΧΑΡΑ
ΨΩΜΙ
ΩΡΑ
```

## Using Your Custom Dictionary

1. **Create your dictionary file** following the format above
2. **Launch CryptoCross**
3. **Select "Αρχείο" → "Επιλογή Αρχείου Λέξεων"** from the menu
4. **Browse and select** your custom dictionary file
5. **Start a new game** - the new dictionary will be used

## Important Notes

- The dictionary change applies only to **new games**
- The current game will continue using the previous dictionary
- The application will **validate** your dictionary file when you select it
- If validation fails, you'll see an error message with details
- Keep your dictionary file in a permanent location (don't delete it)

## Default Dictionary

The default Greek dictionary (`el-dictionary.txt`) contains a comprehensive list of Greek words without diacritics, suitable for the game.

## Troubleshooting

### "Failed to load dictionary file" Error

This error occurs when:
- The file is not in plain text format
- The file contains non-Greek characters
- The file has encoding issues
- The file is empty or corrupted

**Solution**: 
1. Verify your file is UTF-8 encoded text
2. Check that all words use only Greek uppercase letters (Α-Ω)
3. Remove any diacritics/tones from vowels
4. Ensure one word per line with no extra spaces

### Words Not Appearing in Game

If your dictionary words don't appear:
- Ensure words are long enough for the board size
- Include a variety of word lengths (3-10 characters)
- Make sure the file has at least 50-100 words for good variety

## Technical Details

The Dictionary class:
- Reads the file line by line
- Capitalizes all words automatically
- Removes Greek diacritics (tonoi) as a safety measure
- Generates board words based on available space
- Validates words when checking player submissions
