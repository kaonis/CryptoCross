package cryptocross;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

/**
 * Unit tests for the Dictionary class.
 */
public class DictionaryTest {
    
    private Dictionary dictionary;
    
    @BeforeEach
    public void setUp() {
        // Using the default dictionary file with board length 5
        dictionary = new Dictionary("el-dictionary.txt", 5);
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
            assertEquals(word, word.toUpperCase(), 
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
        if (!boardWords.isEmpty()) {
            String testWord = boardWords.get(0);
            assertTrue(dictionary.containsWord(testWord), 
                "Dictionary should contain words from the board");
        }
    }
    
    @Test
    public void testDoesNotContainNonExistentWord() {
        String nonExistentWord = "ZZZZZZZ";
        assertFalse(dictionary.containsWord(nonExistentWord), 
            "Dictionary should not contain non-existent words");
    }
    
    @Test
    public void testLargerBoardSize() {
        Dictionary largeDictionary = new Dictionary("el-dictionary.txt", 8);
        assertNotNull(largeDictionary.getBoardWords(), 
            "Board words should be generated for 8x8 board");
        assertTrue(largeDictionary.getTotalFilledLetters() > 0, 
            "Total filled letters should be greater than 0 for 8x8 board");
        assertTrue(largeDictionary.getTotalFilledLetters() <= 64, 
            "Total filled letters should not exceed 8x8 board size");
    }
}
