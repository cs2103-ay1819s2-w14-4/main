package seedu.address.model.person;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class ExpectedMaxGradeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> Grade.valueOf(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> Grade.valueOf(invalidAddress));
    }

    @Test
    public void isValidGrade() {
        // null address
        Assert.assertThrows(NullPointerException.class, () -> Grade.isValidGrade(null));

        // invalid addresses
        assertFalse(Grade.isValidGrade("")); // empty string
        assertFalse(Grade.isValidGrade(" ")); // spaces only

        // valid addresses
        assertTrue(Grade.isValidGrade("Blk 456, Den Road, #01-355"));
        assertTrue(Grade.isValidGrade("-")); // one character
        assertTrue(Grade.isValidGrade("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long address
    }
}
