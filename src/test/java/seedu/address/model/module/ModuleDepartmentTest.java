package seedu.address.model.module;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class ModuleDepartmentTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new ModuleDepartment(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidModuleDepartment = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new ModuleDepartment(invalidModuleDepartment));
    }

    @Test
    public void isValidModuleDepartment() {
        // null address
        Assert.assertThrows(NullPointerException.class, () -> ModuleDepartment.isValidModuleDepartment(null));

        // invalid addresses
        assertFalse(ModuleDepartment.isValidModuleDepartment("")); // empty string
        assertFalse(ModuleDepartment.isValidModuleDepartment(" ")); // spaces only
        assertFalse(ModuleDepartment.isValidModuleDepartment(" Department of Computer Science")); //Starts with space
        assertFalse(ModuleDepartment.isValidModuleDepartment("1 Department of Computer Science")); //contains numbers
        // valid addresses
        assertTrue(ModuleDepartment.isValidModuleDepartment("Computer Science"));
        assertTrue(ModuleDepartment.isValidModuleDepartment("Electrical Engineering")); // one character
        assertTrue(ModuleDepartment.isValidModuleDepartment("Statistics")); // long address
    }
}
