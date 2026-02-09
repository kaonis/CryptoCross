/*
 * @author Charis Ioannikios Kaonis 321/2010069
 * @author Georgios Moustakas 321/2011102
 * @project CryptoCross (Κρυπτόλεξο) 2016
 */
package cryptocross;

import java.awt.Color;

/**
 * Represents a balandeur (wildcard) letter on the game board.
 * Balandeur letters use the '?' character and have minimal point value.
 */
public class BalandeurLetter extends Letter {
    
    /**
     * Constructs a new BalandeurLetter with the specified character.
     * Assigns points and sets the color to white.
     * @param ch_letter the character this letter represents (typically '?')
     * @throws UknownCharacterException if the character is not recognized
     */
    public BalandeurLetter(Character ch_letter) throws UknownCharacterException {
        super(ch_letter);
        assignPoints();
        setColor(Color.WHITE);
    }
    
    /**
     * Constructs a new BalandeurLetter with the specified character and coordinates.
     * Assigns points and sets the color to white.
     * @param ch_letter the character this letter represents (typically '?')
     * @param int_x_coord the x coordinate on the board
     * @param int_y_coord the y coordinate on the board
     * @throws UknownCharacterException if the character is not recognized
     */
    public BalandeurLetter(Character ch_letter, Integer int_x_coord, Integer int_y_coord) 
            throws UknownCharacterException {
        super(ch_letter, int_x_coord, int_y_coord);
        assignPoints();
        setColor(Color.WHITE);
    }
    
    /**
     * Sets the point value of this letter.
     * @param int_points the point value to set
     */
    @Override
    public void setPoints(Integer int_points) {
        this.int_points = int_points;
    }
    
}
