package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ModuleInfoList;

/**
 * A class to access Module info data stored as a json file on the hard disk.
 */
public class JsonModuleInfoStorage implements ModuleInfoStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonAddressBookStorage.class);

    private Path filePath;

    public JsonModuleInfoStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getModuleInfoFilePath() {
        return filePath;
    }

    @Override
    public Optional<ModuleInfoList> readModuleInfoFile() throws DataConversionException {
        return readModuleInfoFile(filePath);
    }

    /**
     * Similar to {@link #readModuleInfoFile()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ModuleInfoList> readModuleInfoFile(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableModuleInfoList> jsonModuleInfoList = JsonUtil.readJsonFile(
                filePath, JsonSerializableModuleInfoList.class);
        if (!jsonModuleInfoList.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonModuleInfoList.get().toModelType());
        } catch (IllegalValueException e) {
            logger.info("Illegal values found in " + filePath + ": " + e.getMessage());
            throw new DataConversionException(e);
        }

    }

}
