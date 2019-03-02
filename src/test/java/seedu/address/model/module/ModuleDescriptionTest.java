package seedu.address.model.module;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class ModuleDescriptionTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new ModuleDescription(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidModuleDescription = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new ModuleDescription(invalidModuleDescription));
    }

    @Test
    public void isValidModuleDescription() {
        // null descriptions
        Assert.assertThrows(NullPointerException.class, () -> ModuleDescription.isValidModuleDescription(null));

        // invalid descriptions
        assertFalse(ModuleDescription.isValidModuleDescription("")); // empty string
        assertFalse(ModuleDescription.isValidModuleDescription(" ")); // spaces only

        // valid descriptions
        assertTrue(ModuleDescription.isValidModuleDescription("Description here")); //two words with one space
        assertTrue(ModuleDescription.isValidModuleDescription("Nil")); // one word
        assertTrue(ModuleDescription.isValidModuleDescription(
                "The objective of this module is to provide the essentials of ring theory and module theory. "
                        + "Major topics: rings, ring isomorphism theorems, prime and maximal ideals, "
                        + "integral domains, field of fractions, factorization, unique factorization domains, "
                        + "principal ideal domains, Euclidean domains, "
                        + "factorization in polynomial domains, modules, "
                        + "module isomorphism theorems, cyclic modules, free modules of finite rank, "
                        + "finitely generated modules, finitely generated modules "
                        + "over a principal ideal domain.")); //long description
        assertTrue(ModuleDescription.isValidModuleDescription("112312")); //only numbers
        assertTrue(ModuleDescription.isValidModuleDescription("testing123 1111"));
        assertTrue(ModuleDescription.isValidModuleDescription("a")); //only one alphabet
        assertTrue(ModuleDescription.isValidModuleDescription("1")); //only one number
    }
}
