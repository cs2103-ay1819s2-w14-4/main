package seedu.address.model.module;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.testutil.TypicalModules.CS1010;
import static seedu.address.testutil.TypicalModules.CS2040;
import static seedu.address.testutil.TypicalModules.CS2103T;
import static seedu.address.testutil.TypicalModules.LSM1301;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.testutil.ModuleBuilder;


class ModuleTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Module module = new ModuleBuilder().build();
        thrown.expect(UnsupportedOperationException.class);
    }

    @Test
    public void isSameModule() {
        // same object -> returns true
        assertTrue(CS2040.isSameModule(CS2040));

        // null -> returns false
        assertFalse(CS2040.isSameModule(null));

        //same module code different module title -> return true
        Module modifiedCS2040 = new ModuleBuilder().withModuleCode("CS2040")
                .withModuleTitle("Algorithms and Data Structures")
                .withModuleDescription(CS2040.getModuleDescription().toString())
                .withModuleCredits(CS2040.getModuleCredits().toString())
                .withModuleDepartment(CS2040.getModuleDepartment().toString())
                .withModulePrereq(CS2040.getModulePrereq().toString(), (
                        CS2040.getModulePrereq().prereq).toArray(new Module[0])).build();
        assertTrue(CS2040.isSameModule(modifiedCS2040));

        //different module code same module title -> return true
        modifiedCS2040 = new ModuleBuilder().withModuleCode("CS2040C")
                .withModuleTitle(CS2040.getModuleTitle().toString())
                .withModuleDescription(CS2040.getModuleDescription().toString())
                .withModuleCredits(CS2040.getModuleCredits().toString())
                .withModuleDepartment(CS2040.getModuleDepartment().toString())
                .withModulePrereq(CS2040.getModulePrereq().toString(), (
                        CS2040.getModulePrereq().prereq).toArray(new Module[0])).build();
        assertTrue(CS2040.isSameModule(modifiedCS2040));


        //different module code different module title -> returns false
        modifiedCS2040 = new ModuleBuilder().withModuleCode("CS2040C")
                .withModuleTitle("Algorithms and Data Structures")
                .withModuleDescription(CS2040.getModuleDescription().toString())
                .withModuleCredits(CS2040.getModuleCredits().toString())
                .withModuleDepartment(CS2040.getModuleDepartment().toString())
                .withModulePrereq(CS2040.getModulePrereq().toString(), (
                        CS2040.getModulePrereq().prereq).toArray(new Module[0])).build();
        assertFalse(CS2040.isSameModule(modifiedCS2040));

        // same module code, same module title, different attributes -> returns true
        modifiedCS2040 = new ModuleBuilder().withModuleCode(CS2040.getModuleCode().toString())
                .withModuleTitle(CS2040.getModuleTitle().toString())
                .withModuleDescription(CS1010.getModuleDescription().toString())
                .withModuleCredits(CS1010.getModuleCredits().toString())
                .withModuleDepartment(CS1010.getModuleDepartment().toString())
                .withModulePrereq(CS1010.getModulePrereq().toString(), (
                        CS1010.getModulePrereq().prereq).toArray(new Module[0])).build();
        assertFalse(CS2040.isSameModule(modifiedCS2040));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Module modifiedCS2040 = new ModuleBuilder().withModuleCode(CS2040.getModuleTitle().toString())
                .withModuleTitle(CS2040.getModuleTitle().toString())
                .withModuleDescription(CS2040.getModuleDescription().toString())
                .withModuleCredits(CS2040.getModuleCredits().toString())
                .withModuleDepartment(CS2040.getModuleDepartment().toString())
                .withModulePrereq(CS2040.getModulePrereq().toString(), (
                        CS2040.getModulePrereq().prereq).toArray(new Module[0])).build();
        assertTrue(CS2040.equals(modifiedCS2040));
        // same object -> returns true
        assertTrue(CS2040.equals(CS2040));

        // null -> returns false
        assertFalse(CS2040.equals(null));

        // different type -> returns false
        assertFalse(CS2040.equals(5));

        // different module -> returns false
        assertFalse(CS2040.equals(CS1010));

        // different module code -> returns false
        modifiedCS2040 = new ModuleBuilder().withModuleCode("CS2040C")
                .withModuleTitle(CS2040.getModuleTitle().toString())
                .withModuleDescription(CS2040.getModuleDescription().toString())
                .withModuleCredits(CS2040.getModuleCredits().toString())
                .withModuleDepartment(CS2040.getModuleDepartment().toString())
                .withModulePrereq(CS2040.getModulePrereq().toString(), (
                        CS2040.getModulePrereq().prereq).toArray(new Module[0])).build();
        assertFalse(CS2040.equals(modifiedCS2040));

        // different module title -> returns false
        modifiedCS2040 = new ModuleBuilder().withModuleCode(CS2040.getModuleTitle().toString())
                .withModuleTitle("Algorithms and Data Structures")
                .withModuleDescription(CS2040.getModuleDescription().toString())
                .withModuleCredits(CS2040.getModuleCredits().toString())
                .withModuleDepartment(CS2040.getModuleDepartment().toString())
                .withModulePrereq(CS2040.getModulePrereq().toString(), (
                        CS2040.getModulePrereq().prereq).toArray(new Module[0])).build();
        assertFalse(CS2040.equals(modifiedCS2040));

        // different module description -> returns false
        modifiedCS2040 = new ModuleBuilder().withModuleCode(CS2040.getModuleTitle().toString())
                .withModuleTitle(CS2040.getModuleTitle().toString())
                .withModuleDescription(CS1010.getModuleDescription().toString())
                .withModuleCredits(CS2040.getModuleCredits().toString())
                .withModuleDepartment(CS2040.getModuleDepartment().toString())
                .withModulePrereq(CS2040.getModulePrereq().toString(), (
                        CS2040.getModulePrereq().prereq).toArray(new Module[0])).build();
        assertFalse(CS2040.equals(modifiedCS2040));

        // different module credits -> returns false
        modifiedCS2040 = new ModuleBuilder().withModuleCode(CS2040.getModuleTitle().toString())
                .withModuleTitle(CS2040.getModuleTitle().toString())
                .withModuleDescription(CS2040.getModuleDescription().toString())
                .withModuleCredits("10")
                .withModuleDepartment(CS2040.getModuleDepartment().toString())
                .withModulePrereq(CS2040.getModulePrereq().toString(), (
                        CS2040.getModulePrereq().prereq).toArray(new Module[0])).build();
        assertFalse(CS2040.equals(modifiedCS2040));

        // different module departments -> returns false
        modifiedCS2040 = new ModuleBuilder().withModuleCode(CS2040.getModuleTitle().toString())
                .withModuleTitle(CS2040.getModuleTitle().toString())
                .withModuleDescription(CS2040.getModuleDescription().toString())
                .withModuleCredits(CS2040.getModuleCredits().toString())
                .withModuleDepartment(LSM1301.getModuleDepartment().toString())
                .withModulePrereq(CS2040.getModulePrereq().toString(), (
                        CS2040.getModulePrereq().prereq).toArray(new Module[0])).build();
        assertFalse(CS2040.equals(modifiedCS2040));

        //different module prerequisite -> returns false
        modifiedCS2040 = new ModuleBuilder().withModuleCode(CS2040.getModuleTitle().toString())
                .withModuleTitle(CS2040.getModuleTitle().toString())
                .withModuleDescription(CS2040.getModuleDescription().toString())
                .withModuleCredits(CS2040.getModuleCredits().toString())
                .withModuleDepartment(CS2040.getModuleDepartment().toString())
                .withModulePrereq(CS2040.getModulePrereq().toString(), (
                        CS2103T.getModulePrereq().prereq).toArray(new Module[0])).build();
        assertFalse(CS2040.equals(modifiedCS2040));
    }

}
