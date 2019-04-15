package seedu.address.testutil;
import seedu.address.model.moduleinfo.ModuleInfoCredits;
import seedu.address.model.moduletaken.WeightedGrade;

/**
 * A class that stores some examples of Typical Weighted Grades
 */
public class TypicalWeightedGrades {

    public static final WeightedGrade HIGH_WEIGHTED_GRADE = new WeightedGrade(5, new ModuleInfoCredits(4));

    public static final WeightedGrade MID_WEIGHTED_GRADE = new WeightedGrade(3.5, new ModuleInfoCredits(2));

    private TypicalWeightedGrades() {
    }
}
