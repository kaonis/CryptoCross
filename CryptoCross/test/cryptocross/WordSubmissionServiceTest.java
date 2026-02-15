package cryptocross;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class WordSubmissionServiceTest {
    private ArrayList<Letter> buildWord(char first, int x1, int y1, char second, int x2, int y2)
            throws UknownCharacterException {
        ArrayList<Letter> word = new ArrayList<>();
        Letter letter1 = new WhiteLetter(first);
        letter1.setCoords(x1, y1);
        Letter letter2 = new WhiteLetter(second);
        letter2.setCoords(x2, y2);
        word.add(letter1);
        word.add(letter2);
        return word;
    }

    @Test
    public void testEvaluateReturnsEmptyForNoSelection() throws Exception {
        WordSubmissionService service = new WordSubmissionService();
        Board board = new Board(5, "custom-test-dictionary.txt");
        Player player = new Player();

        WordSubmissionService.SubmissionResult result =
            service.evaluate(new ArrayList<>(), board, player);

        assertEquals(WordSubmissionService.SubmissionStatus.EMPTY, result.getStatus());
        assertEquals(0, result.getPoints());
    }

    @Test
    public void testEvaluateReturnsInvalidForUnknownWord() throws Exception {
        Path tempDict = Files.createTempFile("cryptocross-submission-invalid", ".txt");
        try {
            Files.writeString(tempDict, "ΑΒ\n", StandardCharsets.UTF_8);
            WordSubmissionService service = new WordSubmissionService();
            Board board = new Board(5, tempDict.toString());
            Player player = new Player();
            ArrayList<Letter> word = buildWord('Α', 0, 0, 'Γ', 0, 1);

            WordSubmissionService.SubmissionResult result = service.evaluate(word, board, player);

            assertEquals(WordSubmissionService.SubmissionStatus.INVALID, result.getStatus());
            assertEquals(0, result.getPoints());
        } finally {
            Files.deleteIfExists(tempDict);
        }
    }

    @Test
    public void testEvaluateAcceptsThenRejectsDuplicateWord() throws Exception {
        Path tempDict = Files.createTempFile("cryptocross-submission-duplicate", ".txt");
        try {
            Files.writeString(tempDict, "ΑΒ\n", StandardCharsets.UTF_8);
            WordSubmissionService service = new WordSubmissionService();
            Board board = new Board(5, tempDict.toString());
            Player player = new Player();
            ArrayList<Letter> word = buildWord('Α', 0, 0, 'Β', 0, 1);

            WordSubmissionService.SubmissionResult first = service.evaluate(word, board, player);
            WordSubmissionService.SubmissionResult second = service.evaluate(word, board, player);

            assertEquals(WordSubmissionService.SubmissionStatus.ACCEPTED, first.getStatus());
            assertTrue(first.getPoints() > 0);
            assertEquals(WordSubmissionService.SubmissionStatus.DUPLICATE, second.getStatus());
            assertEquals(0, second.getPoints());
        } finally {
            Files.deleteIfExists(tempDict);
        }
    }
}
