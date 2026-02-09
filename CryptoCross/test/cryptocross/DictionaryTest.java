package cryptocross;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Unit tests for the Dictionary class.
 */
public class DictionaryTest {
    
    private static Dictionary dictionary;
    
    @BeforeAll
    public static void setUp() {
        // Using a small test dictionary file for fast, deterministic tests
        dictionary = new Dictionary("test-dictionary.txt", 5);
    }
    
    @Test
    public void testDictionaryInitialization() {
        assertNotNull(dictionary, "Dictionary should not be null");
        assertNotNull(dictionary.getBoardWords(), 
            "Board words list should not be null");
    }
    
    @Test
    public void testBoardWordsGeneration() {
        ArrayList<String> boardWords = dictionary.getBoardWords();
        assertFalse(boardWords.isEmpty(), 
            "Board words should not be empty");
    }
    
    @Test
    public void testTotalFilledLetters() {
        int totalFilledLetters = dictionary.getTotalFilledLetters();
        assertTrue(totalFilledLetters > 0, 
            "Total filled letters should be greater than 0");
        assertTrue(totalFilledLetters <= 25, 
            "Total filled letters should not exceed board size (5x5=25)");
    }
    
    @Test
    public void testEmptySpaces() {
        int emptySpaces = dictionary.getEmptySpaces();
        int totalFilledLetters = dictionary.getTotalFilledLetters();
        assertEquals(25, emptySpaces + totalFilledLetters, 
            "Empty spaces plus filled letters should equal board size");
        assertTrue(emptySpaces >= 0, 
            "Empty spaces should be non-negative");
    }
    
    @Test
    public void testBoardWordsAreCapitalized() {
        ArrayList<String> boardWords = dictionary.getBoardWords();
        for (String word : boardWords) {
            assertEquals(word, word.toUpperCase(Locale.ROOT), 
                "All board words should be in uppercase");
        }
    }
    
    @Test
    public void testBoardWordsLength() {
        ArrayList<String> boardWords = dictionary.getBoardWords();
        for (String word : boardWords) {
            assertTrue(word.length() <= 5, 
                "Word length should not exceed board length for 5x5 board");
        }
    }
    
    @Test
    public void testContainsWord() {
        // Get one of the board words
        ArrayList<String> boardWords = dictionary.getBoardWords();
        assertFalse(boardWords.isEmpty(), 
            "Board words should not be empty for this test");
        
        String testWord = boardWords.get(0);
        assertTrue(dictionary.containsWord(testWord), 
            "Dictionary should contain words from the board");
    }
    
    @Test
    public void testDoesNotContainNonExistentWord() {
        String nonExistentWord = "ZZZZZZZ";
        assertFalse(dictionary.containsWord(nonExistentWord), 
            "Dictionary should not contain non-existent words");
    }
    
    @Test
    public void testLargerBoardSize() {
        Dictionary largeDictionary = new Dictionary("test-dictionary.txt", 8);
        assertNotNull(largeDictionary.getBoardWords(), 
            "Board words should be generated for 8x8 board");
        assertTrue(largeDictionary.getTotalFilledLetters() > 0, 
            "Total filled letters should be greater than 0 for 8x8 board");
        assertTrue(largeDictionary.getTotalFilledLetters() <= 64, 
            "Total filled letters should not exceed 8x8 board size");
    }
    
    @Test
    public void testGreekDictionaryIsUsed() {
        // Verify that the Greek dictionary can be loaded and used
        Dictionary greekDictionary = new Dictionary("el-dictionary.txt", 5);
        assertNotNull(greekDictionary, 
            "Greek dictionary should not be null");
        assertNotNull(greekDictionary.getBoardWords(), 
            "Greek dictionary should generate board words");
        assertFalse(greekDictionary.getBoardWords().isEmpty(), 
            "Greek dictionary should generate non-empty board words");
    }
    
    @Test
    public void testGreekDictionaryNoDiacritics() {
        // Verify that board words from Greek dictionary have no diacritics
        Dictionary greekDictionary = new Dictionary("el-dictionary.txt", 5);
        ArrayList<String> boardWords = greekDictionary.getBoardWords();
        
        // Greek vowels with tonos (diacritics) - both lowercase and uppercase
        String diacritics = "άέήίόύώΆΈΉΊΌΎΏ";
        
        for (String word : boardWords) {
            for (char c : word.toCharArray()) {
                assertFalse(diacritics.contains(String.valueOf(c)), 
                    "Word '" + word + "' should not contain diacritics. Found: " + c);
            }
        }
    }
    
    @Test
    public void testGreekDictionaryWordsAreGreek() {
        // Verify that words from Greek dictionary are in Greek alphabet
        Dictionary greekDictionary = new Dictionary("el-dictionary.txt", 5);
        ArrayList<String> boardWords = greekDictionary.getBoardWords();
        
        // Greek uppercase alphabet (without diacritics)
        String greekAlphabet = "ΑΒΓΔΕΖΗΘΙΚΛΜΝΞΟΠΡΣΤΥΦΧΨΩ";
        
        for (String word : boardWords) {
            for (char c : word.toCharArray()) {
                assertTrue(greekAlphabet.contains(String.valueOf(c)), 
                    "Word '" + word + "' contains non-Greek character: " + c);
            }
        }
    }
}
