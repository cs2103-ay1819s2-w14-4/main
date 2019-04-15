package seedu.address.testutil;

import seedu.address.model.moduletaken.Hour;
import seedu.address.model.moduletaken.Workload;

/**
 * A class that stores some examples of Typical Workloads
 */
public class TypicalWorkloads {

    public static final Workload CS1010_WORKLOAD = new Workload(new Hour("1"), new Hour("3"), new Hour("1"),
            new Hour("1"), new Hour("2"));

    public static final Workload CS2040_WORKLOAD = new Workload(new Hour("1"), new Hour("1"), new Hour("1"),
            new Hour("1"), new Hour("1"));

    private TypicalWorkloads() {
    }
}
