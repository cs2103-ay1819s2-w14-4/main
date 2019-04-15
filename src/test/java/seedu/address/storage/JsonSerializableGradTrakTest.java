package seedu.address.storage;

import static org.junit.Assert.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.GradTrak;
import seedu.address.testutil.TypicalModuleTaken;

public class JsonSerializableGradTrakTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableGradTrakTest");
    private static final Path TYPICAL_MODULES_TAKEN_FILE = TEST_DATA_FOLDER.resolve("typicalModulesTaken.json");
    private static final Path INVALID_MODULE_TAKEN_FILE = TEST_DATA_FOLDER.resolve("invalidModulesTaken.json");
    private static final Path DUPLICATE_MODULE_TAKEN_FILE = TEST_DATA_FOLDER.resolve("duplicateModulesTaken.json");

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void toModelType_typicalModulesTakenFile_success() throws Exception {
        JsonSerializableGradTrak dataFromFile = JsonUtil.readJsonFile(TYPICAL_MODULES_TAKEN_FILE,
                JsonSerializableGradTrak.class).get();
        GradTrak addressBookFromFile = dataFromFile.toModelType();
        GradTrak typicalModulesTakenAddressBook = TypicalModuleTaken.getTypicalGradTrak();
        assertEquals(addressBookFromFile, typicalModulesTakenAddressBook);
    }

    @Test
    public void toModelType_invalidModuleTakenFile_throwsIllegalValueException() throws Exception {
        JsonSerializableGradTrak dataFromFile = JsonUtil.readJsonFile(INVALID_MODULE_TAKEN_FILE,
                JsonSerializableGradTrak.class).get();
        thrown.expect(IllegalValueException.class);
        dataFromFile.toModelType();
    }

    @Test
    public void toModelType_duplicateModulesTaken_throwsIllegalValueException() throws Exception {
        JsonSerializableGradTrak dataFromFile = JsonUtil.readJsonFile(DUPLICATE_MODULE_TAKEN_FILE,
                JsonSerializableGradTrak.class).get();
        thrown.expect(IllegalValueException.class);
        thrown.expectMessage(JsonSerializableGradTrak.MESSAGE_DUPLICATE_MODULES_TAKEN);
        dataFromFile.toModelType();
    }

}
