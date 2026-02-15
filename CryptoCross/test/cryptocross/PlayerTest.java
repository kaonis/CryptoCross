package cryptocross;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Player class.
 */
public class PlayerTest {
    
    private Player player;
    
    @BeforeEach
    public void setUp() {
        player = new Player();
    }
    
    @Test
    public void testPlayerInitialization() {
        assertEquals(0, player.getPlayerScore(), 
            "Initial score should be 0");
        assertEquals(0, player.getCompletedWordsNum(), 
            "Initial completed words should be 0");
    }
    
    @Test
    public void testSetAndGetPlayerName() {
        String testName = "Test Player";
        player.setPlayerName(testName);
        assertEquals(testName, player.getPlayerName(), 
            "Player name should match the set value");
    }
    
    @Test
    public void testSetAndGetPlayerScore() {
        Integer testScore = 100;
        player.setPlayerScore(testScore);
        assertEquals(testScore, player.getPlayerScore(), 
            "Player score should match the set value");
    }
    
    @Test
    public void testSetAndGetCompletedWordsNum() {
        Integer testWords = 5;
        player.setCompletedWordsNum(testWords);
        assertEquals(testWords, player.getCompletedWordsNum(), 
            "Completed words should match the set value");
    }
    
    @Test
    public void testPlayerCompletedAWord() {
        assertEquals(0, player.getCompletedWordsNum(), 
            "Initial completed words should be 0");
        
        player.playerCompletedAWord();
        assertEquals(1, player.getCompletedWordsNum(), 
            "After completing one word, count should be 1");
        
        player.playerCompletedAWord();
        assertEquals(2, player.getCompletedWordsNum(), 
            "After completing two words, count should be 2");
    }
    
    @Test
    public void testPlayerScoreUpdate() {
        player.setPlayerScore(50);
        assertEquals(50, player.getPlayerScore());
        
        // Update score
        player.setPlayerScore(player.getPlayerScore() + 30);
        assertEquals(80, player.getPlayerScore(), 
            "Score should be updated correctly");
    }

    @Test
    public void testRegisterCompletedWordRejectsDuplicates() {
        assertTrue(player.registerCompletedWord("ΑΒ"),
            "First completed word submission should be accepted");
        assertFalse(player.registerCompletedWord("ΑΒ"),
            "Duplicate completed word submission should be rejected");
        assertTrue(player.registerCompletedWord("ΓΔ"),
            "Different completed words should still be accepted");
    }

    @Test
    public void testRegisterCompletedWordRejectsInvalidInput() {
        assertFalse(player.registerCompletedWord(null));
        assertFalse(player.registerCompletedWord(""));
        assertFalse(player.registerCompletedWord("   "));
    }
}
