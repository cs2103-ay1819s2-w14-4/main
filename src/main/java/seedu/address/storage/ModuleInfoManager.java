package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.*;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ModuleInfo;

public class ModuleInfoManager implements ModuleInfoStorage{

    private static final Path moduleInfoFilePath = Paths.get("test1.json");


    @Override
    public Path getModuleInfoFilePath(){
        return this.moduleInfoFilePath;
    }

    @Override
    public ArrayList<ModuleInfo> readModuleInfoFile(Path filePath) throws DataConversionException, IOException{
        ArrayList<ModuleInfo> allModules = new ArrayList<>();
        return allModules;
    }
}
