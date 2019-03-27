package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.course.Course;
import seedu.address.model.course.CourseReqType;
import seedu.address.model.moduleinfo.ModuleInfo;
import seedu.address.model.moduleinfo.ModuleInfoCode;
import seedu.address.model.moduleinfo.ModuleInfoList;
import seedu.address.model.moduletaken.ModuleTaken;
import seedu.address.model.moduletaken.Semester;
import seedu.address.model.moduletaken.exceptions.PersonNotFoundException;

/**
 * Represents the in-memory model of the GradTrak data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private Course course;
    private Semester currentSemester;
    private ArrayList<PlanPreference> planPreferences;

    private final VersionedGradTrak versionedGradTrak;
    private final UserPrefs userPrefs;
    private final FilteredList<ModuleTaken> filteredModulesTaken;
    private final SimpleObjectProperty<ModuleTaken> selectedModuleTaken = new SimpleObjectProperty<>();

    //Model Information List for Model Manager to have Module Info List and list to be printed for displaymod
    private final ObservableList<ModuleInfo> allModules;
    private final FilteredList<ModuleInfo> displayList;
    private final ModuleInfoList moduleInfoList;

    private final FilteredList<ModuleInfoCode> recModuleList;
    private final SortedList<ModuleInfoCode> recModuleListSorted;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyGradTrak gradTrak, ReadOnlyUserPrefs userPrefs, ModuleInfoList allModules) {
        super();
        requireAllNonNull(gradTrak, userPrefs);

        logger.fine("Initializing with address book: " + gradTrak + " and user prefs " + userPrefs);

        versionedGradTrak = new VersionedGradTrak(gradTrak);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredModulesTaken = new FilteredList<>(versionedGradTrak.getModulesTakenList());
        filteredModulesTaken.addListener(this::ensureSelectedPersonIsValid);

        //Get an non Modifiable List of all modules and use a filtered list based on that to search for modules
        this.allModules = allModules.getObservableList();
        this.displayList = new FilteredList<>(this.allModules);
        this.moduleInfoList = allModules;

        this.recModuleList = new FilteredList<>(allModules.getObservableCodeList());
        this.recModuleListSorted = new SortedList<>(recModuleList);
    }

    public ModelManager() {
        this(new GradTrak(), new UserPrefs(), new ModuleInfoList());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setCourse(Course course) {
        requireNonNull(course);
        this.course = course;
    }

    @Override
    public Course getCourse() {
        return course;
    }

    @Override
    public Semester getCurrentSemester() {
        return currentSemester;
    }

    @Override
    public Cap computeCumulativeCap() {
        return new Cap("3.19");
    }

    @Override
    public Cap computeExpectedMinimumCap() {
        return new Cap("2.43");
    }

    @Override
    public Cap computeExpectedMaximumCap() {
        return new Cap("4.23");
    }


    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getGradTrakFilePath() {
        return userPrefs.getGradTrakFilePath();
    }

    @Override
    public void setGradTrakFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setGradTrakFilePath(addressBookFilePath);
    }

    //=========== GradTrak ================================================================================

    @Override
    public void setGradTrak(ReadOnlyGradTrak addressBook) {
        versionedGradTrak.resetData(addressBook);
    }

    @Override
    public ReadOnlyGradTrak getGradTrak() {
        return versionedGradTrak;
    }

    @Override
    public boolean hasModuleTaken(ModuleTaken moduleTaken) {
        requireNonNull(moduleTaken);
        return versionedGradTrak.hasModuleTaken(moduleTaken);
    }

    @Override
    public void deleteModuleTaken(ModuleTaken target) {
        versionedGradTrak.removeModuleTaken(target);
    }

    @Override
    public void addModuleTaken(ModuleTaken moduleTaken) {
        versionedGradTrak.addModuleTaken(moduleTaken);
        updateFilteredModulesTakenList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setModuleTaken(ModuleTaken target, ModuleTaken editedModuleTaken) {
        requireAllNonNull(target, editedModuleTaken);
        versionedGradTrak.setModuleTaken(target, editedModuleTaken);
    }

    //=========== Filtered ModuleTaken List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code ModuleTaken} backed by the internal list of
     * {@code versionedGradTrak}
     */
    @Override
    public ObservableList<ModuleTaken> getFilteredModulesTakenList() {
        return filteredModulesTaken;
    }

    @Override
    public void updateFilteredModulesTakenList(Predicate<ModuleTaken> predicate) {
        requireNonNull(predicate);
        filteredModulesTaken.setPredicate(predicate);
    }

    //=========== Undo/Redo =================================================================================

    @Override
    public boolean canUndoGradTrak() {
        return versionedGradTrak.canUndo();
    }

    @Override
    public boolean canRedoGradTrak() {
        return versionedGradTrak.canRedo();
    }

    @Override
    public void undoGradTrak() {
        versionedGradTrak.undo();
    }

    @Override
    public void redoGradTrak() {
        versionedGradTrak.redo();
    }

    @Override
    public void commitGradTrak() {
        versionedGradTrak.commit();
    }

    //=========== Selected moduleTaken ===========================================================================

    @Override
    public ReadOnlyProperty<ModuleTaken> selectedModuleTakenProperty() {
        return selectedModuleTaken;
    }

    @Override
    public ModuleTaken getSelectedModuleTaken() {
        return selectedModuleTaken.getValue();
    }

    @Override
    public void setSelectedModuleTaken(ModuleTaken moduleTaken) {
        if (moduleTaken != null && !filteredModulesTaken.contains(moduleTaken)) {
            throw new PersonNotFoundException();
        }
        selectedModuleTaken.setValue(moduleTaken);
    }

    //=========== Module Info List ===========================================================================
    @Override
    public ObservableList<ModuleInfo> getDisplayList() {
        return this.displayList;
    }

    @Override
    public void updateDisplayList(Predicate<ModuleInfo> predicate) {
        requireNonNull(predicate);
        displayList.setPredicate(predicate);
    }

    //=========== Module recommendation ===========================================================================
    @Override
    public ObservableList<ModuleInfoCode> getRecModuleListSorted() {
        return recModuleListSorted;
    }

    @Override
    public HashMap<ModuleInfoCode, CourseReqType> updateRecModuleList() {
        RecModuleManager recModuleManager = new RecModuleManager(course, versionedGradTrak);
        recModuleList.setPredicate(recModuleManager.getRecModulePredicate());
        recModuleListSorted.setComparator(recModuleManager.getRecModuleComparator());

        return recModuleManager.getCodeToReqMap();
    }

    /**
     * Ensures {@code selectedModuleTaken} is a valid moduleTaken in {@code filteredModulesTaken}.
     */
    private void ensureSelectedPersonIsValid(ListChangeListener.Change<? extends ModuleTaken> change) {
        while (change.next()) {
            if (selectedModuleTaken.getValue() == null) {
                // null is always a valid selected moduleTaken, so we do not need to check that it is valid anymore.
                return;
            }

            boolean wasSelectedPersonReplaced = change.wasReplaced() && change.getAddedSize() == change.getRemovedSize()
                    && change.getRemoved().contains(selectedModuleTaken.getValue());
            if (wasSelectedPersonReplaced) {
                // Update selectedModuleTaken to its new value.
                int index = change.getRemoved().indexOf(selectedModuleTaken.getValue());
                selectedModuleTaken.setValue(change.getAddedSubList().get(index));
                continue;
            }

            boolean wasSelectedPersonRemoved = change.getRemoved().stream()
                    .anyMatch(removedPerson -> selectedModuleTaken.getValue().isSameModuleTaken(removedPerson));
            if (wasSelectedPersonRemoved) {
                // Select the moduleTaken that came before it in the list,
                // or clear the selection if there is no such moduleTaken.
                selectedModuleTaken.setValue(change.getFrom() > 0 ? change.getList().get(change.getFrom() - 1) : null);
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return versionedGradTrak.equals(other.versionedGradTrak)
                && userPrefs.equals(other.userPrefs)
                && filteredModulesTaken.equals(other.filteredModulesTaken)
                && Objects.equals(selectedModuleTaken.get(), other.selectedModuleTaken.get());
    }

}
