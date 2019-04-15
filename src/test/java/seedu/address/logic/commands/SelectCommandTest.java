package seedu.address.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showModuleTakenAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE_TAKEN;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MODULE_TAKEN;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_MODULE_TAKEN;
import static seedu.address.testutil.TypicalModuleTaken.getTypicalGradTrak;

import org.junit.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserInfo;
import seedu.address.model.UserPrefs;
import seedu.address.model.course.CourseList;
import seedu.address.model.moduleinfo.ModuleInfoList;

/**
 * Contains integration tests (interaction with the Model) for {@code SelectCommand}.
 */
public class SelectCommandTest {
    private Model model = new ModelManager(getTypicalGradTrak(), new UserPrefs(),
            new ModuleInfoList(), new CourseList(), new UserInfo());
    private Model expectedModel = new ModelManager(getTypicalGradTrak(), new UserPrefs(),
            new ModuleInfoList(), new CourseList(), new UserInfo());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Index lastPersonIndex = Index.fromOneBased(model.getFilteredModulesTakenList().size());

        assertExecutionSuccess(INDEX_FIRST_MODULE_TAKEN);
        assertExecutionSuccess(INDEX_THIRD_MODULE_TAKEN);
        assertExecutionSuccess(lastPersonIndex);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_failure() {
        Index outOfBoundsIndex = Index.fromOneBased(model.getFilteredModulesTakenList().size() + 1);

        assertExecutionFailure(outOfBoundsIndex, Messages.MESSAGE_INVALID_MODULETAKEN_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showModuleTakenAtIndex(model, INDEX_FIRST_MODULE_TAKEN);
        showModuleTakenAtIndex(expectedModel, INDEX_FIRST_MODULE_TAKEN);

        assertExecutionSuccess(INDEX_FIRST_MODULE_TAKEN);
    }

    @Test
    public void execute_invalidIndexFilteredList_failure() {
        showModuleTakenAtIndex(model, INDEX_FIRST_MODULE_TAKEN);
        showModuleTakenAtIndex(expectedModel, INDEX_FIRST_MODULE_TAKEN);

        Index outOfBoundsIndex = INDEX_SECOND_MODULE_TAKEN;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundsIndex.getZeroBased() < model.getGradTrak().getModulesTakenList().size());

        assertExecutionFailure(outOfBoundsIndex, Messages.MESSAGE_INVALID_MODULETAKEN_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        SelectCommand selectFirstCommand = new SelectCommand(INDEX_FIRST_MODULE_TAKEN);
        SelectCommand selectSecondCommand = new SelectCommand(INDEX_SECOND_MODULE_TAKEN);

        // same object -> returns true
        assertTrue(selectFirstCommand.equals(selectFirstCommand));

        // same values -> returns true
        SelectCommand selectFirstCommandCopy = new SelectCommand(INDEX_FIRST_MODULE_TAKEN);
        assertTrue(selectFirstCommand.equals(selectFirstCommandCopy));

        // different types -> returns false
        assertFalse(selectFirstCommand.equals(1));

        // null -> returns false
        assertFalse(selectFirstCommand.equals(null));

        // different moduleTaken -> returns false
        assertFalse(selectFirstCommand.equals(selectSecondCommand));
    }

    /**
     * Executes a {@code SelectCommand} with the given {@code index},
     * and checks that the model's selected moduleTaken is set to the moduleTaken at {@code index}
     * in the filtered moduleTaken list.
     */
    private void assertExecutionSuccess(Index index) {
        SelectCommand selectCommand = new SelectCommand(index);
        String expectedMessage = String.format(SelectCommand.MESSAGE_SELECT_MODULE_TAKEN_SUCCESS, index.getOneBased());
        expectedModel.setSelectedClassForPrinting(model.getFilteredModulesTakenList().get(index.getZeroBased()));

        assertCommandSuccess(selectCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    /**
     * Executes a {@code SelectCommand} with the given {@code index}, and checks that a {@code CommandException}
     * is thrown with the {@code expectedMessage}.
     */
    private void assertExecutionFailure(Index index, String expectedMessage) {
        SelectCommand selectCommand = new SelectCommand(index);
        assertCommandFailure(selectCommand, model, commandHistory, expectedMessage);
    }
}
