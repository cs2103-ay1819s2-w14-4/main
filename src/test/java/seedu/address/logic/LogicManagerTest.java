package seedu.address.logic;

import static org.junit.Assert.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_MODULETAKEN_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.EXPECTED_MAX_GRADE_DESC_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.EXPECTED_MIN_GRADE_DESC_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.SEMESTER_DESC_CS2103T;
import static seedu.address.testutil.TypicalModuleTaken.DEFAULT_MODULE_CS2103T;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.HistoryCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyGradTrak;
import seedu.address.model.UserInfo;
import seedu.address.model.UserPrefs;
import seedu.address.model.course.CourseList;
import seedu.address.model.moduleinfo.ModuleInfoList;
import seedu.address.model.moduletaken.ModuleTaken;
import seedu.address.storage.JsonGradTrakStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.StorageManager;
import seedu.address.storage.UserInfoStorage;
import seedu.address.storage.UserInfoStorageManager;
import seedu.address.testutil.ModuleTakenBuilder;


public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    private Model model = new ModelManager();
    private Logic logic;

    @Before
    public void setUp() throws Exception {
        JsonGradTrakStorage addressBookStorage = new JsonGradTrakStorage(temporaryFolder.newFile().toPath());
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.newFile().toPath());
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage);
        UserInfoStorage userInfoStorage = new UserInfoStorageManager();

        logic = new LogicManager(model, storage, userInfoStorage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
        assertHistoryCorrect(invalidCommand);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "delete 9";
        assertCommandException(deleteCommand, MESSAGE_INVALID_MODULETAKEN_DISPLAYED_INDEX);
        assertHistoryCorrect(deleteCommand);
    }

    @Test
    public void execute_validCommand_success() {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
        assertHistoryCorrect(listCommand);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() throws Exception {
        // Setup LogicManager with JsonGradTrakIoExceptionThrowingStub
        JsonGradTrakStorage addressBookStorage =
                new JsonGradTrakIoExceptionThrowingStub(temporaryFolder.newFile().toPath());
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.newFile().toPath());
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage);
        UserInfoStorage userInfoStorage = new UserInfoStorageManager();
        logic = new LogicManager(model, storage, userInfoStorage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + NAME_DESC_CS2103T + SEMESTER_DESC_CS2103T
                + EXPECTED_MIN_GRADE_DESC_CS2103T + EXPECTED_MAX_GRADE_DESC_CS2103T;
        ModuleTaken expectedModuleTaken = new ModuleTakenBuilder(DEFAULT_MODULE_CS2103T).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addModuleTaken(expectedModuleTaken);
        expectedModel.commitGradTrak();
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandBehavior(CommandException.class, addCommand, expectedMessage, expectedModel);
        assertHistoryCorrect(addCommand);
    }

    @Test
    public void getFilteredModuleTakenList_modifyList_throwsUnsupportedOperationException() {
        thrown.expect(UnsupportedOperationException.class);
        logic.getFilteredModuleTakenList().remove(0);
    }

    /**
     * Executes the command, confirms that no exceptions are thrown and that the result message is correct.
     * Also confirms that {@code expectedModel} is as specified.
     * @see #assertCommandBehavior(Class, String, String, Model)
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage, Model expectedModel) {
        assertCommandBehavior(null, inputCommand, expectedMessage, expectedModel);
    }

    /**
     * Executes the command, confirms that a ParseException is thrown and that the result message is correct.
     * @see #assertCommandBehavior(Class, String, String, Model)
     */
    private void assertParseException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, ParseException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that the result message is correct.
     * @see #assertCommandBehavior(Class, String, String, Model)
     */
    private void assertCommandException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, CommandException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the result message is correct.
     * @see #assertCommandBehavior(Class, String, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<?> expectedException, String expectedMessage) {
        Model expectedModel = new ModelManager(model.getGradTrak(), new UserPrefs(),
                new ModuleInfoList(), new CourseList(), new UserInfo());
        assertCommandBehavior(expectedException, inputCommand, expectedMessage, expectedModel);
    }

    /**
     * Executes the command, confirms that the result message is correct and that the expected exception is thrown,
     * and also confirms that the following two parts of the LogicManager object's state are as expected:<br>
     *      - the internal model manager data are same as those in the {@code expectedModel} <br>
     *      - {@code expectedModel}'s address book was saved to the storage file.
     */
    private void assertCommandBehavior(Class<?> expectedException, String inputCommand,
                                           String expectedMessage, Model expectedModel) {

        try {
            CommandResult result = logic.execute(inputCommand);
            assertEquals(expectedException, null);
            assertEquals(expectedMessage, result.getFeedbackToUser());
        } catch (CommandException | ParseException e) {
            assertEquals(expectedException, e.getClass());
            assertEquals(expectedMessage, e.getMessage());
        }

        assertEquals(expectedModel, model);
    }

    /**
     * Asserts that the result display shows all the {@code expectedCommands} upon the execution of
     * {@code HistoryCommand}.
     */
    private void assertHistoryCorrect(String... expectedCommands) {
        try {
            CommandResult result = logic.execute(HistoryCommand.COMMAND_WORD);
            String expectedMessage = String.format(
                    HistoryCommand.MESSAGE_SUCCESS, String.join("\n", expectedCommands));
            assertEquals(expectedMessage, result.getFeedbackToUser());
        } catch (ParseException | CommandException e) {
            throw new AssertionError("Parsing and execution of HistoryCommand.COMMAND_WORD should succeed.", e);
        }
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonGradTrakIoExceptionThrowingStub extends JsonGradTrakStorage {
        private JsonGradTrakIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveGradTrak(ReadOnlyGradTrak gradTrak, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
