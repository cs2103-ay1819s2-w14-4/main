package seedu.address.model.moduletaken;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPECTED_MAX_GRADE_CS1010;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.TypicalModuleTaken.CS2103T;
import static seedu.address.testutil.TypicalModuleTaken.DEFAULT_MODULE_CS1010;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.model.moduletaken.exceptions.DuplicateModuleTakenException;
import seedu.address.model.moduletaken.exceptions.ModuleTakenNotFoundException;
import seedu.address.testutil.ModuleTakenBuilder;

public class UniqueModuleTakenListTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final UniqueModuleTakenList uniqueModuleTakenList = new UniqueModuleTakenList();

    @Test
    public void contains_nullModuleTaken_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueModuleTakenList.contains(null);
    }

    @Test
    public void contains_moduleTakenNotInList_returnsFalse() {
        assertFalse(uniqueModuleTakenList.contains(CS2103T));
    }

    @Test
    public void contains_moduleTakenInList_returnsTrue() {
        uniqueModuleTakenList.add(CS2103T);
        assertTrue(uniqueModuleTakenList.contains(CS2103T));
    }

    @Test
    public void contains_moduleTakenWithSameIdentityFieldsInList_returnsTrue() {
        uniqueModuleTakenList.add(CS2103T);
        ModuleTaken editedAlice = new ModuleTakenBuilder(CS2103T)
                .withExpectedMaxGrade(VALID_EXPECTED_MAX_GRADE_CS1010)
                .withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueModuleTakenList.contains(editedAlice));
    }

    @Test
    public void add_nullModuleTaken_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueModuleTakenList.add(null);
    }

    @Test
    public void add_duplicateModuleTaken_throwsDuplicateModuleTakenException() {
        uniqueModuleTakenList.add(CS2103T);
        thrown.expect(DuplicateModuleTakenException.class);
        uniqueModuleTakenList.add(CS2103T);
    }

    @Test
    public void setModuleTaken_nullTargetModuleTaken_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueModuleTakenList.setModuleTaken(null, CS2103T);
    }

    @Test
    public void setModuleTaken_nullEditedModuleTaken_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueModuleTakenList.setModuleTaken(CS2103T, null);
    }

    @Test
    public void setModuleTaken_targetModuleTakenNotInList_throwsModuleTakenNotFoundException() {
        thrown.expect(ModuleTakenNotFoundException.class);
        uniqueModuleTakenList.setModuleTaken(CS2103T, CS2103T);
    }

    @Test
    public void setModuleTaken_editedModuleTakenIsSameModuleTaken_success() {
        uniqueModuleTakenList.add(CS2103T);
        uniqueModuleTakenList.setModuleTaken(CS2103T, CS2103T);
        UniqueModuleTakenList expectedUniqueModuleTakenList = new UniqueModuleTakenList();
        expectedUniqueModuleTakenList.add(CS2103T);
        assertEquals(expectedUniqueModuleTakenList, uniqueModuleTakenList);
    }

    @Test
    public void setModuleTaken_editedModuleTakenHasSameIdentity_success() {
        uniqueModuleTakenList.add(CS2103T);
        ModuleTaken editedAlice = new ModuleTakenBuilder(CS2103T)
                .withExpectedMaxGrade(VALID_EXPECTED_MAX_GRADE_CS1010)
                .withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueModuleTakenList.setModuleTaken(CS2103T, editedAlice);
        UniqueModuleTakenList expectedUniqueModuleTakenList = new UniqueModuleTakenList();
        expectedUniqueModuleTakenList.add(editedAlice);
        assertEquals(expectedUniqueModuleTakenList, uniqueModuleTakenList);
    }

    @Test
    public void setModuleTaken_editedModuleTakenHasDifferentIdentity_success() {
        uniqueModuleTakenList.add(CS2103T);
        uniqueModuleTakenList.setModuleTaken(CS2103T, DEFAULT_MODULE_CS1010);
        UniqueModuleTakenList expectedUniqueModuleTakenList = new UniqueModuleTakenList();
        expectedUniqueModuleTakenList.add(DEFAULT_MODULE_CS1010);
        assertEquals(expectedUniqueModuleTakenList, uniqueModuleTakenList);
    }

    @Test
    public void setModuleTaken_editedModuleTakenHasNonUniqueIdentity_throwsDuplicateModuleTakenException() {
        uniqueModuleTakenList.add(CS2103T);
        uniqueModuleTakenList.add(DEFAULT_MODULE_CS1010);
        thrown.expect(DuplicateModuleTakenException.class);
        uniqueModuleTakenList.setModuleTaken(CS2103T, DEFAULT_MODULE_CS1010);
    }

    @Test
    public void remove_nullModuleTaken_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueModuleTakenList.remove(null);
    }

    @Test
    public void remove_moduleTakenDoesNotExist_throwsModuleTakenNotFoundException() {
        thrown.expect(ModuleTakenNotFoundException.class);
        uniqueModuleTakenList.remove(CS2103T);
    }

    @Test
    public void remove_existingModuleTaken_removesModuleTaken() {
        uniqueModuleTakenList.add(CS2103T);
        uniqueModuleTakenList.remove(CS2103T);
        UniqueModuleTakenList expectedUniqueModuleTakenList = new UniqueModuleTakenList();
        assertEquals(expectedUniqueModuleTakenList, uniqueModuleTakenList);
    }

    @Test
    public void setModulesTaken_nullUniqueModuleTakenList_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueModuleTakenList.setModulesTaken((UniqueModuleTakenList) null);
    }

    @Test
    public void setModulesTaken_uniqueModuleTakenList_replacesOwnListWithProvidedUniqueModuleTakenList() {
        uniqueModuleTakenList.add(CS2103T);
        UniqueModuleTakenList expectedUniqueModuleTakenList = new UniqueModuleTakenList();
        expectedUniqueModuleTakenList.add(DEFAULT_MODULE_CS1010);
        uniqueModuleTakenList.setModulesTaken(expectedUniqueModuleTakenList);
        assertEquals(expectedUniqueModuleTakenList, uniqueModuleTakenList);
    }

    @Test
    public void setModulesTaken_nullList_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueModuleTakenList.setModulesTaken((List<ModuleTaken>) null);
    }

    @Test
    public void setModulesTaken_list_replacesOwnListWithProvidedList() {
        uniqueModuleTakenList.add(CS2103T);
        List<ModuleTaken> moduleTakenList = Collections.singletonList(DEFAULT_MODULE_CS1010);
        uniqueModuleTakenList.setModulesTaken(moduleTakenList);
        UniqueModuleTakenList expectedUniqueModuleTakenList = new UniqueModuleTakenList();
        expectedUniqueModuleTakenList.add(DEFAULT_MODULE_CS1010);
        assertEquals(expectedUniqueModuleTakenList, uniqueModuleTakenList);
    }

    @Test
    public void setModulesTaken_listWithDuplicateModulesTaken_throwsDuplicateModuleTakenException() {
        List<ModuleTaken> listWithDuplicateModuleTakens = Arrays.asList(CS2103T, CS2103T);
        thrown.expect(DuplicateModuleTakenException.class);
        uniqueModuleTakenList.setModulesTaken(listWithDuplicateModuleTakens);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        thrown.expect(UnsupportedOperationException.class);
        uniqueModuleTakenList.asUnmodifiableObservableList().remove(0);
    }
}
