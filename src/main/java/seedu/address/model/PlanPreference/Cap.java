package seedu.address.model.PlanPreference;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
/**
 * Represents CAP (Cumulative Average Grade) of the user in the system
 */
public class Cap {

    public static final String MESSAGE_CONSTRAINTS =
            "CAP should be 2 decimal places from 0 to 5";
    public static final String VALIDATION_REGEX = "";
    private double cap;

    /**
     * Constructs a {@code Cap}.
     *
     * @param cap A valid cap value.
     */
    public Cap(String cap) {
        requireNonNull(cap);
        checkArgument(isValidCap(cap), MESSAGE_CONSTRAINTS);
        this.cap = Double.parseDouble(cap);
    }

    /**
     * Returns true if a given string is a valid cap value.
     */
    public static boolean isValidCap(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Cap // instanceof handles nulls
                && cap == ((Cap) other).cap); // state check
    }

}
