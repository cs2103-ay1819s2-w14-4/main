package seedu.address.logic;

import java.nio.file.Path;

import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ClassForPrinting;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyGradTrak;
import seedu.address.model.course.RequirementStatus;
import seedu.address.model.moduleinfo.ModuleInfo;
import seedu.address.model.moduleinfo.ModuleInfoCode;
import seedu.address.model.moduletaken.ModuleTaken;
import seedu.address.model.recmodule.RecModule;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the GradTrak.
     *
     * @see seedu.address.model.Model#getGradTrak()
     */
    ReadOnlyGradTrak getAddressBook();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<ModuleTaken> getFilteredModuleTakenList();

    /**
     * Returns an unmodifiable view of the list of commands entered by the user.
     * The list is ordered from the least recent command to the most recent command.
     */
    ObservableList<String> getHistory();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Selected moduleTaken in the filtered moduleTaken list.
     * null if no moduleTaken is selected.
     *
     * @see seedu.address.model.Model#selectedClassForPrintingProperty()
     */
    ReadOnlyProperty<ClassForPrinting> selectedClassForPrintingProperty();

    /**
     * Sets the selected moduleTaken in the filtered moduleTaken list.
     *
     * @see seedu.address.model.Model#setSelectedClassForPrinting(ClassForPrinting)
     */

    void setSelectedClassForPrinting(ClassForPrinting moduleTaken);

    /**
     * Sets the selected person in the filtered person list.
     *
     * @see Model#selectedModuleInfoProperty()
     */
    ReadOnlyProperty<ModuleInfo> selectedModuleInfoProperty();

    /**
     * Sets the selected person in the filtered person list.
     *
     * @see seedu.address.model.Model#setSelectedModuleInfo(ModuleInfo)
     */
    void setSelectedModuleInfo(ModuleInfo moduleInfo);

    /**
     * Gets the filtered list based on the search
     *
     * @see Model#getDisplayList()
     */
    ObservableList<ModuleInfo> getDisplayList();

    ObservableList<RequirementStatus> getRequirementStatusList();

    /**
     * Returns a moduleInfoCodeList
     */
    ObservableList<ModuleInfoCode> getModuleInfoCodeList();


    /**
     * Gets the {@code SortedList} of {@code RecModule}.
     * @see Model#getRecModuleListSorted()
     */
    ObservableList<RecModule> getRecModuleListSorted();
}
