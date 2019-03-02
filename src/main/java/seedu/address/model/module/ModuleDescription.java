package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a module's module description
 * Guarantees: immutable; is valid as declared in {@link #isValidModuleDescription(String)}
 */
public class ModuleDescription {
    public static final String MESSAGE_CONSTRAINTS =
            "Must consist of at least 1 word and cannot start with whitespace";
    public static final String VALIDATION_REGEX = "[^\\s].*";
    public final String description;

    public ModuleDescription(String moduleDescription) {
        requireNonNull(moduleDescription);
        checkArgument(isValidModuleDescription(moduleDescription), MESSAGE_CONSTRAINTS);
        description = moduleDescription;
    }

    public static boolean isValidModuleDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String toString() {
        return description;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleDescription// instanceof handles nulls
                && description.equals(((ModuleDescription) other).description)); // state check
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }
}
