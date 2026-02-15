package cryptocross;

import java.util.ArrayList;

/**
 * Encapsulates adjacency checks for incremental letter selection.
 */
public class WordSelectionService {
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
}
