package cryptocross;

import java.util.ArrayList;

/**
 * Encapsulates adjacency checks for incremental letter selection.
 */
public class WordSelectionService {
    private boolean strictSelectionMode;

    public WordSelectionService() {
        this(false);
    }

    public WordSelectionService(boolean strictSelectionMode) {
        this.strictSelectionMode = strictSelectionMode;
    }

    public void setStrictSelectionMode(boolean strictSelectionMode) {
        this.strictSelectionMode = strictSelectionMode;
    }

    public boolean canSelectNext(ArrayList<Letter> currentWord, Letter candidate) {
        if (candidate == null) {
            return false;
        }

        if (currentWord == null || currentWord.isEmpty()) {
            return true;
        }

        Letter previous = currentWord.get(currentWord.size() - 1);
        if (previous == null
                || previous.getXcoord() == null
                || previous.getYcoord() == null
                || candidate.getXcoord() == null
                || candidate.getYcoord() == null) {
            return false;
        }

        WordPilot wordPilot = new WordPilot(null);
        return wordPilot.isNeighbour(
                previous.getXcoord(),
                previous.getYcoord(),
                candidate.getXcoord(),
                candidate.getYcoord());
    }

    public boolean canDeselect(ArrayList<Letter> currentWord, Letter candidate) {
        if (candidate == null) {
            return false;
        }

        if (currentWord == null || currentWord.isEmpty()) {
            return true;
        }

        if (!strictSelectionMode) {
            return true;
        }

        Letter lastSelected = currentWord.get(currentWord.size() - 1);
        return candidate == lastSelected;
    }
}
