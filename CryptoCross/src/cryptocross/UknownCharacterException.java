/*
 * @author Charis Ioannikios Kaonis 321/2010069
 * @author Georgios Moustakas 321/2011102
 * @project CryptoCross (Κρυπτόλεξο) 2016
 */
package cryptocross;

/**
 * Exception thrown when an unknown or invalid character is encountered
 * during letter creation or processing.
 */
class UknownCharacterException extends Exception {

    /**
     * Constructs a new UknownCharacterException with a default error message
     * indicating an unknown character was encountered.
     */
    public UknownCharacterException() {
        super("Σφάλμα! Άγνωστος χαρακτήρας");
    }
    
}
