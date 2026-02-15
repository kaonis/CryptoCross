package cryptocross;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import org.junit.jupiter.api.Test;

public class StrictSelectionPreferenceServiceTest {

    @Test
    public void testLoadDefaultsToFalseWhenUnset() {
        Preferences node = createTestNode();
        try {
            StrictSelectionPreferenceService service = new StrictSelectionPreferenceService(node);
            assertFalse(service.loadStrictSelectionMode());
        } finally {
            cleanup(node);
        }
    }

    @Test
    public void testSaveThenLoadTrue() {
        Preferences node = createTestNode();
        try {
            StrictSelectionPreferenceService service = new StrictSelectionPreferenceService(node);
            service.saveStrictSelectionMode(true);
            assertTrue(service.loadStrictSelectionMode());
        } finally {
            cleanup(node);
        }
    }

    @Test
    public void testSaveThenLoadFalse() {
        Preferences node = createTestNode();
        try {
            StrictSelectionPreferenceService service = new StrictSelectionPreferenceService(node);
            service.saveStrictSelectionMode(true);
            service.saveStrictSelectionMode(false);
            assertFalse(service.loadStrictSelectionMode());
        } finally {
            cleanup(node);
        }
    }

    private Preferences createTestNode() {
        return Preferences.userRoot().node("cryptocross/test/" + UUID.randomUUID());
    }

    private void cleanup(Preferences node) {
        try {
            node.removeNode();
        } catch (BackingStoreException ignored) {
            // No-op cleanup path for environments that restrict preference writes.
        }
    }
}
