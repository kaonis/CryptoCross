/*
 * @author Charis Ioannikios Kaonis 321/2010069
 * @author Georgios Moustakas 321/2011102
 * @project CryptoCross (Κρυπτόλεξο) 2016
 */
package cryptocross;

/**
 * Helper class to validate if letters can be selected consecutively when forming words.
 * Checks if letters are adjacent neighbors on the game board.
 */
public class WordPilot {
    /** Current X position on the board */
    private Integer xPosition;
    /** Current Y position on the board */
    private Integer yPosition;
    /** New X position being checked */
    private Integer xNew;
    /** New Y position being checked */
    private Integer yNew;
    /** Reference to the game board letter array */
    private Letter[][] ar_letters;
    
    /**
     * Constructs a new WordPilot with a reference to the game board.
     * @param ar_letters the 2D array of letters representing the game board
     */
    public WordPilot(Letter[][] ar_letters) {
        this.ar_letters = ar_letters;
    }
    
    /**
     * Gets the current X position.
     * @return the X position
     */
    public Integer getXposition() {
        return xPosition;
    }
    
    /**
     * Gets the current Y position.
     * @return the Y position
     */
    public Integer getYposition() {
        return yPosition;
    }
    
    /**
     * Sets the letter array reference.
     * @param ar_letters the 2D array of letters to set
     */
    public void setLetterArray(Letter[][] ar_letters) {
        this.ar_letters = ar_letters;
    }
    
    /**
     * Sets the current X position.
     * @param xPosition the X position to set
     */
    public void setXposition(Integer xPosition) {
        this.xPosition = xPosition;
    }
    
    /**
     * Sets the current Y position.
     * @param yPosition the Y position to set
     */
    public void setYposition(Integer yPosition) {
        this.yPosition = yPosition;
    }
    
    /**
     * Sets the new X position being checked.
     * @param xNew the new X position
     */
    public void setNewX(Integer xNew) {
        this.xNew = xNew;
    }
    
    /**
     * Sets the new Y position being checked.
     * @param yNew the new Y position
     */
    public void setNewY(Integer yNew) {
        this.yNew = yNew;
    }
    
    /**
     * Checks if a new position is adjacent to the current position.
     * Two positions are neighbors if they differ by at most 1 in both X and Y coordinates.
     * @param xPosition the current X position
     * @param yPosition the current Y position
     * @param xNew the new X position to check
     * @param yNew the new Y position to check
     * @return true if the positions are adjacent neighbors, false otherwise
     */
    public Boolean isNeighbour(Integer xPosition, Integer yPosition, Integer xNew, Integer yNew) {
        if ((xNew - xPosition == 0 || xNew - xPosition == 1) 
                && (yNew - yPosition == 0 || yNew - yPosition == 1)) {
            return true;
        } else {
            return false;
        }
    }
}
