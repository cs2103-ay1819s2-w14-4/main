package seedu.address.model.ModuleInfo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.model.moduleinfo.ModuleInfoDescription;
import seedu.address.testutil.Assert;

public class ModuleInfoDescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new ModuleInfoDescription(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidDescription = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new ModuleInfoDescription(invalidDescription));
    }

    @Test
    public void isValidModuleInfoDescription() {
        // null name
        Assert.assertThrows(NullPointerException.class, () -> ModuleInfoDescription.isValidModuleInfoDescription(null));

        //Invalid Description
        assertFalse(ModuleInfoDescription.isValidModuleInfoDescription("")); //empty string
        assertFalse(ModuleInfoDescription.isValidModuleInfoDescription(" ")); //whitespace
        assertFalse(ModuleInfoDescription.isValidModuleInfoDescription(" start")); //start with whitepace

        //Valid Description
        assertTrue(ModuleInfoDescription.isValidModuleInfoDescription("s")); //single letter
        assertTrue(ModuleInfoDescription.isValidModuleInfoDescription("single")); //single word
        assertTrue(ModuleInfoDescription.isValidModuleInfoDescription("This is full sentence.")); //full sentence
        assertTrue(ModuleInfoDescription.isValidModuleInfoDescription("12")); // numbers

    }


}
