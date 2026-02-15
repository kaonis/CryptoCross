package cryptocross;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class StrictSelectionToggleFlowHarnessTest {

    @Test
    public void testToggleStrictModeChangesDeselectBehaviorInActiveFlow() throws Exception {
        WordSelectionService service = new WordSelectionService();

        Letter letterA = createLetter('Α', 0, 0);
        Letter letterB = createLetter('Β', 0, 1);
        Letter letterC = createLetter('Γ', 0, 2);

        ArrayList<Letter> currentWord = new ArrayList<>();
        currentWord.add(letterA);
        currentWord.add(letterB);
        currentWord.add(letterC);

        // Baseline: non-strict mode allows deselecting non-terminal letters.
        assertTrue(service.canDeselect(currentWord, letterB));

        // Toggle strict mode on: non-terminal deselection should now be blocked.
        service.setStrictSelectionMode(true);
        assertFalse(service.canDeselect(currentWord, letterB));
        assertTrue(service.canDeselect(currentWord, letterC));

        // Toggle strict mode off again: non-terminal deselection should be allowed again.
        service.setStrictSelectionMode(false);
        assertTrue(service.canDeselect(currentWord, letterB));
    }

    private Letter createLetter(char c, int x, int y) throws UknownCharacterException {
        Letter letter = new WhiteLetter(c);
        letter.setCoords(x, y);
        return letter;
    }
}
