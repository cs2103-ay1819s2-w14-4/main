package seedu.address.model.module;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class ModuleCreditsTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new ModuleCredits(null));
    }

    @Test
    public void constructor_invalidModuleCredits_throwsIllegalArgumentException() {
        String invalidModuleCredits = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new ModuleCredits(invalidModuleCredits));
    }

    @Test
    public void isValidModuleCredit() {
        // null address
        Assert.assertThrows(NullPointerException.class, () -> ModuleCredits.isValidModuleCredits(null));

        // invalid addresses
        assertFalse(ModuleCredits.isValidModuleCredits("")); // empty string
        assertFalse(ModuleCredits.isValidModuleCredits("-1")); //contains - sign
        assertFalse(ModuleCredits.isValidModuleCredits("21")); //larger than 20
        assertFalse(ModuleCredits.isValidModuleCredits("5.2")); //contains .
        assertFalse(ModuleCredits.isValidModuleCredits("CS1010")); //contains alphabet
        assertFalse(ModuleCredits.isValidModuleCredits("Sdasdas")); //contains only alphabet

        // valid addresses
        assertTrue(ModuleCredits.isValidModuleCredits("4"));
        assertTrue(ModuleCredits.isValidModuleCredits("0")); //minimum
        assertTrue(ModuleCredits.isValidModuleCredits("20")); //maximum
    }

}
