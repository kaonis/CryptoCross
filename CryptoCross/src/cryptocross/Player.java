/*
 * @author Charis Ioannikios Kaonis 321/2010069
 * @author Georgios Moustakas 321/2011102
 * @project CryptoCross (Κρυπτόλεξο) 2016
 */
package cryptocross;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a player in the CryptoCross game.
 * Tracks the player's name, score, and number of words completed.
 */
public class Player implements PlayerInterface {

    /** The player's name */
    private String str_playerName;
    
    /** The current total number of points the player has collected */
    private Integer int_playerScore;

    /** The number of words the player has completed */
    private Integer int_wordsCompleted;

    /** Set of words already completed in the current game */
    private Set<String> completedWords;

    /**
     * Constructs a new Player with initial values.
     * Score and words completed are initialized to 0.
     */
    public Player() {
        this.int_playerScore = 0;
        this.int_wordsCompleted = 0;
        this.completedWords = new HashSet<>();
    }

    /**
     * Gets the player's name.
     * @return the player's name
     */
    @Override
    public String getPlayerName() {
        return str_playerName;
    }
    
    /**
     * Gets the player's current score.
     * @return the player's score
     */
    @Override
    public Integer getPlayerScore() {
        return int_playerScore;
    }

    /**
     * Gets the number of words the player has completed.
     * @return the count of completed words
     */
    @Override
    public Integer getCompletedWordsNum() {
        return int_wordsCompleted;
    }
    
    /**
     * Sets the player's name.
     * @param str_playerName the name to set
     */
    @Override
    public void setPlayerName(String str_playerName) {
        this.str_playerName = str_playerName;
    }
    
    /**
     * Sets the player's score.
     * @param int_playerScore the score to set
     */
    @Override
    public void setPlayerScore(Integer int_playerScore) {
        this.int_playerScore = int_playerScore;
    }
    
    /**
     * Sets the number of words the player has completed.
     * @param int_wordsCompleted the count to set
     */
    @Override
    public void setCompletedWordsNum(Integer int_wordsCompleted) {
        this.int_wordsCompleted = int_wordsCompleted;
    }
    
    /**
     * Increments the player's completed word count by one.
     */
    @Override
    public void playerCompletedAWord() {
        this.int_wordsCompleted++;
    }

    /**
     * Registers a completed word for the current game.
     * @param word the completed word
     * @return true if the word was new and registered, false if invalid or already completed
     */
    public boolean registerCompletedWord(String word) {
        if (word == null) {
            return false;
        }

        String normalizedWord = word.trim();
        if (normalizedWord.isEmpty()) {
            return false;
        }

        return completedWords.add(normalizedWord);
    }

}
