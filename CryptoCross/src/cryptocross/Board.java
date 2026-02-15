/*
 * @author Charis Ioannikios Kaonis 321/2010069
 * @author Georgios Moustakas 321/2011102
 * @project CryptoCross (Κρυπτόλεξο) 2016
 */
package cryptocross;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents the game board for CryptoCross.
 * Manages the board layout, letter placement, colored letter distribution,
 * and provides operations for board manipulation.
 */
public class Board implements BoardInterface {
    /** Enable board snapshot printing for debugging only */
    private static final boolean DEBUG_BOARD_OUTPUT = false;

    /** The length (dimension) of the square board */
    private Integer boardLength;
    /** 2D array storing the letters on the board */
    private Letter[][] boardArray;
    /** Dictionary used for word validation and board generation */
    private Dictionary dict;
    /** Secure random number generator for randomization */
    private SecureRandom random;
    /** Number of words initially placed on the board */
    private Integer wordsNum;

    /** X coordinates of colored letters */
    private int coloredX[];
    /** Y coordinates of colored letters */
    private int coloredY[];
    /** Total count of colored letters on the board */
    private int coloredLettersCount;

    /** Count of red letters to place */
    private int redCount = 0;
    /** Count of blue letters to place */
    private int blueCount = 0;
    /** Count of balandeur letters to place */
    private int balCount = 0;

    /**
     * Constructs a new Board with the specified dimensions.
     * Initializes the board array, dictionary, and generates the initial board layout.
     * @param boardLength the length (dimension) of the square board
     * @throws UknownCharacterException if an unknown character is encountered during board generation
     */
    public Board(Integer boardLength) throws UknownCharacterException {
        this(boardLength, "el-dictionary.txt");
    }

    /**
     * Constructs a new Board with the specified dimensions and custom dictionary.
     * Initializes the board array, dictionary, and generates the initial board layout.
     * @param boardLength the length (dimension) of the square board
     * @param dictionaryPath the path to the dictionary file to use
     * @throws UknownCharacterException if an unknown character is encountered during board generation
     */
    public Board(Integer boardLength, String dictionaryPath) throws UknownCharacterException {
        this.boardLength = boardLength;
        boardArray = new Letter[boardLength][boardLength];
        random = new SecureRandom();

        dict = new Dictionary(dictionaryPath, boardLength);

        wordsNum = 0;

        generateBoard();
    }

    /**
     * Generates a new board layout based on board length.
     * Determines the number of colored letters based on board size,
     * places dictionary words, fills remaining spaces with random characters,
     * and shuffles the board using Fisher-Yates algorithm.
     * @throws UknownCharacterException if an unknown character is encountered
     */
    private void generateBoard() throws UknownCharacterException {

        switch (boardLength) {
            case 5:
                redCount = 2;
                blueCount = 1;
                balCount = random.nextInt(2);
                break;
            case 8:
                redCount = 3;
                blueCount = 2;
                balCount = random.nextInt(2);
                break;
            case 10:
                redCount = 4;
                blueCount = 3;
                balCount = random.nextInt(2);
                break;
        }

        /* We determine what colors should be placed in each letter. */
        coloredLettersCount = redCount + blueCount + balCount;

        coloredX = randomArrayGen(coloredLettersCount);
        coloredY = randomArrayGen(coloredLettersCount);

        int i = 0, j = 0;
        for (String word : dict.getBoardWords()) {
            wordsNum++;
            for (char c : word.toCharArray()) {
                Letter let = decideColor(i, j, c);
                let.setCoords(i, j);
                boardArray[i][j] = let;

                if (j + 1 == boardLength) {
                    j = 0;
                    i++;
                } else {
                    j++;
                }
            }
        }

        while (i < boardLength) {
            while (j < boardLength) {
                Character c = getRandomChar();
                Letter let = decideColor(i, j, c);
                let.setCoords(i, j);
                boardArray[i][j] = let;
                j++;
            }
            j = 0;
            i++;
        }

        /* We shuffle the board using Fisher-Yates algorithm. */
        if (DEBUG_BOARD_OUTPUT) {
            show();
        }
        shuffle(boardArray);
        if (DEBUG_BOARD_OUTPUT) {
            show();
        }
    }

    /**
     * Shuffles the board using the Fisher-Yates algorithm.
     * Updates letter coordinates after each swap to maintain consistency.
     * @param a the 2D array of letters to shuffle
     */
    private void shuffle(Letter[][] a) {

        for (int i = a.length - 1; i > 0; i--) {
            for (int j = a[i].length - 1; j > 0; j--) {
                int m = random.nextInt(i + 1);
                int n = random.nextInt(j + 1);

                Letter temp = a[i][j];
                a[i][j] = a[m][n];
                a[m][n] = temp;
                
                // Update coordinates after swap
                a[i][j].setCoords(i, j);
                a[m][n].setCoords(m, n);
            }
        }
    }

    /**
     * Determines the color for a letter at the specified position.
     * Colored positions receive red, blue, or balandeur letters in that priority order.
     * Non-colored positions receive white letters.
     * @param x the row index of the letter
     * @param y the column index of the letter
     * @param c the character for which to decide the color
     * @return a Letter object with the appropriate color
     * @throws UknownCharacterException if the character is not recognized
     */
    private Letter decideColor(int x, int y, char c) throws UknownCharacterException {
        if (isColored(x, y)) {
            if (redCount > 0) {
                redCount--;
                return new RedLetter(c); // red
            } else if (blueCount > 0) {
                blueCount--;
                return new BlueLetter(c);
            } else {
                return new BalandeurLetter('?');
            }
        }

        return new WhiteLetter(c);
    }

    /**
     * Checks if a letter at the specified position should be colored.
     * @param x the row index to check
     * @param y the column index to check
     * @return true if the position is designated for a colored letter, false otherwise
     */
    private Boolean isColored(int x, int y) {
        for (int i = 0; i < coloredLettersCount; i++) {
            if (coloredX[i] == x && coloredY[i] == y) {
                return true;
            }
        }
        return false;
    }

    /**
     * Generates a random array of unique integers within the board dimensions.
     * Each element is unique and within the range [0, boardLength-1].
     * @param size the size of the array to generate
     * @return an array of random unique integers
     */
    private int[] randomArrayGen(Integer size) {

        int result[] = new int[size];
        for (int i = 0; i < size; i++) {
            Integer newNumber;
            do {
                newNumber = random.nextInt(boardLength);
            } while (existInArray(newNumber, result, i));
            result[i] = newNumber;
        }

        return result;
    }

    /**
     * Checks if a number exists in an array.
     * @param number the number to search for
     * @param array the array to search in
     * @return true if the number exists in the array, false otherwise
     */
    private Boolean existInArray(int number, int[] array, int usedLength) {

        for (int i = 0; i < usedLength; i++) {
            if (number == array[i]) {
                return true;
            }
        }

        return false;
    }

    /**
     * Gets the 2D array representing the game board.
     * @return the board array
     */
    public Letter[][] getBoardArray() {
        return boardArray;
    }

    /**
     * Gets the length (dimension) of the board.
     * @return the board length
     */
    public Integer getBoardLength() {
        return boardLength;
    }

    /**
     * Gets the number of words initially placed on the board.
     * @return the word count
     */
    public Integer getWordsNum() {
        return wordsNum;
    }

    /**
     * Generates a random Greek (Hellenic) character.
     * @return a random character from the Greek alphabet
     */
    private Character getRandomChar() {
        final String alphabet = "ΑΒΓΔΕΖΗΘΙΚΛΜΝΞΟΠΡΣΤΥΦΧΨΩ";
        final int N = alphabet.length();

        return alphabet.charAt(random.nextInt(N));
    }

    /**
     * Debug method to print the current board state to console.
     * Displays the board in a grid format with letter characters.
     */
    public void show() {
        System.out.println("------------------------");
        for (int i = 0; i < boardLength; i++) {
            for (int j = 0; j < boardLength; j++) {
                System.out.print(boardArray[i][j].getLetterChar() + " ");
            }
            System.out.println();
        }
        System.out.println("------------------------");
    }

    /**
     * Deletes a row of the game board by replacing all letters with random characters.
     * Invalid row indices are ignored.
     * @param int_row the index of the row to delete
     */
    @Override
    public void deleteRow(Integer int_row) {
        if (int_row == null || int_row < 0 || int_row >= boardLength) {
            return; // Invalid row index
        }
        
        // Fill the specified row with random characters
        for (int j = 0; j < boardLength; j++) {
            Character c = getRandomChar();
            try {
                Letter let = new WhiteLetter(c);
                let.setCoords(int_row, j);
                boardArray[int_row][j] = let;
            } catch (UknownCharacterException e) {
                // Should not happen with Greek alphabet
            }
        }
    }

    /**
     * Reorders (shuffles) a row of the game board using Fisher-Yates algorithm.
     * Updates letter coordinates after shuffling. Invalid row indices are ignored.
     * @param int_row the index of the row to reorder
     */
    @Override
    public void reorderRow(Integer int_row) {
        if (int_row == null || int_row < 0 || int_row >= boardLength) {
            return; // Invalid row index
        }
        
        // Shuffle the letters in the specified row using Fisher-Yates algorithm
        for (int j = boardLength - 1; j > 0; j--) {
            int randomIndex = random.nextInt(j + 1);
            
            // Swap letters at positions j and randomIndex
            Letter temp = boardArray[int_row][j];
            boardArray[int_row][j] = boardArray[int_row][randomIndex];
            boardArray[int_row][randomIndex] = temp;
            
            // Update coordinates after swap
            boardArray[int_row][j].setCoords(int_row, j);
            boardArray[int_row][randomIndex].setCoords(int_row, randomIndex);
        }
    }

    /**
     * Reorders (shuffles) a column of the game board using Fisher-Yates algorithm.
     * Updates letter coordinates after shuffling. Invalid column indices are ignored.
     * @param int_column the index of the column to reorder
     */
    @Override
    public void reorderColumn(Integer int_column) {
        if (int_column == null || int_column < 0 || int_column >= boardLength) {
            return; // Invalid column index
        }
        
        // Shuffle the letters in the specified column using Fisher-Yates algorithm
        for (int i = boardLength - 1; i > 0; i--) {
            int randomIndex = random.nextInt(i + 1);
            
            // Swap letters at positions i and randomIndex
            Letter temp = boardArray[i][int_column];
            boardArray[i][int_column] = boardArray[randomIndex][int_column];
            boardArray[randomIndex][int_column] = temp;
            
            // Update coordinates after swap
            boardArray[i][int_column].setCoords(i, int_column);
            boardArray[randomIndex][int_column].setCoords(randomIndex, int_column);
        }
    }

    /**
     * Reorders (shuffles) the entire game board using Fisher-Yates algorithm.
     */
    @Override
    public void reorderBoard() {
        shuffle(boardArray);
    }

    /**
     * Swaps two letters on the board.
     * Updates their coordinates after swapping. Invalid letters or coordinates are ignored.
     * @param letter1 the first letter to swap
     * @param letter2 the second letter to swap
     */
    @Override
    public void swapLetters(Letter letter1, Letter letter2) {
        if (letter1 == null || letter2 == null) {
            return; // Invalid letters
        }
        
        Integer x1 = letter1.getXcoord();
        Integer y1 = letter1.getYcoord();
        Integer x2 = letter2.getXcoord();
        Integer y2 = letter2.getYcoord();
        
        if (x1 == null || y1 == null || x2 == null || y2 == null ||
            x1 < 0 || x1 >= boardLength || y1 < 0 || y1 >= boardLength ||
            x2 < 0 || x2 >= boardLength || y2 < 0 || y2 >= boardLength) {
            return; // Invalid coordinates
        }
        
        // Swap the letters in the board array
        boardArray[x1][y1] = letter2;
        boardArray[x2][y2] = letter1;
        
        // Update coordinates
        letter1.setCoords(x2, y2);
        letter2.setCoords(x1, y1);
    }
    
    /**
     * Checks if a word exists in the dictionary.
     * Builds a string from the letters and validates against the dictionary.
     * @param word an ArrayList of Letter objects forming the word to check
     * @return true if the word is valid, false otherwise
     */
    @Override
    public Boolean checkWordValidity(ArrayList<Letter> word) {
        if (word == null || word.isEmpty()) {
            return false;
        }
        
        // Build the word string from letters
        StringBuilder wordBuilder = new StringBuilder();
        for (Letter letter : word) {
            wordBuilder.append(letter.getLetterChar());
        }
        String wordString = wordBuilder.toString();
        
        // Check if word exists in dictionary
        return dict.containsWord(wordString);
    }
}
