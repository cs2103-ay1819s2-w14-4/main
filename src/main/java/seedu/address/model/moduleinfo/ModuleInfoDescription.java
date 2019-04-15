package seedu.address.model.moduleinfo;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;


/**
 * Represents a moduleInfo's description
 * Guarantees: immutable;
 * Validity wiil not be checked due to variance of descriptions {@link #isValidModuleInfoDescription(String)}
 */
public class ModuleInfoDescription {
    public static final String MESSAGE_CONSTRAINTS =
            "Must consist of at least 1 word and cannot start with whitespace";
    public static final String VALIDATION_REGEX = "[^\\s].*";
    private final String description;

    public ModuleInfoDescription(String moduleDescription) {
        requireNonNull(moduleDescription);
        checkArgument(isValidModuleInfoDescription(moduleDescription), MESSAGE_CONSTRAINTS);
        description = moduleDescription;
    }

    public static boolean isValidModuleInfoDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String toString() {
        return description;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleInfoDescription// instanceof handles nulls
                && description.equals(((ModuleInfoDescription) other).description)); // state check
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }
}
