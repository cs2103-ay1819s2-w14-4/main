package seedu.address.model.moduletaken;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPECTED_MAX_GRADE_CS1010;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPECTED_MIN_GRADE_CS1010;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_INFO_CODE_CS1010;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SEMESTER_CS1010;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.TypicalModuleTaken.CS2103T;
import static seedu.address.testutil.TypicalModuleTaken.DEFAULT_MODULE_CS1010;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.testutil.ModuleTakenBuilder;

public class ModuleTakenTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        ModuleTaken moduleTaken = new ModuleTakenBuilder().build();
        thrown.expect(UnsupportedOperationException.class);
        moduleTaken.getTags().remove(0);
    }

    @Test
    public void isSameModuleTaken() {
        // same object -> returns true
        assertTrue(CS2103T.isSameModuleTaken(CS2103T));

        // null -> returns false
        assertFalse(CS2103T.isSameModuleTaken(null));

        // different semester and expected min grade -> returns false
        ModuleTaken editedModule = new ModuleTakenBuilder(CS2103T)
                .withSemester(VALID_SEMESTER_CS1010)
                .withExpectedMinGrade(VALID_EXPECTED_MIN_GRADE_CS1010)
                .build();
        assertFalse(CS2103T.isSameModuleTaken(editedModule));

        // different name -> returns false
        editedModule = new ModuleTakenBuilder(CS2103T).withModuleInfoCode(VALID_MODULE_INFO_CODE_CS1010).build();
        assertFalse(CS2103T.isSameModuleTaken(editedModule));

        // same name, same semester, different attributes -> returns true
        editedModule = new ModuleTakenBuilder(CS2103T)
                .withExpectedMinGrade(VALID_EXPECTED_MIN_GRADE_CS1010)
                .withExpectedMaxGrade(VALID_EXPECTED_MAX_GRADE_CS1010)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(CS2103T.isSameModuleTaken(editedModule));

        // same name, same expected max grade, different tags -> returns true
        editedModule = new ModuleTakenBuilder(CS2103T)
                .withExpectedMaxGrade(VALID_EXPECTED_MAX_GRADE_CS1010)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(CS2103T.isSameModuleTaken(editedModule));
    }

    @Test
    public void equals() {
        // same values -> returns true
        ModuleTaken moduleCopy = new ModuleTakenBuilder(CS2103T).build();
        assertTrue(CS2103T.equals(moduleCopy));

        // same object -> returns true
        assertTrue(CS2103T.equals(CS2103T));

        // null -> returns false
        assertFalse(CS2103T.equals(null));

        // different type -> returns false
        assertFalse(CS2103T.equals(5));

        // different moduleTaken -> returns false
        assertFalse(CS2103T.equals(DEFAULT_MODULE_CS1010));

        // different name -> returns false
        ModuleTaken editedModule = new ModuleTakenBuilder(CS2103T)
                .withModuleInfoCode(VALID_MODULE_INFO_CODE_CS1010).build();
        assertFalse(CS2103T.equals(editedModule));

        // different semester -> returns false
        editedModule = new ModuleTakenBuilder(CS2103T)
                .withSemester(VALID_SEMESTER_CS1010).build();
        assertFalse(CS2103T.equals(editedModule));

        // different email -> returns false
        editedModule = new ModuleTakenBuilder(CS2103T)
                .withExpectedMinGrade(VALID_EXPECTED_MIN_GRADE_CS1010).build();
        assertFalse(CS2103T.equals(editedModule));

        // different address -> returns false
        editedModule = new ModuleTakenBuilder(CS2103T)
                .withExpectedMaxGrade(VALID_EXPECTED_MAX_GRADE_CS1010).build();
        assertFalse(CS2103T.equals(editedModule));

        // different tags -> returns false
        editedModule = new ModuleTakenBuilder(CS2103T)
                .withTags(VALID_TAG_HUSBAND).build();
        assertFalse(CS2103T.equals(editedModule));
    }
}
