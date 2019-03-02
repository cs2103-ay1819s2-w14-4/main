package seedu.address.model.module;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class ModuleCodeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new ModuleCode(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidModuleCode = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new ModuleCode(invalidModuleCode));
    }

    @Test
    public void isValidModuleCode() {
        // null address
        Assert.assertThrows(NullPointerException.class, () -> ModuleCode.isValidModuleCode(null));

        // invalid addresses
        assertFalse(ModuleCode.isValidModuleCode("")); // empty string
        assertFalse(ModuleCode.isValidModuleCode(" ")); // spaces only
        assertFalse(ModuleCode.isValidModuleCode("AAA11111")); //more than 5 digits
        assertFalse(ModuleCode.isValidModuleCode("haha1234")); //prefix more than 3 letters
        assertFalse(ModuleCode.isValidModuleCode("a1231")); //less than 2 letters prefix
        assertFalse(ModuleCode.isValidModuleCode("CS1231SS")); //more than 2 letters suffix
        // valid addresses
        assertTrue(ModuleCode.isValidModuleCode("LSM1301"));
        assertTrue(ModuleCode.isValidModuleCode("CS2103T")); // one character
        assertTrue(ModuleCode.isValidModuleCode("ACC1007X")); // long address
    }
}
