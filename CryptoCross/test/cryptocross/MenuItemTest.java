/*
 * Test for menu items to verify they are implemented
 */
package cryptocross;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;

/**
 * Tests to verify that menu action methods are implemented.
 */
public class MenuItemTest {

    @BeforeEach
    public void setUp() {
        // Set headless mode to test without GUI
        System.setProperty("java.awt.headless", "true");
    }

    @Test
    public void testHelpMenuItemMethodExists() {
        try {
            Method method = CryptoCross.class.getDeclaredMethod(
                "helpMenuItemActionPerformed", 
                java.awt.event.ActionEvent.class
            );
            assertNotNull(method, "helpMenuItemActionPerformed method should exist");
        } catch (NoSuchMethodException e) {
            fail("helpMenuItemActionPerformed method not found");
        }
    }

    @Test
    public void testStopGameMenuItemMethodExists() {
        try {
            Method method = CryptoCross.class.getDeclaredMethod(
                "stopGameMenuItemActionPerformed", 
                java.awt.event.ActionEvent.class
            );
            assertNotNull(method, "stopGameMenuItemActionPerformed method should exist");
        } catch (NoSuchMethodException e) {
            fail("stopGameMenuItemActionPerformed method not found");
        }
    }

    @Test
    public void testHelpSettingsMenuItemMethodExists() {
        try {
            Method method = CryptoCross.class.getDeclaredMethod(
                "helpSettingsMenuItemActionPerformed", 
                java.awt.event.ActionEvent.class
            );
            assertNotNull(method, "helpSettingsMenuItemActionPerformed method should exist");
        } catch (NoSuchMethodException e) {
            fail("helpSettingsMenuItemActionPerformed method not found");
        }
    }

    @Test
    public void testPickWordFileMenuItemMethodExists() {
        try {
            Method method = CryptoCross.class.getDeclaredMethod(
                "pickWordFileMenuItemActionPerformed", 
                java.awt.event.ActionEvent.class
            );
            assertNotNull(method, "pickWordFileMenuItemActionPerformed method should exist");
        } catch (NoSuchMethodException e) {
            fail("pickWordFileMenuItemActionPerformed method not found");
        }
    }

    @Test
    public void testAboutMenuItemMethodExists() {
        try {
            Method method = CryptoCross.class.getDeclaredMethod(
                "aboutMenuItemActionPerformed", 
                java.awt.event.ActionEvent.class
            );
            assertNotNull(method, "aboutMenuItemActionPerformed method should exist");
        } catch (NoSuchMethodException e) {
            fail("aboutMenuItemActionPerformed method not found");
        }
    }

    @Test
    public void testNewGameMenuItemMethodExists() {
        try {
            Method method = CryptoCross.class.getDeclaredMethod(
                "newGameMenuItemActionPerformed", 
                java.awt.event.ActionEvent.class
            );
            assertNotNull(method, "newGameMenuItemActionPerformed method should exist");
        } catch (NoSuchMethodException e) {
            fail("newGameMenuItemActionPerformed method not found");
        }
    }

    @Test
    public void testPlayerInfoMenuItemMethodExists() {
        try {
            Method method = CryptoCross.class.getDeclaredMethod(
                "playerInfoMenuItemActionPerformed", 
                java.awt.event.ActionEvent.class
            );
            assertNotNull(method, "playerInfoMenuItemActionPerformed method should exist");
        } catch (NoSuchMethodException e) {
            fail("playerInfoMenuItemActionPerformed method not found");
        }
    }

    @Test
    public void testExitMenuItemMethodExists() {
        try {
            Method method = CryptoCross.class.getDeclaredMethod(
                "exitMenuItemActionPerformed", 
                java.awt.event.ActionEvent.class
            );
            assertNotNull(method, "exitMenuItemActionPerformed method should exist");
        } catch (NoSuchMethodException e) {
            fail("exitMenuItemActionPerformed method not found");
        }
    }

    @Test
    public void testStrictSelectionToggleMethodExists() {
        try {
            Method method = CryptoCross.class.getDeclaredMethod(
                "setStrictSelectionMode",
                boolean.class
            );
            assertNotNull(method, "setStrictSelectionMode method should exist");
        } catch (NoSuchMethodException e) {
            fail("setStrictSelectionMode method not found");
        }
    }
}
