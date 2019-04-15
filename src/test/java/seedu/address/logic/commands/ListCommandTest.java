package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showModuleTakenAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE_TAKEN;
import static seedu.address.testutil.TypicalModuleTaken.getTypicalGradTrak;

import org.junit.Before;
import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserInfo;
import seedu.address.model.UserPrefs;
import seedu.address.model.course.CourseList;
import seedu.address.model.moduleinfo.ModuleInfoList;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;
    private CommandHistory commandHistory = new CommandHistory();

    @Before
    public void setUp() {
        model = new ModelManager(getTypicalGradTrak(), new UserPrefs(),
                new ModuleInfoList(), new CourseList(), new UserInfo());
        expectedModel = new ModelManager(model.getGradTrak(), new UserPrefs(),
                new ModuleInfoList(), new CourseList(), new UserInfo());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, commandHistory, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showModuleTakenAtIndex(model, INDEX_FIRST_MODULE_TAKEN);
        assertCommandSuccess(new ListCommand(), model, commandHistory, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
