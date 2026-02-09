/*
 * @author Charis Ioannikios Kaonis 321/2010069
 * @author Georgios Moustakas 321/2011102
 * @project CryptoCross (Κρυπτόλεξο) 2016
 */
package cryptocross;

import java.util.ArrayList;

/**
 * Interface defining the contract for Board objects in the CryptoCross game.
 * Provides methods to manipulate the game board including row/column operations,
 * letter swapping, and word validation.
 */
public interface BoardInterface {
    
    /**
     * Gets the 2D array representing the game board.
     * @return a 2D array of Letter objects representing the board
     */
    public Letter[][] getBoardArray();
    
    /**
     * Gets the length (dimension) of the board.
     * @return the board length as an Integer
     */
    public Integer getBoardLength();
    
    /**
     * Gets the number of words initially placed on the board.
     * @return the count of words as an Integer
     */
    public Integer getWordsNum();
    
    /**
     * Deletes a row by replacing all letters in it with random characters.
     * @param int_row the index of the row to delete
     */
    public void deleteRow(Integer int_row);

    /**
     * Reorders (shuffles) the letters within a specific row.
     * @param int_row the index of the row to reorder
     */
    public void reorderRow(Integer int_row);

    /**
     * Reorders (shuffles) the letters within a specific column.
     * @param int_column the index of the column to reorder
     */
    public void reorderColumn(Integer int_column);

    /**
     * Reorders (shuffles) the entire game board.
     */
    public void reorderBoard();
    
    /**
     * Swaps the positions of two letters on the board.
     * @param letter1 the first letter to swap
     * @param letter2 the second letter to swap
     */
    public void swapLetters(Letter letter1, Letter letter2);
    
    /**
     * Checks if a word exists in the dictionary.
     * @param word an ArrayList of Letter objects forming the word to check
     * @return true if the word is valid, false otherwise
     */
    public Boolean checkWordValidity(ArrayList<Letter> word);
        
}
