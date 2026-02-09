package cryptocross;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * Integration test to verify that HTML messages are properly loaded from resource file
 * and can be formatted as they would be in the actual UI dialogs.
 */
public class HtmlMessageIntegrationTest {

    private static final ResourceBundle messages = ResourceBundle.getBundle("cryptocross.CryptoCrossMessages");

    @Test
    public void testHelpSettingsDialogMessage() {
        // Simulate the actual values used in the UI
        Integer int_UsedDeleteRow = 1;
        Integer int_TotalDeleteRow = 3;
        Integer int_UsedReorderRow = 0;
        Integer int_TotalReorderRow = 3;
        Integer int_UsedReorderColumn = 2;
        Integer int_TotalReorderColumn = 3;
        Integer int_UsedReorderBoard = 1;
        Integer int_TotalReorderBoard = 5;
        Integer int_UsedSwapLetter = 3;
        Integer int_TotalSwapLetter = 6;

        // This is exactly how the code formats the message in CryptoCross.java
        String settingsText = MessageFormat.format(
                messages.getString("help.settings.html"),
                int_UsedDeleteRow,
                (int_TotalDeleteRow - int_UsedDeleteRow),
                int_TotalDeleteRow,
                int_UsedReorderRow,
                (int_TotalReorderRow - int_UsedReorderRow),
                int_TotalReorderRow,
                int_UsedReorderColumn,
                (int_TotalReorderColumn - int_UsedReorderColumn),
                int_TotalReorderColumn,
                int_UsedReorderBoard,
                (int_TotalReorderBoard - int_UsedReorderBoard),
                int_TotalReorderBoard,
                int_UsedSwapLetter,
                (int_TotalSwapLetter - int_UsedSwapLetter),
                int_TotalSwapLetter
        );

        // Verify the message was formatted correctly
        assertNotNull(settingsText);
        assertTrue(settingsText.contains("<html>"));
        assertTrue(settingsText.contains("Ρυθμίσεις Βοηθειών"));
        assertTrue(settingsText.contains("1")); // Used delete row
        assertTrue(settingsText.contains("2")); // Available delete row
        assertTrue(settingsText.contains("3")); // Total values
        assertFalse(settingsText.contains("{0}")); // No unformatted placeholders
    }

    @Test
    public void testFileChooserDialogMessage() {
        // This is exactly how the code loads the message in CryptoCross.java
        String fileChooserText = messages.getString("file.chooser.format.html");

        // Verify the message was loaded correctly
        assertNotNull(fileChooserText);
        assertTrue(fileChooserText.contains("<html>"));
        assertTrue(fileChooserText.contains("Υποστηριζόμενη Μορφή"));
        assertTrue(fileChooserText.contains(".txt"));
        assertTrue(fileChooserText.contains("Ελληνικά κεφαλαία"));
    }

    @Test
    public void testHelpMainDialogMessage() {
        // Simulate the actual values used in the UI
        Integer int_TotalDeleteRow = 3;
        Integer int_TotalReorderRow = 3;
        Integer int_TotalReorderColumn = 3;
        Integer int_TotalReorderBoard = 5;
        Integer int_TotalSwapLetter = 6;

        // This is exactly how the code formats the message in CryptoCross.java
        String helpText = MessageFormat.format(
                messages.getString("help.main.html"),
                int_TotalDeleteRow,
                int_TotalReorderRow,
                int_TotalReorderColumn,
                int_TotalReorderBoard,
                int_TotalSwapLetter
        );

        // Verify the message was formatted correctly
        assertNotNull(helpText);
        assertTrue(helpText.contains("<html>"));
        assertTrue(helpText.contains("Οδηγός Παιχνιδιού"));
        assertTrue(helpText.contains("CryptoCross"));
        assertTrue(helpText.contains("Κρυπτόλεξο"));
        assertTrue(helpText.contains("3 χρήσεις")); // Help action counts
        assertTrue(helpText.contains("5 χρήσεις")); // Reorder board
        assertTrue(helpText.contains("6 χρήσεις")); // Swap letter
        assertFalse(helpText.contains("{0}")); // No unformatted placeholders
        
        // Verify key sections are present
        assertTrue(helpText.contains("Σκοπός του Παιχνιδιού"));
        assertTrue(helpText.contains("Πώς να Παίξετε"));
        assertTrue(helpText.contains("Χρώματα Γραμμάτων"));
        assertTrue(helpText.contains("Βαθμολογία Γραμμάτων"));
        assertTrue(helpText.contains("Βοηθήματα"));
        assertTrue(helpText.contains("Κανόνες"));
        assertTrue(helpText.contains("Συμβουλές"));
    }

    @Test
    public void testAllMessagesAreProperlyEncoded() {
        // Verify that Greek characters are properly encoded in all messages
        String[] keys = {"help.settings.html", "file.chooser.format.html", "help.main.html"};
        
        for (String key : keys) {
            String message = messages.getString(key);
            assertNotNull(message, "Message for key " + key + " should not be null");
            
            // Check that Greek characters are present (not garbled)
            // These characters should appear in their proper Unicode form
            boolean hasGreekChars = message.contains("Α") || message.contains("Ο") || 
                                   message.contains("Ε") || message.contains("Ι");
            assertTrue(hasGreekChars, "Message should contain Greek characters: " + key);
        }
    }
}
