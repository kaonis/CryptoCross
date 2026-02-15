package cryptocross;

import java.util.prefs.Preferences;

/**
 * Persists strict selection mode preference between app runs.
 */
public class StrictSelectionPreferenceService {
    private static final String PREF_KEY_STRICT_SELECTION_MODE = "strict.selection.mode.enabled";
    private static final boolean DEFAULT_STRICT_SELECTION_MODE = false;

    private final Preferences preferences;

    public StrictSelectionPreferenceService() {
        this(Preferences.userNodeForPackage(CryptoCross.class));
    }

    StrictSelectionPreferenceService(Preferences preferences) {
        this.preferences = preferences;
    }

    public boolean loadStrictSelectionMode() {
        return preferences.getBoolean(PREF_KEY_STRICT_SELECTION_MODE, DEFAULT_STRICT_SELECTION_MODE);
    }

    public void saveStrictSelectionMode(boolean enabled) {
        preferences.putBoolean(PREF_KEY_STRICT_SELECTION_MODE, enabled);
    }
}
