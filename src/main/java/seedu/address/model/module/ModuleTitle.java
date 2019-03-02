package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a module's module title
 * Guarantees: immutable; is valid as declared in {@link #isValidModuleTitle(String)}
 */
public class ModuleTitle {
    public static final String MESSAGE_CONSTRAINTS =
            "Only alphanumeric characters and first character cannot be whitespace";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    public final String title;

    public ModuleTitle(String moduleTitle) {
        requireNonNull(moduleTitle);
        checkArgument(isValidModuleTitle(moduleTitle), MESSAGE_CONSTRAINTS);
        title = moduleTitle;
    }

    public static boolean isValidModuleTitle(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String toString() {
        return title;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleTitle// instanceof handles nulls
                && title.equals(((ModuleTitle) other).title)); // state check
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}
