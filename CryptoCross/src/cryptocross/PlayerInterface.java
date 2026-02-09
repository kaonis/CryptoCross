/*
 * @author Charis Ioannikios Kaonis 321/2010069
 * @author Georgios Moustakas 321/2011102
 * @project CryptoCross (Κρυπτόλεξο) 2016
 */
package cryptocross;

/**
 * Interface defining the contract for Player objects in the CryptoCross game.
 * Provides methods to manage player information including name, score, and word completion count.
 */
public interface PlayerInterface {
    
    /**
     * Gets the player's name.
     * @return the player's name as a String
     */
    public String getPlayerName();
    
    /**
     * Gets the player's current total score.
     * @return the player's score as an Integer
     */
    public Integer getPlayerScore();
    
    /**
     * Gets the number of words the player has completed.
     * @return the count of completed words as an Integer
     */
    public Integer getCompletedWordsNum();
    
    /**
     * Sets the player's name.
     * @param str_playerName the name to set for the player
     */
    public void setPlayerName(String str_playerName);
    
    /**
     * Sets the player's current score.
     * @param int_playerScore the score value to set
     */
    public void setPlayerScore(Integer int_playerScore);
    
    /**
     * Sets the number of words the player has completed.
     * @param int_wordsCompleted the count of completed words to set
     */
    public void setCompletedWordsNum(Integer int_wordsCompleted);
    
    /**
     * Increments the player's completed word count by one.
     * Called when the player successfully completes a word.
     */
    public void playerCompletedAWord();
}
