/*
 * @author Charis Ioannikios Kaonis 321/2010069
 * @author Georgios Moustakas 321/2011102
 * @project CryptoCross (Κρυπτόλεξο) 2016
 */
package cryptocross;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.SecureRandom;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manages the word dictionary for the CryptoCross game.
 * Handles loading words from file, generating board words, and word validation.
 */
public class Dictionary {
    
    /** List of all words loaded from the dictionary file */
    private     ArrayList<String>   wordlist;
    /** List of words selected to be placed on the board */
    private     ArrayList<String>   boardWords;
    /** Total size of the board (boardLength * boardLength) */
    private     int                 boardSize;
    /** Number of letters filled with dictionary words */
    private     int                 filledLetters;
    /** Length of one dimension of the board */
    private     int                 boardLength;
    
    /**
     * Gets the list of words selected for the board.
     * @return ArrayList of board words
     */
    public ArrayList<String> getBoardWords() {
        return this.boardWords;
    }

    /**
     * Gets the total number of letters filled with dictionary words.
     * @return the count of filled letters
     */
    public int getTotalFilledLetters() {
        return filledLetters;
    }

    /**
     * Gets the number of empty spaces on the board (not filled with dictionary words).
     * @return the count of empty spaces
     */
    public int getEmptySpaces() {
        return boardSize - filledLetters;
    }
    
    /**
     * Constructs a new Dictionary by loading words from a file.
     * Reads the dictionary file, capitalizes all words, and generates board words.
     * @param filename the name of the dictionary file to load
     * @param boardLength the length (dimension) of the game board
     */
    public Dictionary(String filename, int boardLength) {
        boardWords = new ArrayList<>();
        wordlist = new ArrayList<>();
        this.boardLength = boardLength;
        
        // Read the dictionary from disk to an ArrayList.
        Scanner s;
        try {
            s = new Scanner(new File(filename));
            wordlist = new ArrayList<>();
            while (s.hasNext()) {
                String word = Capitalize(s.next());
                wordlist.add(word);
            }
            s.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Dictionary.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Generate the Board.
        generateBoardWords();
    }
    
    /**
     * Generates a new set of words to be placed on the board.
     * Selects words from the dictionary to fill the board while leaving some empty spaces
     * for random characters.
     */
    public void generateBoardWords() {
        boardSize           =   boardLength * boardLength;
        filledLetters       =   0;
       
        int lettersToGet = lettersToGet(filledLetters);
        
        // Loop until lettersToGet() decides we should not pick
        // any other words.
        while (lettersToGet > 0) {
            
            // We request a new word.
            String nuWord = getNuWord(lettersToGet);
            
            // Add it to board list.
            boardWords.add(nuWord);
                
            // Log our changes so we won't exceed the upper limit.
            filledLetters = filledLetters + nuWord.length();
            
            // Request how many more characters we can add to 
            // the board.
            lettersToGet = lettersToGet(filledLetters);
        }
    }
    
    /**
     * Determines how many letters the next word should have.
     * Calculates based on filled letters to maintain a balance between
     * dictionary words and random characters on the board.
     * @param filledLetters the sum of characters of already added words
     * @return the number of letters for the next word, or 0 if no more words should be added
     */
    private int lettersToGet(int filledLetters) {
        
        // We want our table to be filled with at least minFilledLetters
        // and not more than maxFilledLetters so that we
        // can add some random characters afterwards (with length 
        // between minEmptyLetters and maxEmptyLetters).

        // Simple function in order to have variable length of 
        // empty characters in order with the boardlength
        // (eg. the bigger the board the more spaces should be available)
        int minEmptyLetters     = (boardLength * 5) / 6;
        int maxEmptyLetters     = ((boardLength * 5) / 6) + 3;
        int minFilledLetters    = boardSize - maxEmptyLetters;
        int maxFilledLetters    = boardSize - minEmptyLetters;
        int emptyLetters        = boardSize - filledLetters;
        
        // In the case that the numbers of letters that are already filled
        // are between the max and min value of our bounds.
        if (filledLetters > minFilledLetters && filledLetters < maxFilledLetters) {
            return 0;
        }
        // In the case that is lesser than min we want to have 
        // at least some spaces free (in fact at least minEmptyLetters
        // and maxFilledLetters at most).
        else if (filledLetters + boardLength <= maxFilledLetters) {
            return boardLength;
        }
        
        // if nothing of the above worked we return 0.
        return 0;
    }
    
    /**
     * Gets a new random word from the dictionary that hasn't been used yet.
     * Ensures the word is not longer than the specified maximum size and
     * has not been previously selected for the board.
     * @param maxSize the maximum length of characters for the word
     * @return a new word for the board
     */
    private String getNuWord(int maxSize) {
        SecureRandom random = new SecureRandom();
        ArrayList<String> candidates = new ArrayList<>();

        // Prefer unused words that fit the requested max length.
        for (String word : wordlist) {
            if (!boardWords.contains(word) && word.length() <= maxSize) {
                candidates.add(word);
            }
        }

        // If all fitting words have been used, allow reuse instead of looping forever.
        if (candidates.isEmpty()) {
            for (String word : wordlist) {
                if (word.length() <= maxSize) {
                    candidates.add(word);
                }
            }
        }

        if (candidates.isEmpty()) {
            throw new IllegalStateException("No dictionary words fit max size " + maxSize);
        }

        return candidates.get(random.nextInt(candidates.size()));
    }
    
    /**
     * Checks if a word exists in the dictionary.
     * @param wordToFind the word to search for in the dictionary
     * @return true if the word exists in the dictionary, false otherwise
     */
    public Boolean containsWord(String wordToFind) {
        for (String word : wordlist) {
            if (word.equals(wordToFind)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Capitalizes a word and removes Greek accent marks.
     * Converts all characters to uppercase and replaces toned Greek vowels
     * with their non-toned equivalents.
     * 
     * Note: The el-dictionary.txt file has been pre-normalized to remove
     * diacritics, making most of this processing redundant. However, this
     * normalization is kept as a safety measure for edge cases and to ensure
     * consistent behavior regardless of the dictionary source.
     * 
     * @param word the word to capitalize
     * @return the word fully capitalized with accent marks removed
     */
    private String Capitalize(String word) {
        // Convert all chars to uppercase
        word = word.toUpperCase();
        // Replace every occurrence of toned char
        // in the Greek Alphabet with the non-toned version.
        word = word.replaceAll("[Ά]", "Α");
        word = word.replaceAll("[Έ]", "Ε");
        word = word.replaceAll("[Ή]", "Η");
        word = word.replaceAll("[Ό]", "Ο");
        word = word.replaceAll("[Ί]", "Ι");
        word = word.replaceAll("[Ύ]", "Υ");
        word = word.replaceAll("[Ώ]", "Ω");
        word = word.replaceAll("[Ϊ]", "Ι");
        word = word.replaceAll("[Ϋ]", "Υ");
        // Normalize decomposed forms and strip any remaining combining diacritics.
        word = Normalizer.normalize(word, Normalizer.Form.NFD);
        word = word.replaceAll("\\p{M}", "");
        word = Normalizer.normalize(word, Normalizer.Form.NFC);
        return word;
    }

}
