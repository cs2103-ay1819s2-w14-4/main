package seedu.address.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.ui.testutil.GuiTestAssert.assertCardDisplaysModuleTaken;

import org.junit.Test;

import guitests.guihandles.ModuleTakenCardHandle;
import seedu.address.model.moduletaken.ModuleTaken;
import seedu.address.testutil.ModuleTakenBuilder;

public class ModuleTakenCardTest extends GuiUnitTest {

    @Test
    public void display() {
        // no tags
        ModuleTaken moduleTakenWithNoTags = new ModuleTakenBuilder().withTags(new String[0]).build();
        ModuleTakenCard moduleTakenCard = new ModuleTakenCard(moduleTakenWithNoTags, 1);
        uiPartRule.setUiPart(moduleTakenCard);
        assertCardDisplay(moduleTakenCard, moduleTakenWithNoTags, 1);

        // with tags
        ModuleTaken moduleTakenWithTags = new ModuleTakenBuilder().build();
        moduleTakenCard = new ModuleTakenCard(moduleTakenWithTags, 2);
        uiPartRule.setUiPart(moduleTakenCard);
        assertCardDisplay(moduleTakenCard, moduleTakenWithTags, 2);
    }

    @Test
    public void equals() {
        ModuleTaken moduleTaken = new ModuleTakenBuilder().build();
        ModuleTakenCard moduleTakenCard = new ModuleTakenCard(moduleTaken, 0);

        // same moduleTaken, same index -> returns true
        ModuleTakenCard copy = new ModuleTakenCard(moduleTaken, 0);
        assertTrue(moduleTakenCard.equals(copy));

        // same object -> returns true
        assertTrue(moduleTakenCard.equals(moduleTakenCard));

        // null -> returns false
        assertFalse(moduleTakenCard.equals(null));

        // different types -> returns false
        assertFalse(moduleTakenCard.equals(0));

        // different moduleTaken, same index -> returns false
        ModuleTaken differentModuleTaken = new ModuleTakenBuilder().withModuleInfoCode("CS2101").build();
        assertFalse(moduleTakenCard.equals(new ModuleTakenCard(differentModuleTaken, 0)));

        // same moduleTaken, different index -> returns false
        assertFalse(moduleTakenCard.equals(new ModuleTakenCard(moduleTaken, 1)));
    }

    /**
     * Asserts that {@code moduleTakenCard} displays the details of {@code expectedModuleTaken} correctly and matches
     * {@code expectedId}.
     */
    private void assertCardDisplay(ModuleTakenCard moduleTakenCard, ModuleTaken expectedModuleTaken, int expectedId) {
        guiRobot.pauseForHuman();

        ModuleTakenCardHandle moduleTakenCardHandle = new ModuleTakenCardHandle(moduleTakenCard.getRoot());

        // verify id is displayed correctly
        assertEquals(Integer.toString(expectedId) + ". ", moduleTakenCardHandle.getId());

        // verify moduleTaken details are displayed correctly
        assertCardDisplaysModuleTaken(expectedModuleTaken, moduleTakenCardHandle);
    }
}
