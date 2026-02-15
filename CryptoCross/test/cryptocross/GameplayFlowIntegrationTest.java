package cryptocross;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class GameplayFlowIntegrationTest {

    @Test
    public void testHelpMutationBeforeSubmissionClearsSelectionAndReturnsEmpty() throws Exception {
        Path tempDict = Files.createTempFile("cryptocross-flow-help-before-submit", ".txt");
        try {
            Files.writeString(tempDict, "ΑΒ\n", StandardCharsets.UTF_8);

            Board board = createDeterministicBoard(tempDict.toString());
            Player player = new Player();
            HelpActionStateService helpStateService = new HelpActionStateService();
            WordSubmissionService submissionService = new WordSubmissionService();

            ArrayList<Letter> currentWord = new ArrayList<>();
            currentWord.add(board.getBoardArray()[0][0]);
            currentWord.add(board.getBoardArray()[0][1]);

            HelpActionStateService.HelpActionState state =
                    helpStateService.onBoardMutation(11, true, currentWord);

            WordSubmissionService.SubmissionResult result =
                    submissionService.evaluate(currentWord, board, player);

            assertEquals(11, state.getTotalScore());
            assertEquals(0, state.getCurrentWordPoints());
            assertFalse(state.isSwapMode());
            assertTrue(currentWord.isEmpty(), "Help mutation should clear pending word selection");
            assertEquals(WordSubmissionService.SubmissionStatus.EMPTY, result.getStatus());
            assertEquals(0, result.getPoints());
            assertEquals(0, player.getCompletedWordsNum());
        } finally {
            Files.deleteIfExists(tempDict);
        }
    }

    @Test
    public void testAcceptedSubmissionThenHelpMutationStillRejectsDuplicateWord() throws Exception {
        Path tempDict = Files.createTempFile("cryptocross-flow-duplicate-after-help", ".txt");
        try {
            Files.writeString(tempDict, "ΑΒ\n", StandardCharsets.UTF_8);

            Board board = createDeterministicBoard(tempDict.toString());
            Player player = new Player();
            HelpActionStateService helpStateService = new HelpActionStateService();
            WordSubmissionService submissionService = new WordSubmissionService();

            ArrayList<Letter> currentWord = new ArrayList<>();
            currentWord.add(board.getBoardArray()[0][0]);
            currentWord.add(board.getBoardArray()[0][1]);

            WordSubmissionService.SubmissionResult first =
                    submissionService.evaluate(currentWord, board, player);

            assertEquals(WordSubmissionService.SubmissionStatus.ACCEPTED, first.getStatus());
            assertTrue(first.getPoints() > 0);

            player.setPlayerScore(player.getPlayerScore() + first.getPoints());
            player.playerCompletedAWord();

            helpStateService.onBoardMutation(player.getPlayerScore(), false, currentWord);
            assertTrue(currentWord.isEmpty(), "Help mutation should reset current word before next selection");

            currentWord.add(board.getBoardArray()[0][0]);
            currentWord.add(board.getBoardArray()[0][1]);

            WordSubmissionService.SubmissionResult second =
                    submissionService.evaluate(currentWord, board, player);

            assertEquals(WordSubmissionService.SubmissionStatus.DUPLICATE, second.getStatus());
            assertEquals(0, second.getPoints());
            assertEquals(1, player.getCompletedWordsNum(), "Duplicate submission must not increase word counter");
        } finally {
            Files.deleteIfExists(tempDict);
        }
    }

    private Board createDeterministicBoard(String dictionaryPath) throws Exception {
        char[][] fixtureChars = {
            {'Α', 'Β', 'Γ', 'Δ', 'Ε'},
            {'Ζ', 'Η', 'Θ', 'Ι', 'Κ'},
            {'Λ', 'Μ', 'Ν', 'Ξ', 'Ο'},
            {'Π', 'Ρ', 'Σ', 'Τ', 'Υ'},
            {'Φ', 'Χ', 'Ψ', 'Ω', 'Α'}
        };

        Letter[][] fixture = new Letter[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                fixture[i][j] = new WhiteLetter(fixtureChars[i][j]);
            }
        }
        return new Board(5, dictionaryPath, fixture);
    }
}
