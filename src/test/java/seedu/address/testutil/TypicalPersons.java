package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPECTED_MAX_GRADE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPECTED_MAX_GRADE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPECTED_MIN_GRADE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPECTED_MIN_GRADE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SEMESTER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SEMESTER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withExpectedMinGrade("F").withExpectedMaxGrade("A")
            .withSemester("Y1S2")
            .withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withExpectedMinGrade("D")
            .withExpectedMaxGrade("A").withSemester("Y3S2")
            .withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withSemester("Y5S2")
            .withExpectedMinGrade("D").withExpectedMaxGrade("A").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withSemester("Y4S2")
            .withExpectedMinGrade("F").withExpectedMaxGrade("A").withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withSemester("Y3S1")
            .withExpectedMinGrade("C").withExpectedMaxGrade("A").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withSemester("Y4S2")
            .withExpectedMinGrade("D").withExpectedMaxGrade("A").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withSemester("Y4S1")
            .withExpectedMinGrade("C").withExpectedMaxGrade("B").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withSemester("Y1S1")
            .withExpectedMinGrade("B").withExpectedMaxGrade("A").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withSemester("Y3S2")
            .withExpectedMinGrade("D").withExpectedMaxGrade("A").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder()
            .withName(VALID_NAME_AMY)
            .withSemester(VALID_SEMESTER_AMY)
            .withExpectedMinGrade(VALID_EXPECTED_MIN_GRADE_AMY)
            .withExpectedMaxGrade(VALID_EXPECTED_MAX_GRADE_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder()
            .withName(VALID_NAME_BOB)
            .withSemester(VALID_SEMESTER_BOB)
            .withExpectedMinGrade(VALID_EXPECTED_MIN_GRADE_BOB)
            .withExpectedMaxGrade(VALID_EXPECTED_MAX_GRADE_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
