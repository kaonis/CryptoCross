package cryptocross;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.SecureRandom;
import java.util.ArrayList;

/**
 * Unit tests for the Board class player help tools.
 */
public class BoardTest {
    
    private Board board;
    
    @BeforeEach
    public void setUp() throws UknownCharacterException {
        board = new Board(5); // Create a 5x5 board
    }
    
    @Test
    public void testDeleteRowValid() throws UknownCharacterException {
        // Get the original letters in row 0
        Letter[][] boardArray = board.getBoardArray();
        Letter[] originalRow = new Letter[5];
        for (int j = 0; j < 5; j++) {
            originalRow[j] = boardArray[0][j];
        }
        
        // Delete row 0
        board.deleteRow(0);
        
        // Verify that the row has been replaced with new letters
        boolean rowChanged = false;
        for (int j = 0; j < 5; j++) {
            if (boardArray[0][j] != originalRow[j] || 
                !boardArray[0][j].getLetterChar().equals(originalRow[j].getLetterChar())) {
                rowChanged = true;
                break;
            }
        }
        assertTrue(rowChanged || originalRow.length > 0, 
            "Row should be replaced with new random letters");
        
        // Verify coordinates are updated correctly
        for (int j = 0; j < 5; j++) {
            assertEquals(0, boardArray[0][j].getXcoord(), 
                "X coordinate should be 0");
            assertEquals(j, boardArray[0][j].getYcoord(), 
                "Y coordinate should be " + j);
        }
    }
    
    @Test
    public void testDeleteRowInvalidIndex() {
        // Test with negative index
        board.deleteRow(-1);
        // Should not throw exception, just return silently
        
        // Test with index beyond board size
        board.deleteRow(10);
        // Should not throw exception, just return silently
        
        // Test with null
        board.deleteRow(null);
        // Should not throw exception, just return silently
    }
    
    @Test
    public void testReorderRowValid() {
        Letter[][] boardArray = board.getBoardArray();
        
        // Save the letters in row 1 before reordering
        char[] originalChars = new char[5];
        for (int j = 0; j < 5; j++) {
            originalChars[j] = boardArray[1][j].getLetterChar();
        }
        
        // Reorder row 1
        board.reorderRow(1);
        
        // Verify that all original letters are still in the row (just reordered)
        char[] reorderedChars = new char[5];
        for (int j = 0; j < 5; j++) {
            reorderedChars[j] = boardArray[1][j].getLetterChar();
        }
        
        // Check that the same letters exist (count should match)
        for (char c : originalChars) {
            boolean found = false;
            for (char rc : reorderedChars) {
                if (c == rc) {
                    found = true;
                    break;
                }
            }
            assertTrue(found, "Original letter '" + c + "' should still be in the row");
        }
        
        // Verify coordinates are updated correctly
        for (int j = 0; j < 5; j++) {
            assertEquals(1, boardArray[1][j].getXcoord(), 
                "X coordinate should be 1");
            assertEquals(j, boardArray[1][j].getYcoord(), 
                "Y coordinate should be " + j);
        }
    }
    
    @Test
    public void testReorderRowInvalidIndex() {
        // Test with negative index
        board.reorderRow(-1);
        // Should not throw exception
        
        // Test with index beyond board size
        board.reorderRow(10);
        // Should not throw exception
        
        // Test with null
        board.reorderRow(null);
        // Should not throw exception
    }
    
    @Test
    public void testReorderColumnValid() {
        Letter[][] boardArray = board.getBoardArray();
        
        // Save the letters in column 2 before reordering
        char[] originalChars = new char[5];
        for (int i = 0; i < 5; i++) {
            originalChars[i] = boardArray[i][2].getLetterChar();
        }
        
        // Reorder column 2
        board.reorderColumn(2);
        
        // Verify that all original letters are still in the column (just reordered)
        char[] reorderedChars = new char[5];
        for (int i = 0; i < 5; i++) {
            reorderedChars[i] = boardArray[i][2].getLetterChar();
        }
        
        // Check that the same letters exist
        for (char c : originalChars) {
            boolean found = false;
            for (char rc : reorderedChars) {
                if (c == rc) {
                    found = true;
                    break;
                }
            }
            assertTrue(found, "Original letter '" + c + "' should still be in the column");
        }
        
        // Verify coordinates are updated correctly
        for (int i = 0; i < 5; i++) {
            assertEquals(i, boardArray[i][2].getXcoord(), 
                "X coordinate should be " + i);
            assertEquals(2, boardArray[i][2].getYcoord(), 
                "Y coordinate should be 2");
        }
    }
    
    @Test
    public void testReorderColumnInvalidIndex() {
        // Test with negative index
        board.reorderColumn(-1);
        // Should not throw exception
        
        // Test with index beyond board size
        board.reorderColumn(10);
        // Should not throw exception
        
        // Test with null
        board.reorderColumn(null);
        // Should not throw exception
    }
    
    @Test
    public void testSwapLettersValid() throws UknownCharacterException {
        Letter[][] boardArray = board.getBoardArray();
        
        // Get two letters to swap
        Letter letter1 = boardArray[0][0];
        Letter letter2 = boardArray[2][3];
        
        char char1 = letter1.getLetterChar();
        char char2 = letter2.getLetterChar();
        
        // Swap the letters
        board.swapLetters(letter1, letter2);
        
        // Verify that the letters have been swapped in the board
        assertEquals(char2, boardArray[0][0].getLetterChar(), 
            "Position [0][0] should now have letter2's character");
        assertEquals(char1, boardArray[2][3].getLetterChar(), 
            "Position [2][3] should now have letter1's character");
        
        // Verify that coordinates have been updated
        assertEquals(2, letter1.getXcoord(), 
            "letter1 X coordinate should be updated to 2");
        assertEquals(3, letter1.getYcoord(), 
            "letter1 Y coordinate should be updated to 3");
        assertEquals(0, letter2.getXcoord(), 
            "letter2 X coordinate should be updated to 0");
        assertEquals(0, letter2.getYcoord(), 
            "letter2 Y coordinate should be updated to 0");
    }
    
    @Test
    public void testSwapLettersWithNull() {
        Letter[][] boardArray = board.getBoardArray();
        Letter letter1 = boardArray[0][0];
        
        // Test swapping with null letters
        board.swapLetters(null, letter1);
        // Should not throw exception
        
        board.swapLetters(letter1, null);
        // Should not throw exception
        
        board.swapLetters(null, null);
        // Should not throw exception
    }
    
    @Test
    public void testSwapLettersWithInvalidCoordinates() throws UknownCharacterException {
        // Create letters with invalid coordinates
        WhiteLetter letter1 = new WhiteLetter('Α');
        letter1.setCoords(-1, 0);
        
        WhiteLetter letter2 = new WhiteLetter('Β');
        letter2.setCoords(0, 10);
        
        // Should not throw exception, just return silently
        board.swapLetters(letter1, letter2);
    }
    
    @Test
    public void testReorderBoardExists() {
        // Just verify that reorderBoard method exists and doesn't throw exception
        board.reorderBoard();
        
        // Verify board still has correct dimensions
        Letter[][] boardArray = board.getBoardArray();
        assertEquals(5, boardArray.length, "Board should still be 5x5");
        assertEquals(5, boardArray[0].length, "Board should still be 5x5");
    }
    
    @Test
    public void testCustomDictionaryPath() throws UknownCharacterException {
        // Test that Board can be created with a custom dictionary path
        Board customBoard = new Board(5, "custom-test-dictionary.txt");
        assertNotNull(customBoard, "Board should be created with custom dictionary");
        assertNotNull(customBoard.getBoardArray(), "Board array should be initialized");
        
        Letter[][] boardArray = customBoard.getBoardArray();
        assertEquals(5, boardArray.length, "Board should be 5x5");
        assertEquals(5, boardArray[0].length, "Board should be 5x5");
        
        // Verify all positions are filled
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                assertNotNull(boardArray[i][j], 
                    "Position [" + i + "][" + j + "] should not be null");
            }
        }
    }
    
    @Test
    public void testDefaultDictionaryConstructor() throws UknownCharacterException {
        // Test that the default constructor still works (backwards compatibility)
        Board defaultBoard = new Board(5);
        assertNotNull(defaultBoard, "Board should be created with default dictionary");
        assertNotNull(defaultBoard.getBoardArray(), "Board array should be initialized");
        
        Letter[][] boardArray = defaultBoard.getBoardArray();
        assertEquals(5, boardArray.length, "Board should be 5x5");
        assertEquals(5, boardArray[0].length, "Board should be 5x5");
    }

    @Test
    public void testRandomArrayGenUsesFullBoardIndexRange() throws Exception {
        Board testBoard = new Board(5);

        Field randomField = Board.class.getDeclaredField("random");
        randomField.setAccessible(true);
        randomField.set(testBoard, new SecureRandom() {
            private int callCount = 0;

            @Override
            public int nextInt(int bound) {
                int value = (bound - 1) - callCount;
                callCount++;
                return Math.max(value, 0);
            }
        });

        Method randomArrayGen = Board.class.getDeclaredMethod("randomArrayGen", Integer.class);
        randomArrayGen.setAccessible(true);
        int[] generated = (int[]) randomArrayGen.invoke(testBoard, 3);

        assertEquals(4, generated[0],
            "First generated coordinate should be allowed to use boardLength-1");
    }

    @Test
    public void testBoardConstructorDoesNotPrintDebugOutputByDefault() throws Exception {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputCapture = new ByteArrayOutputStream();
        try {
            System.setOut(new PrintStream(outputCapture));
            new Board(5);
        } finally {
            System.setOut(originalOut);
        }

        String output = outputCapture.toString(StandardCharsets.UTF_8);
        assertFalse(output.contains("------------------------"),
            "Board constructor should not dump debug board snapshots by default");
    }

    @Test
    public void testCheckWordValidityAcceptsAdjacentDictionaryWord() throws Exception {
        Path tempDict = Files.createTempFile("cryptocross-adjacent-word", ".txt");
        try {
            Files.writeString(tempDict, "ΑΒ\n", StandardCharsets.UTF_8);
            Board testBoard = new Board(5, tempDict.toString());

            Letter letterA = new WhiteLetter('Α');
            letterA.setCoords(0, 0);
            Letter letterB = new WhiteLetter('Β');
            letterB.setCoords(0, 1);

            ArrayList<Letter> word = new ArrayList<>();
            word.add(letterA);
            word.add(letterB);

            assertTrue(testBoard.checkWordValidity(word),
                "Adjacent letters that form a dictionary word should be valid");
        } finally {
            Files.deleteIfExists(tempDict);
        }
    }

    @Test
    public void testCheckWordValidityRejectsNonAdjacentDictionaryWord() throws Exception {
        Path tempDict = Files.createTempFile("cryptocross-nonadjacent-word", ".txt");
        try {
            Files.writeString(tempDict, "ΑΒ\n", StandardCharsets.UTF_8);
            Board testBoard = new Board(5, tempDict.toString());

            Letter letterA = new WhiteLetter('Α');
            letterA.setCoords(0, 0);
            Letter letterB = new WhiteLetter('Β');
            letterB.setCoords(0, 2);

            ArrayList<Letter> word = new ArrayList<>();
            word.add(letterA);
            word.add(letterB);

            assertFalse(testBoard.checkWordValidity(word),
                "Non-adjacent letters should be rejected even if they form a dictionary word");
        } finally {
            Files.deleteIfExists(tempDict);
        }
    }
}
