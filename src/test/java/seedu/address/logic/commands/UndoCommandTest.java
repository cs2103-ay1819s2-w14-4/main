package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.deleteFirstModuleTaken;
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

public class UndoCommandTest {

    private final Model model = new ModelManager(getTypicalGradTrak(), new UserPrefs(),
            new ModuleInfoList(), new CourseList(), new UserInfo());
    private final Model expectedModel = new ModelManager(getTypicalGradTrak(), new UserPrefs(),
            new ModuleInfoList(), new CourseList(), new UserInfo());
    private final CommandHistory commandHistory = new CommandHistory();

    @Before
    public void setUp() {
        // set up of models' undo/redo history
        deleteFirstModuleTaken(model);
        deleteFirstModuleTaken(model);

        deleteFirstModuleTaken(expectedModel);
        deleteFirstModuleTaken(expectedModel);
    }

    @Test
    public void execute() {
        // multiple undoable states in model
        expectedModel.undoGradTrak();
        assertCommandSuccess(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        // single undoable state in model
        expectedModel.undoGradTrak();
        assertCommandSuccess(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        // no undoable states in model
        assertCommandFailure(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_FAILURE);
    }
}
