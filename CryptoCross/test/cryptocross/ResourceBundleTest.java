package cryptocross;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ResourceBundle;
import java.text.MessageFormat;

/**
 * Test class for verifying resource bundle loading and HTML message strings.
 */
public class ResourceBundleTest {

    @Test
    public void testResourceBundleExists() {
        ResourceBundle messages = ResourceBundle.getBundle("cryptocross.CryptoCrossMessages");
        assertNotNull(messages, "Resource bundle should be loaded");
    }

    @Test
    public void testHelpSettingsHtmlExists() {
        ResourceBundle messages = ResourceBundle.getBundle("cryptocross.CryptoCrossMessages");
        String helpSettings = messages.getString("help.settings.html");
        assertNotNull(helpSettings, "Help settings HTML should exist");
        assertTrue(helpSettings.contains("<html>"), "Should contain HTML tags");
        assertTrue(helpSettings.contains("Ρυθμίσεις Βοηθειών"), "Should contain Greek text");
    }

    @Test
    public void testFileChooserFormatHtmlExists() {
        ResourceBundle messages = ResourceBundle.getBundle("cryptocross.CryptoCrossMessages");
        String fileChooserFormat = messages.getString("file.chooser.format.html");
        assertNotNull(fileChooserFormat, "File chooser format HTML should exist");
        assertTrue(fileChooserFormat.contains("<html>"), "Should contain HTML tags");
        assertTrue(fileChooserFormat.contains("Υποστηριζόμενη Μορφή"), "Should contain Greek text");
    }

    @Test
    public void testHelpMainHtmlExists() {
        ResourceBundle messages = ResourceBundle.getBundle("cryptocross.CryptoCrossMessages");
        String helpMain = messages.getString("help.main.html");
        assertNotNull(helpMain, "Main help HTML should exist");
        assertTrue(helpMain.contains("<html>"), "Should contain HTML tags");
        assertTrue(helpMain.contains("Οδηγός Παιχνιδιού"), "Should contain Greek text");
    }

    @Test
    public void testMessageFormatWithParameters() {
        ResourceBundle messages = ResourceBundle.getBundle("cryptocross.CryptoCrossMessages");
        String helpSettings = messages.getString("help.settings.html");
        
        // Test that we can format the message with parameters
        String formatted = MessageFormat.format(helpSettings, 
            0, 3, 3,  // Delete row: used, available, total
            0, 3, 3,  // Reorder row: used, available, total
            0, 3, 3,  // Reorder column: used, available, total
            0, 5, 5,  // Reorder board: used, available, total
            0, 6, 6   // Swap letter: used, available, total
        );
        
        assertNotNull(formatted, "Formatted message should not be null");
        assertTrue(formatted.contains("0"), "Should contain formatted numbers");
        assertFalse(formatted.contains("{0}"), "Should not contain unformatted placeholders");
    }

    @Test
    public void testHelpMainMessageFormatting() {
        ResourceBundle messages = ResourceBundle.getBundle("cryptocross.CryptoCrossMessages");
        String helpMain = messages.getString("help.main.html");
        
        // Test that we can format the message with parameters for help actions
        String formatted = MessageFormat.format(helpMain, 3, 3, 3, 5, 6);
        
        assertNotNull(formatted, "Formatted message should not be null");
        assertTrue(formatted.contains("3"), "Should contain formatted numbers");
        assertFalse(formatted.contains("{0}"), "Should not contain unformatted placeholders");
    }
}
