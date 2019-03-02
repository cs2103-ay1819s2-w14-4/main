package seedu.address.model.person;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class ExpectedMinGradeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> Grade.valueOf(null));
    }

    @Test
    public void constructor_invalidExpectedMinGradeWithWhitespace_throwsIllegalArgumentException() {
        String invalidExpectedMinGradeWithWhitespace = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> Grade.valueOf(""));
    }

    @Test
    public void isValidExpectedMinGradeWithWhitespace() {
        // null email
        Assert.assertThrows(NullPointerException.class, () -> Grade.isValidGrade(null));

        // blank email
        assertFalse(Grade.isValidGrade("")); // empty string
        assertFalse(Grade.isValidGrade(" ")); // spaces only

        // missing parts
        assertFalse(Grade.isValidGrade("G"));
        assertFalse(Grade.isValidGrade("peterjackexample.com")); // missing '@' symbol
        assertFalse(Grade.isValidGrade("peterjack@")); // missing domain name

        // invalid parts
        assertFalse(Grade.isValidGrade("peterjack@-")); // invalid domain name
        assertFalse(Grade.isValidGrade("peterjack@exam_ple.com")); // underscore in domain name
        assertFalse(Grade.isValidGrade("peter jack@example.com")); // spaces in local part
        assertFalse(Grade.isValidGrade("peterjack@exam ple.com")); // spaces in domain name
        assertFalse(Grade.isValidGrade(" peterjack@example.com")); // leading space
        assertFalse(Grade.isValidGrade("peterjack@example.com ")); // trailing space
        assertFalse(Grade.isValidGrade("peterjack@@example.com")); // double '@' symbol
        assertFalse(Grade.isValidGrade("peter@jack@example.com")); // '@' symbol in local part
        assertFalse(Grade.isValidGrade("peterjack@example@com")); // '@' symbol in domain name
        assertFalse(Grade.isValidGrade("peterjack@.example.com")); // domain name starts with a period
        assertFalse(Grade.isValidGrade("peterjack@example.com.")); // domain name ends with a period
        assertFalse(Grade.isValidGrade("peterjack@-example.com")); // domain name starts with a hyphen
        assertFalse(Grade.isValidGrade("peterjack@example.com-")); // domain name ends with a hyphen

        // valid email
        assertTrue(Grade.isValidGrade("F"));
        assertTrue(Grade.isValidGrade("D"));
        assertTrue(Grade.isValidGrade("C"));
        assertTrue(Grade.isValidGrade("B"));
        assertTrue(Grade.isValidGrade("A"));
    }
}
