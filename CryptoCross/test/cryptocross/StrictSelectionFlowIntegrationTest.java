package cryptocross;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class StrictSelectionFlowIntegrationTest {

    @Test
    public void testNonTerminalDeselectIsBlockedOnlyInStrictMode() throws Exception {
        Letter letterA = createLetter('Α', 0, 0);
        Letter letterB = createLetter('Β', 0, 1);
        Letter letterC = createLetter('Γ', 0, 2);

        ArrayList<Letter> nonStrictWord = new ArrayList<>();
        nonStrictWord.add(letterA);
        nonStrictWord.add(letterB);
        nonStrictWord.add(letterC);
        WordSelectionService nonStrictService = new WordSelectionService(false);

        boolean canDeselectInNonStrict = nonStrictService.canDeselect(nonStrictWord, letterB);
        assertTrue(canDeselectInNonStrict, "Non-strict mode should allow deselecting non-terminal letters");
        if (canDeselectInNonStrict) {
            nonStrictWord.remove(letterB);
        }
        assertEquals(2, nonStrictWord.size());
        assertFalse(nonStrictWord.contains(letterB));

        ArrayList<Letter> strictWord = new ArrayList<>();
        strictWord.add(letterA);
        strictWord.add(letterB);
        strictWord.add(letterC);
        WordSelectionService strictService = new WordSelectionService(true);

        boolean canDeselectInStrict = strictService.canDeselect(strictWord, letterB);
        assertFalse(canDeselectInStrict, "Strict mode should block deselecting non-terminal letters");
        if (canDeselectInStrict) {
            strictWord.remove(letterB);
        }
        assertEquals(3, strictWord.size(), "Blocked deselection should leave selection unchanged");

        boolean canDeselectLastInStrict = strictService.canDeselect(strictWord, letterC);
        assertTrue(canDeselectLastInStrict, "Strict mode should still allow deselecting last selected letter");
        if (canDeselectLastInStrict) {
            strictWord.remove(letterC);
        }
        assertEquals(2, strictWord.size());
        assertFalse(strictWord.contains(letterC));
    }

    private Letter createLetter(char c, int x, int y) throws UknownCharacterException {
        Letter letter = new WhiteLetter(c);
        letter.setCoords(x, y);
        return letter;
    }
}
