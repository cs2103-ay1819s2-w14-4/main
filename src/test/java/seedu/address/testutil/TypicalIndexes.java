package seedu.address.testutil;

import seedu.address.commons.core.index.Index;
import seedu.address.model.moduletaken.Semester;

/**
 * A utility class containing a list of {@code Index} objects to be used in tests.
 */
public class TypicalIndexes {
    public static final Index INDEX_FIRST_MODULE_TAKEN = Index.fromOneBased(1);
    public static final Index INDEX_SECOND_MODULE_TAKEN = Index.fromOneBased(2);
    public static final Index INDEX_THIRD_MODULE_TAKEN = Index.fromOneBased(3);
    public static final Semester INDEX_FIRST_SEMESTER = Semester.Y1S1;
    public static final Semester INDEX_SECOND_SEMESTER = Semester.Y1S2;
    public static final Semester INDEX_THIRD_SEMESTER = Semester.Y2S1;
}
