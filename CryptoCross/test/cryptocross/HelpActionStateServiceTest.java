package cryptocross;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class HelpActionStateServiceTest {
    private Letter createLetter(char c, int x, int y) throws UknownCharacterException {
        Letter letter = new WhiteLetter(c);
        letter.setCoords(x, y);
        return letter;
    }

    @Test
    public void testOnBoardMutationResetsCurrentWordStateForRowOperationContext() throws Exception {
        HelpActionStateService service = new HelpActionStateService();
        ArrayList<Letter> currentWord = new ArrayList<>();
        currentWord.add(createLetter('Α', 0, 0));
        currentWord.add(createLetter('Β', 0, 1));

        HelpActionStateService.HelpActionState state = service.onBoardMutation(42, false, currentWord);

        assertEquals(42, state.getTotalScore());
        assertEquals(0, state.getCurrentWordPoints());
        assertFalse(state.isSwapMode());
        assertEquals(0, currentWord.size(), "Selected letters should be cleared after board mutation");
    }

    @Test
    public void testOnBoardMutationDisablesSwapModeForSwapOperationContext() {
        HelpActionStateService service = new HelpActionStateService();
        ArrayList<Letter> currentWord = new ArrayList<>();

        HelpActionStateService.HelpActionState state = service.onBoardMutation(17, true, currentWord);

        assertEquals(17, state.getTotalScore());
        assertEquals(0, state.getCurrentWordPoints());
        assertFalse(state.isSwapMode(), "Swap mode should be disabled after board mutation");
    }
}
