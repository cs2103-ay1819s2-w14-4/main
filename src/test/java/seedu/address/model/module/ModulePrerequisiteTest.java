package seedu.address.model.module;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;
import seedu.address.testutil.TypicalModules;

public class ModulePrerequisiteTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new ModulePrereq(null, null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidModulePrereq = "";
        Assert.assertThrows(IllegalArgumentException.class, (
                ) -> new ModulePrereq(invalidModulePrereq, TypicalModules.getTypicalModules()));
    }

    @Test
    public void isValidModulePrereq() {
        // null address
        Assert.assertThrows(NullPointerException.class, () -> ModulePrereq.isValidModulePrereq(null));

        // invalid addresses
        assertFalse(ModulePrereq.isValidModulePrereq("")); //empty string
        assertFalse(ModulePrereq.isValidModulePrereq(" ")); //spaces only
        assertFalse(ModulePrereq.isValidModulePrereq(" CS1010S and CS2103T")); //starts with space
        // valid addresses
        assertTrue(ModulePrereq.isValidModulePrereq("LSM1301 and CS1010S")); //spaces in between words
        assertTrue(ModulePrereq.isValidModulePrereq("CS2103T")); //no spaces
        assertTrue(ModulePrereq.isValidModulePrereq("Students are from SOC and"
                + " must clear at least 70 MCs.")); //multiple spaces
    }
}
