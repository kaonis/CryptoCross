/*
 * @author Charis Ioannikios Kaonis 321/2010069
 * @author Georgios Moustakas 321/2011102
 * @project CryptoCross (Κρυπτόλεξο) 2016
 */
package cryptocross;

import java.awt.Color;

/**
 * Abstract base class representing a single letter on the game board.
 * Each letter has a character, position coordinates, point value, and color.
 */
public abstract class Letter {

    /** The character/letter this object represents */
    protected Character ch_letter;
    /** The x coordinate of the letter on the board */
    protected Integer int_x_coord;
    /** The y coordinate of the letter on the board */
    protected Integer int_y_coord;
    /** Flag indicating if the letter belongs to a marked word */
    protected Boolean tf_belongsToWord;
    /** Points value of the letter */
    protected Integer int_points;
    /** Color group of letter, each color represents a different points multiplier */
    protected Color col_color;

    /**
     * Constructs a new Letter with the specified character.
     * @param ch_letter the character this letter represents
     */
    public Letter(Character ch_letter) {
        this.ch_letter = ch_letter;
    }

    /**
     * Constructs a new Letter with the specified character and coordinates.
     * @param ch_letter the character this letter represents
     * @param int_x_coord the x coordinate on the board
     * @param int_y_coord the y coordinate on the board
     */
    public Letter(Character ch_letter, Integer int_x_coord, Integer int_y_coord) {
        this.ch_letter = ch_letter;
    }

    /**
     * Gets the character this letter represents.
     * @return the character
     */
    public Character getLetterChar() {
        return ch_letter;
    }

    /**
     * Gets the point value of this letter.
     * @return the point value
     */
    public Integer getPoints() {
        return int_points;
    }

    /**
     * Gets the color of this letter.
     * @return the color
     */
    public Color getColor() {
        return col_color;
    }

    /**
     * Gets the x coordinate of this letter on the board.
     * @return the x coordinate
     */
    public Integer getXcoord() {
        return int_x_coord;
    }

    /**
     * Gets the y coordinate of this letter on the board.
     * @return the y coordinate
     */
    public Integer getYcoord() {
        return int_y_coord;
    }

    /**
     * Gets whether this letter belongs to a marked word.
     * @return true if belongs to a word, false otherwise
     */
    public Boolean getBelongsToWord() {
        return tf_belongsToWord;
    }

    /**
     * Sets the x coordinate of this letter.
     * @param int_x_coord the x coordinate to set
     */
    public void setXcoord(Integer int_x_coord) {
        this.int_x_coord = int_x_coord;
    }

    /**
     * Sets the y coordinate of this letter.
     * @param int_y_coord the y coordinate to set
     */
    public void setYcoord(Integer int_y_coord) {
        this.int_y_coord = int_y_coord;
    }

    /**
     * Sets the point value of this letter.
     * Must be implemented by subclasses.
     * @param int_points the point value to set
     */
    abstract void setPoints(Integer int_points);

    /**
     * Sets the color of this letter.
     * @param col_color the color to set
     */
    public void setColor(Color col_color) {
        this.col_color = col_color;
    }

    /**
     * Assigns points to this letter based on its character.
     * Each Greek letter has a specific point value based on its rarity.
     * @throws UknownCharacterException if the character is not recognized
     */
    protected void assignPoints() throws UknownCharacterException {
        if (ch_letter == 'Α') {
            int_points = 1;
        } else if (ch_letter == 'Β') {
            int_points = 8;
        } else if (ch_letter == 'Γ') {
            int_points = 4;
        } else if (ch_letter == 'Δ') {
            int_points = 4;
        } else if (ch_letter == 'Ε') {
            int_points = 1;
        } else if (ch_letter == 'Ζ') {
            int_points = 8;
        } else if (ch_letter == 'Η') {
            int_points = 1;
        } else if (ch_letter == 'Θ') {
            int_points = 8;
        } else if (ch_letter == 'Ι') {
            int_points = 1;
        } else if (ch_letter == 'Κ') {
            int_points = 2;
        } else if (ch_letter == 'Λ') {
            int_points = 3;
        } else if (ch_letter == 'Μ') {
            int_points = 3;
        } else if (ch_letter == 'Ν') {
            int_points = 1;
        } else if (ch_letter == 'Ξ') {
            int_points = 10;
        } else if (ch_letter == 'Ο') {
            int_points = 1;
        } else if (ch_letter == 'Π') {
            int_points = 2;
        } else if (ch_letter == 'Ρ') {
            int_points = 2;
        } else if (ch_letter == 'Σ') {
            int_points = 1;
        } else if (ch_letter == 'Τ') {
            int_points = 1;
        } else if (ch_letter == 'Υ') {
            int_points = 2;
        } else if (ch_letter == 'Φ') {
            int_points = 8;
        } else if (ch_letter == 'Χ') {
            int_points = 10;
        } else if (ch_letter == 'Ψ') {
            int_points = 10;
        } else if (ch_letter == 'Ω') {
            int_points = 3;
        } else if (ch_letter == '?') {
            int_points = 1;
        }

    }

    /**
     * Sets both x and y coordinates of this letter.
     * @param int_x_coord the x coordinate to set
     * @param int_y_coord the y coordinate to set
     */
    public void setCoords(Integer int_x_coord, Integer int_y_coord) {
        this.int_x_coord = int_x_coord;
        this.int_y_coord = int_y_coord;
    }

    /**
     * Returns a string representation of this letter.
     * @return a string containing the letter's character, points, and coordinates
     */
    @Override
    public String toString() {
        return "Letter:" + "Character: " + ch_letter + ", Points: " + int_points + ", xCoord: " + int_x_coord + ", yCoord: " + int_y_coord;
    }
}
