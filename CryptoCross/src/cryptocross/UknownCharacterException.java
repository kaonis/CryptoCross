/*
 * @author Charis Ioannikios Kaonis 321/2010069
 * @author Georgios Moustakas 321/2011102
 * @project CryptoCross (Κρυπτόλεξο) 2016
 */
package cryptocross;


class UknownCharacterException extends Exception {

    public UknownCharacterException() {
        super("Σφάλμα! Άγνωστος χαρακτήρας");
    }
    
}
