package cryptocross;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import org.junit.jupiter.api.Test;

public class StrictSelectionStartupIntegrationTest {

    @Test
    public void testStartupDefaultsToStrictModeOffWhenPreferenceUnset() {
        Preferences node = createTestNode();
        try {
            StrictSelectionPreferenceService startupService =
                    new StrictSelectionPreferenceService(node);

            assertFalse(startupService.loadStrictSelectionMode());
        } finally {
            cleanup(node);
        }
    }

    @Test
    public void testStartupLoadsPreviouslyPersistedStrictModeValue() {
        Preferences node = createTestNode();
        try {
            StrictSelectionPreferenceService firstRunService =
                    new StrictSelectionPreferenceService(node);
            firstRunService.saveStrictSelectionMode(true);

            // Simulate a new app run by creating a new service instance.
            StrictSelectionPreferenceService secondRunService =
                    new StrictSelectionPreferenceService(node);

            assertTrue(secondRunService.loadStrictSelectionMode());
        } finally {
            cleanup(node);
        }
    }

    private Preferences createTestNode() {
        return Preferences.userRoot().node("cryptocross/test/startup/" + UUID.randomUUID());
    }

    private void cleanup(Preferences node) {
        try {
            node.removeNode();
        } catch (BackingStoreException ignored) {
            // No-op cleanup path for restricted environments.
        }
    }
}
