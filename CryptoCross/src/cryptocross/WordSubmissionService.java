package cryptocross;

import java.util.ArrayList;

/**
 * Encapsulates non-UI word submission decisions for the game flow.
 */
public class WordSubmissionService {
    public enum SubmissionStatus {
        EMPTY,
        INVALID,
        DUPLICATE,
        ACCEPTED
    }

    public static class SubmissionResult {
        private final SubmissionStatus status;
        private final int points;

        public SubmissionResult(SubmissionStatus status, int points) {
            this.status = status;
            this.points = points;
        }

        public SubmissionStatus getStatus() {
            return status;
        }

        public int getPoints() {
            return points;
        }
    }

    public SubmissionResult evaluate(ArrayList<Letter> currentWord, Board gameBoard, Player player) {
        if (currentWord == null || currentWord.isEmpty()) {
            return new SubmissionResult(SubmissionStatus.EMPTY, 0);
        }

        if (gameBoard == null || player == null) {
            return new SubmissionResult(SubmissionStatus.INVALID, 0);
        }

        if (!gameBoard.checkWordValidity(currentWord)) {
            return new SubmissionResult(SubmissionStatus.INVALID, 0);
        }

        String completedWord = buildWordString(currentWord);
        if (!player.registerCompletedWord(completedWord)) {
            return new SubmissionResult(SubmissionStatus.DUPLICATE, 0);
        }

        return new SubmissionResult(SubmissionStatus.ACCEPTED, calculateWordPoints(currentWord));
    }

    private String buildWordString(ArrayList<Letter> word) {
        StringBuilder wordBuilder = new StringBuilder();
        for (Letter letter : word) {
            wordBuilder.append(letter.getLetterChar());
        }
        return wordBuilder.toString();
    }

    private int calculateWordPoints(ArrayList<Letter> word) {
        int points = 0;
        for (Letter letter : word) {
            points += letter.getPoints();
        }
        return points;
    }
}
