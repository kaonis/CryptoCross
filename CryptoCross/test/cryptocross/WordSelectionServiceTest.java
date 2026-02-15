package cryptocross;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class WordSelectionServiceTest {
    private Letter createLetter(char c, int x, int y) throws UknownCharacterException {
        Letter letter = new WhiteLetter(c);
        letter.setCoords(x, y);
        return letter;
    }

    @Test
    public void testCanSelectNextAllowsFirstSelection() throws Exception {
        WordSelectionService service = new WordSelectionService();
        ArrayList<Letter> currentWord = new ArrayList<>();

        assertTrue(service.canSelectNext(currentWord, createLetter('Α', 3, 3)));
    }

    @Test
    public void testCanSelectNextAllowsAdjacentSelection() throws Exception {
        WordSelectionService service = new WordSelectionService();
        ArrayList<Letter> currentWord = new ArrayList<>();
        currentWord.add(createLetter('Α', 3, 3));

        assertTrue(service.canSelectNext(currentWord, createLetter('Β', 4, 4)));
    }

    @Test
    public void testCanSelectNextRejectsNonAdjacentSelection() throws Exception {
        WordSelectionService service = new WordSelectionService();
        ArrayList<Letter> currentWord = new ArrayList<>();
        currentWord.add(createLetter('Α', 3, 3));

        assertFalse(service.canSelectNext(currentWord, createLetter('Β', 3, 5)));
    }

    @Test
    public void testCanDeselectAllowsAnySelectedLetterWhenStrictModeDisabled() throws Exception {
        WordSelectionService service = new WordSelectionService();
        ArrayList<Letter> currentWord = new ArrayList<>();
        Letter first = createLetter('Α', 0, 0);
        Letter second = createLetter('Β', 0, 1);
        Letter third = createLetter('Γ', 0, 2);
        currentWord.add(first);
        currentWord.add(second);
        currentWord.add(third);

        assertTrue(service.canDeselect(currentWord, second));
    }

    @Test
    public void testCanDeselectRejectsNonTerminalWhenStrictModeEnabled() throws Exception {
        WordSelectionService service = new WordSelectionService(true);
        ArrayList<Letter> currentWord = new ArrayList<>();
        Letter first = createLetter('Α', 0, 0);
        Letter second = createLetter('Β', 0, 1);
        Letter third = createLetter('Γ', 0, 2);
        currentWord.add(first);
        currentWord.add(second);
        currentWord.add(third);

        assertFalse(service.canDeselect(currentWord, second));
    }

    @Test
    public void testCanDeselectAllowsOnlyLastLetterWhenStrictModeEnabled() throws Exception {
        WordSelectionService service = new WordSelectionService();
        service.setStrictSelectionMode(true);
        ArrayList<Letter> currentWord = new ArrayList<>();
        Letter first = createLetter('Α', 0, 0);
        Letter second = createLetter('Β', 0, 1);
        currentWord.add(first);
        currentWord.add(second);

        assertTrue(service.canDeselect(currentWord, second));
    }
}
