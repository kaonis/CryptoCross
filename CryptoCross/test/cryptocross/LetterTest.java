package cryptocross;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;

/**
 * Unit tests for the Letter class hierarchy.
 */
public class LetterTest {
    
    @Test
    public void testWhiteLetterCreation() throws UknownCharacterException {
        WhiteLetter letter = new WhiteLetter('Α');
        assertEquals('Α', letter.getLetterChar(), 
            "Letter character should be 'Α'");
        assertEquals(1, letter.getPoints(), 
            "Points for 'Α' should be 1");
        assertEquals(Color.WHITE, letter.getColor(), 
            "White letter color should be WHITE");
    }
    
    @Test
    public void testWhiteLetterWithCoordinates() throws UknownCharacterException {
        WhiteLetter letter = new WhiteLetter('Β', 2, 3);
        assertEquals('Β', letter.getLetterChar());
        assertEquals(8, letter.getPoints(), 
            "Points for 'Β' should be 8");
        assertEquals(Color.WHITE, letter.getColor());
    }
    
    @Test
    public void testRedLetterCreation() throws UknownCharacterException {
        RedLetter letter = new RedLetter('Γ');
        assertEquals('Γ', letter.getLetterChar());
        assertEquals(8, letter.getPoints(), 
            "Points for 'Γ' in RedLetter should be 8 (4 base points x 2)");
        assertEquals(Color.RED, letter.getColor(), 
            "Red letter color should be RED");
    }
    
    @Test
    public void testBlueLetterCreation() throws UknownCharacterException {
        BlueLetter letter = new BlueLetter('Δ');
        assertEquals('Δ', letter.getLetterChar());
        assertEquals(4, letter.getPoints(), 
            "Points for 'Δ' in BlueLetter should be 4 (base points, no multiplier)");
        assertEquals(Color.BLUE, letter.getColor(), 
            "Blue letter color should be BLUE");
    }
    
    @Test
    public void testBalandeurLetterCreation() throws UknownCharacterException {
        BalandeurLetter letter = new BalandeurLetter('?');
        assertEquals('?', letter.getLetterChar(), 
            "Balandeur letter character should be '?'");
        assertEquals(1, letter.getPoints(), 
            "Points for Balandeur letter should be 1");
        assertEquals(Color.WHITE, letter.getColor(), 
            "Balandeur letter color should be WHITE");
    }
    
    @Test
    public void testSetCoordinates() throws UknownCharacterException {
        WhiteLetter letter = new WhiteLetter('Α');
        letter.setCoords(5, 7);
        assertEquals(5, letter.getXcoord(), 
            "X coordinate should be 5");
        assertEquals(7, letter.getYcoord(), 
            "Y coordinate should be 7");
    }
    
    @Test
    public void testSetPoints() throws UknownCharacterException {
        WhiteLetter letter = new WhiteLetter('Α');
        letter.setPoints(10);
        assertEquals(10, letter.getPoints(), 
            "Points should be updated to 10");
    }
    
    @Test
    public void testLetterPointsValues() throws UknownCharacterException {
        // Test various Greek letters and their point values
        assertEquals(1, new WhiteLetter('Ε').getPoints(), 
            "Points for 'Ε' should be 1");
        assertEquals(10, new WhiteLetter('Ξ').getPoints(), 
            "Points for 'Ξ' should be 10");
        assertEquals(3, new WhiteLetter('Ω').getPoints(), 
            "Points for 'Ω' should be 3");
        assertEquals(2, new WhiteLetter('Κ').getPoints(), 
            "Points for 'Κ' should be 2");
    }
    
    @Test
    public void testToString() throws UknownCharacterException {
        WhiteLetter letter = new WhiteLetter('Α');
        letter.setCoords(1, 2);
        String str = letter.toString();
        assertTrue(str.contains("Α"), 
            "toString should contain the character");
        assertTrue(str.contains("Points: 1"), 
            "toString should contain the points in the 'Points: 1' format");
    }

    @Test
    public void testUnknownCharacterThrowsException() {
        assertThrows(UknownCharacterException.class, () -> new WhiteLetter('A'),
            "Unsupported non-Greek characters should throw UknownCharacterException");
        assertThrows(UknownCharacterException.class, () -> new RedLetter('1'),
            "Unsupported numeric characters should throw UknownCharacterException");
    }
}
