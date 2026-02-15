package cryptocross;

import java.util.ArrayList;

/**
 * Encapsulates state updates after board-mutating help actions.
 */
public class HelpActionStateService {
    public static class HelpActionState {
        private final int totalScore;
        private final int currentWordPoints;
        private final boolean swapMode;

        public HelpActionState(int totalScore, int currentWordPoints, boolean swapMode) {
            this.totalScore = totalScore;
            this.currentWordPoints = currentWordPoints;
            this.swapMode = swapMode;
        }

        public int getTotalScore() {
            return totalScore;
        }

        public int getCurrentWordPoints() {
            return currentWordPoints;
        }

        public boolean isSwapMode() {
            return swapMode;
        }
    }

    public HelpActionState onBoardMutation(int totalScore, boolean swapMode, ArrayList<Letter> currentWord) {
        if (currentWord != null) {
            currentWord.clear();
        }
        return new HelpActionState(totalScore, 0, false);
    }
}
