package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ModuleInfo;
import seedu.address.model.ModuleInfoList;

//TODO: Find a way to set the relative path of the JSON file currently uses
/**
 * Manages storage of All the module information data in local storage.
 */
public class ModuleInfoManager implements ModuleInfoStorage{

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private static final Path moduleInfoFilePath = Paths.get("D:\\YR4S2\\CS2103T\\Projects\\main\\src\\main\\java" +
            "\\seedu\\address\\storage\\test1.json");
    private ModuleInfoStorage moduleInfoStorage;

    public ModuleInfoManager(){
        super();
        this.moduleInfoStorage = new JsonModuleInfoStorage(moduleInfoFilePath);
    }

    @Override
    public Optional<ModuleInfoList> readModuleInfoFile()throws DataConversionException{
        return readModuleInfoFile(moduleInfoStorage.getModuleInfoFilePath());
    }

    @Override
    public Path getModuleInfoFilePath(){
        return this.moduleInfoFilePath;
    }

    @Override
    public Optional<ModuleInfoList> readModuleInfoFile(Path filePath) throws DataConversionException {
        logger.fine("Attempting to read data from file: " + filePath);
        return moduleInfoStorage.readModuleInfoFile();
    }
}
