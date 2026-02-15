package cryptocross;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class WordPilotTest {
    @Test
    public void testIsNeighbourReturnsTrueForAllEightDirections() {
        WordPilot pilot = new WordPilot(new Letter[0][0]);

        assertTrue(pilot.isNeighbour(5, 5, 4, 4));
        assertTrue(pilot.isNeighbour(5, 5, 5, 4));
        assertTrue(pilot.isNeighbour(5, 5, 6, 4));
        assertTrue(pilot.isNeighbour(5, 5, 4, 5));
        assertTrue(pilot.isNeighbour(5, 5, 6, 5));
        assertTrue(pilot.isNeighbour(5, 5, 4, 6));
        assertTrue(pilot.isNeighbour(5, 5, 5, 6));
        assertTrue(pilot.isNeighbour(5, 5, 6, 6));
    }

    @Test
    public void testIsNeighbourReturnsFalseForSameCellAndDistantCells() {
        WordPilot pilot = new WordPilot(new Letter[0][0]);

        assertFalse(pilot.isNeighbour(5, 5, 5, 5));
        assertFalse(pilot.isNeighbour(5, 5, 7, 5));
        assertFalse(pilot.isNeighbour(5, 5, 5, 7));
        assertFalse(pilot.isNeighbour(5, 5, 7, 7));
    }
}
