package seedu.address.model.person;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class SemesterTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> Semester.valueOf(null));
    }

    @Test
    public void constructor_invalidSemester_throwsIllegalArgumentException() {
        String invalidSemester = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> Semester.valueOf(invalidSemester));
    }

    @Test
    public void isValidSemester() {
        // null semester number
        Assert.assertThrows(NullPointerException.class, () -> Semester.isValidSemester(null));

        // invalid semester numbers
        assertFalse(Semester.isValidSemester("")); // empty string
        assertFalse(Semester.isValidSemester(" ")); // spaces only
        assertFalse(Semester.isValidSemester("91")); // less than 3 numbers
        assertFalse(Semester.isValidSemester("semester")); // non-numeric
        assertFalse(Semester.isValidSemester("9011p041")); // alphabets within digits
        assertFalse(Semester.isValidSemester("9312 1534")); // spaces within digits

        // valid semester numbers
        assertTrue(Semester.isValidSemester("911")); // exactly 3 numbers
        assertTrue(Semester.isValidSemester("93121534"));
        assertTrue(Semester.isValidSemester("124293842033123")); // long semester numbers
    }
}
