package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.GradTrakParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ClassForPrinting;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyGradTrak;
import seedu.address.model.course.RequirementStatus;
import seedu.address.model.moduleinfo.ModuleInfo;
import seedu.address.model.moduleinfo.ModuleInfoCode;
import seedu.address.model.moduletaken.ModuleTaken;
import seedu.address.model.recmodule.RecModule;
import seedu.address.storage.Storage;
import seedu.address.storage.UserInfoStorage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final UserInfoStorage userInfoStorage;
    private final CommandHistory history;
    private final GradTrakParser gradTrakParser;
    private boolean gradTrakModified;
    private boolean userInfoModified;

    public LogicManager(Model model, Storage storage, UserInfoStorage userInfoStorage) {
        this.model = model;
        this.storage = storage;
        this.userInfoStorage = userInfoStorage;
        history = new CommandHistory();
        gradTrakParser = new GradTrakParser();

        // Set gradTrakModified to true whenever the model's gradTrak is modified.
        model.getGradTrak().addListener(observable -> gradTrakModified = true);
        model.getUserInfo().addListener(observable -> userInfoModified = true);
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");
        gradTrakModified = false;
        userInfoModified = false;

        CommandResult commandResult;
        try {
            Command command = gradTrakParser.parseCommand(commandText);
            commandResult = command.execute(model, history);
        } finally {
            history.add(commandText);
        }

        if (userInfoModified) {
            logger.info("userInfo modified, saving to file.");
            try {
                userInfoStorage.saveUserInfo(model.getUserInfo());
            } catch (IOException ioe) {
                throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
            }
        }
        if (gradTrakModified) {
            logger.info("GradTrak modified, saving to file.");
            try {
                storage.saveGradTrak(model.getGradTrak());
            } catch (IOException ioe) {
                throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
            }
        }

        return commandResult;
    }

    @Override
    public ReadOnlyGradTrak getAddressBook() {
        return model.getGradTrak();
    }

    @Override
    public ObservableList<ModuleTaken> getFilteredModuleTakenList() {
        return model.getFilteredModulesTakenList();
    }

    @Override
    public ObservableList<String> getHistory() {
        return history.getHistory();
    }

    @Override
    public Path getAddressBookFilePath() {
        return model.getGradTrakFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    @Override
    public ReadOnlyProperty<ClassForPrinting> selectedClassForPrintingProperty() {
        return model.selectedClassForPrintingProperty();
    }

    @Override
    public void setSelectedClassForPrinting(ClassForPrinting moduleTaken) {
        model.setSelectedClassForPrinting(moduleTaken);
    }

    @Override
    public ReadOnlyProperty<ModuleInfo> selectedModuleInfoProperty() {
        return model.selectedModuleInfoProperty();
    }

    @Override
    public void setSelectedModuleInfo(ModuleInfo moduleInfo) {
        model.setSelectedModuleInfo(moduleInfo);
    }

    @Override
    public ObservableList<ModuleInfo> getDisplayList() {
        return model.getDisplayList();
    }

    @Override
    public ObservableList<RequirementStatus> getRequirementStatusList() {
        return model.getRequirementStatusList();
    }

    @Override
    public ObservableList<ModuleInfoCode> getModuleInfoCodeList() {
        return model.getModuleInfoCodeList();
    }


    @Override
    public ObservableList<RecModule> getRecModuleListSorted() {
        return model.getRecModuleListSorted();
    }

}
