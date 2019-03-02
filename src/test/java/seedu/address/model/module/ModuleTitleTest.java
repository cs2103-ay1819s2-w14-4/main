package seedu.address.model.module;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class ModuleTitleTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new ModuleTitle(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidModuleTitle = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new ModuleTitle(invalidModuleTitle));
    }

    @Test
    public void isValidModuleTitle() {
        // null address
        Assert.assertThrows(NullPointerException.class, () -> ModuleTitle.isValidModuleTitle(null));

        // invalid addresses
        assertFalse(ModuleTitle.isValidModuleTitle("")); // empty string
        assertFalse(ModuleTitle.isValidModuleTitle(" ")); // spaces only
        assertFalse(ModuleTitle.isValidModuleTitle("abc?")); //contains non-alphanumeric character
        assertFalse(ModuleTitle.isValidModuleTitle("??!!!")); //contains only non-alphanumeric character
        // valid addresses
        assertTrue(ModuleTitle.isValidModuleTitle("General Biology 1")); //two words with one space
        assertTrue(ModuleTitle.isValidModuleTitle("Logic2")); // one word
        assertTrue(ModuleTitle.isValidModuleTitle("Communication for Computing Professionals")); //multiple spaces
        assertTrue(ModuleTitle.isValidModuleTitle("112312")); //only numbers
        assertTrue(ModuleTitle.isValidModuleTitle("testing123 1111"));
        assertTrue(ModuleTitle.isValidModuleTitle("a")); //only one alphabet
        assertTrue(ModuleTitle.isValidModuleTitle("1")); //only one number
    }
}

