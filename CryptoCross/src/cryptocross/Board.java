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

//Class for the game board
public class Board implements BoardInterface {

    private Integer boardLength;
    private Letter[][] boardArray;
    private Dictionary dict;
    private SecureRandom random;
    private Integer wordsNum;

    private int coloredX[];
    private int coloredY[];
    private int coloredLettersCount;

    private int redCount = 0, blueCount = 0, balCount = 0;

    /*
     * Constuctor.
     * @param boardLength The length of the board
     */
    public Board(Integer boardLength) throws UknownCharacterException {
        this.boardLength = boardLength;
        boardArray = new Letter[boardLength][boardLength];
        random = new SecureRandom();

        dict = new Dictionary("el-dictionary.txt", boardLength);

        wordsNum = 0;

        generateBoard();
    }

    /*
     * Method to generate a new board based on boardLength.
     * @return nothing.
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
        show();
        shuffle(boardArray);
        show();
    }

    /* Fisher- Yates Shuffle */
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

    /*
     * Method to decide color for a letter
     * @param x letter's row on board.
     * @param y letter's column on board.
     * @param c the character for which we decide the color.
     * @return Letter.
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

    /*
     * Method to return if a letter is colord.
     * @return true if colored or false for the opposite.
     */
    private Boolean isColored(int x, int y) {
        for (int i = 0; i < coloredLettersCount; i++) {
            if (coloredX[i] == x && coloredY[i] == y) {
                return true;
            }
        }
        return false;
    }

    /*
     * Method to generate a new random array.
     * @param size The size of the array.
     * @return Array of Integers.
     */
    private int[] randomArrayGen(Integer size) {

        int result[] = new int[size];
        for (int i = 0; i < size; i++) {
            Integer newNumber = 0;
            if (i > 0) {
                do {
                    newNumber = random.nextInt(boardLength - 1);
                } while (existInArray(newNumber, result));
            }

            result[i] = newNumber;
        }

        return result;
    }

    /*
     * Method to find if a certain number exists in an array.
     * @param number The number to find.
     * @param array The array.
     * @return True if exists or false if the opposite.
     */
    private Boolean existInArray(int number, int[] array) {

        for (int i = 0; i < array.length; i++) {
            if (number == array[i]) {
                return true;
            }
        }

        return false;
    }

    /*
     * Getters.
     */
    public Letter[][] getBoardArray() {
        return boardArray;
    }

    public Integer getBoardLength() {
        return boardLength;
    }

    public Integer getWordsNum() {
        return wordsNum;
    }

    /*
     * Method to generate a new random (Hellenic) character.
     * @return The character
     */
    private Character getRandomChar() {
        final String alphabet = "ΑΒΓΔΕΖΗΘΙΚΛΜΝΞΟΠΡΣΤΥΦΧΨΩ";
        final int N = alphabet.length();

        return alphabet.charAt(random.nextInt(N));
    }

    /*
     * Debug method to print the array.
     * @return nothing.
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

    //Delete a row of the game board
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

    //Reorder a row of the game board
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

    //Reorder a column of the game board
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

    //Reorder the game board
    @Override
    public void reorderBoard() {
        shuffle(boardArray);
    }

    //Swap 2 Letters
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
    
    //Check if a word exists in the dictionary
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
