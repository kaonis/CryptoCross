package cryptocross;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class DictionarySwitchIntegrationTest {

    @Test
    public void testDictionarySwitchValidationAndFirstSubmissionOnEightByEightBoard() throws Exception {
        Path tempDict = Files.createTempFile("cryptocross-dict-switch-8x8", ".txt");
        try {
            Files.writeString(tempDict, "ΑΒΓΔ\nΛΜΝΞ\n", StandardCharsets.UTF_8);

            CryptoCross.validateDictionaryFileForBoardSize(tempDict.toString(), 8);

            Board board = createDeterministicBoard(tempDict.toString());
            Player player = new Player();
            WordSubmissionService submissionService = new WordSubmissionService();

            ArrayList<Letter> currentWord = new ArrayList<>();
            currentWord.add(board.getBoardArray()[0][0]);
            currentWord.add(board.getBoardArray()[0][1]);
            currentWord.add(board.getBoardArray()[0][2]);
            currentWord.add(board.getBoardArray()[0][3]);

            WordSubmissionService.SubmissionResult result =
                    submissionService.evaluate(currentWord, board, player);

            assertEquals(WordSubmissionService.SubmissionStatus.ACCEPTED, result.getStatus());
            assertTrue(result.getPoints() > 0);
        } finally {
            Files.deleteIfExists(tempDict);
        }
    }

    private Board createDeterministicBoard(String dictionaryPath) throws Exception {
        char[][] fixtureChars = {
            {'Α', 'Β', 'Γ', 'Δ', 'Ε', 'Ζ', 'Η', 'Θ'},
            {'Ι', 'Κ', 'Λ', 'Μ', 'Ν', 'Ξ', 'Ο', 'Π'},
            {'Ρ', 'Σ', 'Τ', 'Υ', 'Φ', 'Χ', 'Ψ', 'Ω'},
            {'Α', 'Β', 'Γ', 'Δ', 'Ε', 'Ζ', 'Η', 'Θ'},
            {'Ι', 'Κ', 'Λ', 'Μ', 'Ν', 'Ξ', 'Ο', 'Π'},
            {'Ρ', 'Σ', 'Τ', 'Υ', 'Φ', 'Χ', 'Ψ', 'Ω'},
            {'Α', 'Β', 'Γ', 'Δ', 'Ε', 'Ζ', 'Η', 'Θ'},
            {'Ι', 'Κ', 'Λ', 'Μ', 'Ν', 'Ξ', 'Ο', 'Π'}
        };

        Letter[][] fixture = new Letter[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                fixture[i][j] = new WhiteLetter(fixtureChars[i][j]);
            }
        }

        return new Board(8, dictionaryPath, fixture);
    }
}
