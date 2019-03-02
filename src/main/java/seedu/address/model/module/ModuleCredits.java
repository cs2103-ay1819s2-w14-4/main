package seedu.address.model.module;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a module's module credit
 * Guarantees: immutable; is valid as declared in {@link #isValidModuleCredits(String)}
 */
public class ModuleCredits {

    public static final String MESSAGE_CONSTRAINTS =
            "Module credits is at least 0, at most 20";
    public static final String VALIDATION_REGEX = "20|1?\\d";
    public final Integer credits;

    public ModuleCredits(String moduleCredits) {
        checkArgument(isValidModuleCredits(moduleCredits), MESSAGE_CONSTRAINTS);
        credits = Integer.valueOf(moduleCredits);
    }

    public static boolean isValidModuleCredits(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String toString() {
        return credits.toString();
    }

    public int getCredits() {
        return credits;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleCredits// instanceof handles nulls
                && credits == ((ModuleCredits) other).credits); // state check
    }

    @Override
    public int hashCode() {
        return credits.hashCode();
    }
}
