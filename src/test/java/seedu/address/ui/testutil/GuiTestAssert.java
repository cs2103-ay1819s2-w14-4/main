package seedu.address.ui.testutil;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.stream.Collectors;

import guitests.guihandles.ModuleTakenCardHandle;
import guitests.guihandles.ModuleTakenListPanelHandle;
import guitests.guihandles.ResultDisplayHandle;
import seedu.address.model.moduletaken.ModuleTaken;

/**
 * A set of assertion methods useful for writing GUI tests.
 */
public class GuiTestAssert {
    /**
     * Asserts that {@code actualCard} displays the same values as {@code expectedCard}.
     */
    public static void assertCardEquals(ModuleTakenCardHandle expectedCard, ModuleTakenCardHandle actualCard) {
        assertEquals(expectedCard.getId(), actualCard.getId());
        assertEquals(expectedCard.getExpectedMaxGrade(), actualCard.getExpectedMaxGrade());
        assertEquals(expectedCard.getExpectedMinGrade(), actualCard.getExpectedMinGrade());
        assertEquals(expectedCard.getModuleInfoCode(), actualCard.getModuleInfoCode());
        assertEquals(expectedCard.getSemester(), actualCard.getSemester());
        assertEquals(expectedCard.getTags(), actualCard.getTags());
    }

    /**
     * Asserts that {@code actualCard} displays the details of {@code expectedModuleTaken}.
     */
    public static void assertCardDisplaysModuleTaken(
            ModuleTaken expectedModuleTaken, ModuleTakenCardHandle actualCard) {
        assertEquals(expectedModuleTaken.getModuleInfoCode().toString(), actualCard.getModuleInfoCode());
        assertEquals(expectedModuleTaken.getSemester().toString(), actualCard.getSemester());
        assertEquals(expectedModuleTaken.getExpectedMinGrade().toString(), actualCard.getExpectedMinGrade());
        assertEquals(expectedModuleTaken.getExpectedMaxGrade().toString(), actualCard.getExpectedMaxGrade());
        assertEquals(expectedModuleTaken.getTags().stream().map(tag -> tag.tagName).collect(Collectors.toList()),
                actualCard.getTags());
    }

    /**
     * Asserts that the list in {@code moduleTakenListPanelHandle}
     * displays the details of {@code moduleTakens} correctly and
     * in the correct order.
     */
    public static void assertListMatching(
            ModuleTakenListPanelHandle moduleTakenListPanelHandle, ModuleTaken... moduleTakens) {
        for (int i = 0; i < moduleTakens.length; i++) {
            moduleTakenListPanelHandle.navigateToCard(i);
            assertCardDisplaysModuleTaken(moduleTakens[i], moduleTakenListPanelHandle.getModuleTakenCardHandle(i));
        }
    }

    /**
     * Asserts that the list in {@code moduleTakenListPanelHandle}
     * displays the details of {@code moduleTakens} correctly and
     * in the correct order.
     */
    public static void assertListMatching(
            ModuleTakenListPanelHandle moduleTakenListPanelHandle, List<ModuleTaken> moduleTakens) {
        assertListMatching(moduleTakenListPanelHandle, moduleTakens.toArray(new ModuleTaken[0]));
    }

    /**
     * Asserts the size of the list in {@code moduleTakenListPanelHandle} equals to {@code size}.
     */
    public static void assertListSize(ModuleTakenListPanelHandle moduleTakenListPanelHandle, int size) {
        int numberOfPeople = moduleTakenListPanelHandle.getListSize();
        assertEquals(size, numberOfPeople);
    }

    /**
     * Asserts the message shown in {@code resultDisplayHandle} equals to {@code expected}.
     */
    public static void assertResultMessage(ResultDisplayHandle resultDisplayHandle, String expected) {
        assertEquals(expected, resultDisplayHandle.getText());
    }
}
