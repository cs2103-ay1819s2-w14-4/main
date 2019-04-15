package seedu.address.model.ModuleInfo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.model.moduleinfo.ModuleInfoWorkload;
import seedu.address.testutil.Assert;

public class ModuleInfoWorkloadTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new ModuleInfoWorkload(null));
    }

    @Test
    public void isValidModuleInfoWorkLoad() {
        // null Workload
        Assert.assertThrows(NullPointerException.class, () -> ModuleInfoWorkload.isValidModuleInfoWorkload(null));

        //Invalid ModuleInfoWorkload
        assertFalse(ModuleInfoWorkload.isValidModuleInfoWorkload("")); //empty String
        assertFalse(ModuleInfoWorkload.isValidModuleInfoWorkload(" ")); //only whitespace
        assertFalse(ModuleInfoWorkload.isValidModuleInfoWorkload("words")); //only words
        assertFalse(ModuleInfoWorkload.isValidModuleInfoWorkload("11231")); //only numbers
        assertFalse(ModuleInfoWorkload.isValidModuleInfoWorkload("1-2-3")); //incomplete workload data

        //Valid ModuleInfoWorkload
        assertTrue(ModuleInfoWorkload.isValidModuleInfoWorkload("1-2-3-4-5")); //Only integers
        assertTrue(ModuleInfoWorkload.isValidModuleInfoWorkload("1.0-2.0-3.1-4.7-5.5")); //Only double
        assertTrue(ModuleInfoWorkload.isValidModuleInfoWorkload("0-0-0-0-0")); //All Zeros
        assertTrue(ModuleInfoWorkload.isValidModuleInfoWorkload("1.0-1.0-2.0-4-5")); //Mix of int and double
    }
}
