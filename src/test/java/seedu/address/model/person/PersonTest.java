package seedu.address.model.person;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPECTED_MAX_GRADE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPECTED_MIN_GRADE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SEMESTER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.testutil.PersonBuilder;

public class PersonTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new PersonBuilder().build();
        thrown.expect(UnsupportedOperationException.class);
        person.getTags().remove(0);
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // different semester and email -> returns false
        Person editedAlice = new PersonBuilder(ALICE)
                .withSemester(VALID_SEMESTER_BOB)
                .withExpectedMinGrade(VALID_EXPECTED_MIN_GRADE_BOB)
                .build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // different name -> returns false
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // same name, same semester, different attributes -> returns true
        editedAlice = new PersonBuilder(ALICE)
                .withExpectedMinGrade(VALID_EXPECTED_MIN_GRADE_BOB)
                .withExpectedMaxGrade(VALID_EXPECTED_MAX_GRADE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // same name, same email, different attributes -> returns true
        editedAlice = new PersonBuilder(ALICE)
                .withSemester(VALID_SEMESTER_BOB)
                .withExpectedMaxGrade(VALID_EXPECTED_MAX_GRADE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // same name, same semester, same email, different attributes -> returns true
        editedAlice = new PersonBuilder(ALICE)
                .withExpectedMaxGrade(VALID_EXPECTED_MAX_GRADE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSamePerson(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new PersonBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different person -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Person editedAlice = new PersonBuilder(ALICE)
                .withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different semester -> returns false
        editedAlice = new PersonBuilder(ALICE)
                .withSemester(VALID_SEMESTER_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PersonBuilder(ALICE)
                .withExpectedMinGrade(VALID_EXPECTED_MIN_GRADE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new PersonBuilder(ALICE)
                .withExpectedMaxGrade(VALID_EXPECTED_MAX_GRADE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PersonBuilder(ALICE)
                .withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
