package cryptocross;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;

/**
 * Integration tests for custom dictionary file selection feature.
 */
public class DictionaryFileSelectionTest {
    
    @Test
    public void testCustomDictionaryFileCanBeLoaded() throws UknownCharacterException {
        // Test that a custom dictionary file can be successfully loaded
        String customDictPath = "custom-test-dictionary.txt";
        
        // Verify file exists
        File customFile = new File(customDictPath);
        assertTrue(customFile.exists(), 
            "Custom dictionary file should exist");
        
        // Create a board with custom dictionary
        Board board = new Board(5, customDictPath);
        assertNotNull(board, "Board should be created with custom dictionary");
        
        // Verify board is properly initialized
        Letter[][] boardArray = board.getBoardArray();
        assertNotNull(boardArray, "Board array should not be null");
        assertEquals(5, boardArray.length, "Board should be 5x5");
        
        // Verify all positions contain letters
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                assertNotNull(boardArray[i][j], 
                    "Position [" + i + "][" + j + "] should contain a letter");
            }
        }
    }
    
    @Test
    public void testDefaultDictionaryStillWorks() throws UknownCharacterException {
        // Ensure default dictionary path still works for backwards compatibility
        Board board = new Board(5);
        assertNotNull(board, "Board should be created with default dictionary");
        
        Letter[][] boardArray = board.getBoardArray();
        assertNotNull(boardArray, "Board array should not be null");
        assertEquals(5, boardArray.length, "Board should be 5x5");
    }
    
    @Test
    public void testCustomDictionaryWithAbsolutePath() throws UknownCharacterException {
        // Test that absolute paths work
        File customFile = new File("custom-test-dictionary.txt");
        String absolutePath = customFile.getAbsolutePath();
        
        Board board = new Board(5, absolutePath);
        assertNotNull(board, "Board should be created with absolute path");
        
        Letter[][] boardArray = board.getBoardArray();
        assertNotNull(boardArray, "Board array should not be null");
    }
}
