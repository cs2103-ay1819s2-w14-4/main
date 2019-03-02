package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a module's module department
 * Guarantees: immutable; is valid as declared in {@link #isValidModuleDepartment(String)}
 */
public class ModuleDepartment {
    public static final String MESSAGE_CONSTRAINTS =
            "Must consist of only characters and white spaces and cannot begin with whitespace.";
    public static final String VALIDATION_REGEX = "[^\\s1-9][a-zA-Z ]{0,}";
    public final String department;

    public ModuleDepartment(String moduleDepartment) {
        requireNonNull(moduleDepartment);
        checkArgument(isValidModuleDepartment(moduleDepartment), MESSAGE_CONSTRAINTS);
        department = moduleDepartment;
    }

    public static boolean isValidModuleDepartment(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String toString() {
        return department;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleDepartment// instanceof handles nulls
                && department.equals(((ModuleDepartment) other).department)); // state check
    }

    @Override
    public int hashCode() {
        return department.hashCode();
    }
}
