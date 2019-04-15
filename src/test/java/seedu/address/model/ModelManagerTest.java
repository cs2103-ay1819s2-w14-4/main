package seedu.address.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPECTED_MIN_GRADE_CS1010;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MODULES_TAKEN;
import static seedu.address.testutil.TypicalModuleTaken.CS2101;
import static seedu.address.testutil.TypicalModuleTaken.CS2103T;
import static seedu.address.testutil.TypicalModuleTaken.DEFAULT_MODULE_CS1010;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.course.CourseList;
import seedu.address.model.moduleinfo.ModuleInfoList;
import seedu.address.model.moduletaken.ModuleTaken;
import seedu.address.model.moduletaken.NameContainsKeywordsPredicate;
import seedu.address.model.moduletaken.exceptions.ModuleTakenNotFoundException;
import seedu.address.testutil.GradTrakBuilder;
import seedu.address.testutil.ModuleTakenBuilder;

public class ModelManagerTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new GradTrak(), new GradTrak(modelManager.getGradTrak()));
        assertEquals(null, modelManager.getSelectedClassForPrinting());
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        modelManager.setUserPrefs(null);
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setGradTrakFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setGradTrakFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        modelManager.setGuiSettings(null);
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        modelManager.setGradTrakFilePath(null);
    }

    @Test
    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setGradTrakFilePath(path);
        assertEquals(path, modelManager.getGradTrakFilePath());
    }

    @Test
    public void hasModuleTaken_nullModuleTaken_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        modelManager.hasModuleTaken(null);
    }

    @Test
    public void hasModuleTaken_moduleTakenNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasModuleTaken(CS2103T));
    }

    @Test
    public void hasModuleTaken_moduleTakenInAddressBook_returnsTrue() {
        modelManager.addModuleTaken(CS2103T);
        assertTrue(modelManager.hasModuleTaken(CS2103T));
    }

    @Test
    public void deleteModuleTaken_moduleTakenIsSelectedAndFirstModuleTakenInFilteredModuleTakenList_selectionCleared() {
        modelManager.addModuleTaken(CS2103T);
        modelManager.setSelectedClassForPrinting(CS2103T);
        modelManager.deleteModuleTaken(CS2103T);
        assertEquals(null, modelManager.getSelectedClassForPrinting());
    }

    @Test
    public void deleteModTaken_modTakenIsSelectedAndSecondModTakenInFilteredModTakenList_firstModTakenSelected() {
        modelManager.addModuleTaken(CS2103T);
        modelManager.addModuleTaken(DEFAULT_MODULE_CS1010);
        assertEquals(Arrays.asList(CS2103T, DEFAULT_MODULE_CS1010), modelManager.getFilteredModulesTakenList());
        modelManager.setSelectedClassForPrinting(DEFAULT_MODULE_CS1010);
        modelManager.deleteModuleTaken(DEFAULT_MODULE_CS1010);
        assertEquals(CS2103T, modelManager.getSelectedClassForPrinting());
    }

    @Test
    public void setModuleTaken_moduleTakenIsSelected_selectedModuleTakenUpdated() {
        modelManager.addModuleTaken(CS2103T);
        modelManager.setSelectedClassForPrinting(CS2103T);
        ModuleTaken updatedAlice = new ModuleTakenBuilder(CS2103T)
                .withExpectedMinGrade(VALID_EXPECTED_MIN_GRADE_CS1010).build();
        modelManager.setModuleTaken(CS2103T, updatedAlice);
        assertEquals(updatedAlice, modelManager.getSelectedClassForPrinting());
    }

    @Test
    public void getFilteredModuleTakenList_modifyList_throwsUnsupportedOperationException() {
        thrown.expect(UnsupportedOperationException.class);
        modelManager.getFilteredModulesTakenList().remove(0);
    }

    @Test
    public void setSelectedModuleTaken_moduleTakenNotInFilteredModuleTakenList_throwsModuleTakenNotFoundException() {
        thrown.expect(ModuleTakenNotFoundException.class);
        modelManager.setSelectedClassForPrinting(CS2103T);
    }

    @Test
    public void setSelectedModuleTaken_moduleTakenInFilteredModuleTakenList_setsSelectedModuleTaken() {
        modelManager.addModuleTaken(CS2103T);
        assertEquals(Collections.singletonList(CS2103T), modelManager.getFilteredModulesTakenList());
        modelManager.setSelectedClassForPrinting(CS2103T);
        assertEquals(CS2103T, modelManager.getSelectedClassForPrinting());
    }

    @Test
    public void equals() {
        GradTrak addressBook = new GradTrakBuilder().withModuleTaken(CS2103T).withModuleTaken(CS2101).build();
        GradTrak differentAddressBook = new GradTrak();
        UserPrefs userPrefs = new UserPrefs();
        ModuleInfoList moduleInfoList = new ModuleInfoList();
        CourseList courseList = new CourseList();
        UserInfo userInfo = new UserInfo();
        // same values -> returns true
        modelManager = new ModelManager(addressBook, userPrefs, moduleInfoList, courseList, userInfo);
        ModelManager modelManagerCopy = new ModelManager(addressBook, userPrefs, moduleInfoList, courseList, userInfo);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, userPrefs,
                moduleInfoList, courseList, userInfo)));

        // different filteredList -> returns false
        String[] keywords = CS2103T.getModuleInfoCode().toString().split("\\s+");
        modelManager.updateFilteredModulesTakenList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs,
                moduleInfoList, courseList, userInfo)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredModulesTakenList(PREDICATE_SHOW_ALL_MODULES_TAKEN);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setGradTrakFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(addressBook, differentUserPrefs,
                moduleInfoList, courseList, userInfo)));
    }
}
