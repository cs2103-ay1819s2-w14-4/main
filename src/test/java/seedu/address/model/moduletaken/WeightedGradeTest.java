package seedu.address.model.moduletaken;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.testutil.TypicalWeightedGrades.HIGH_WEIGHTED_GRADE;
import static seedu.address.testutil.TypicalWeightedGrades.MID_WEIGHTED_GRADE;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class WeightedGradeTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(HIGH_WEIGHTED_GRADE.equals(HIGH_WEIGHTED_GRADE));

        // different type -> returns false
        assertFalse(HIGH_WEIGHTED_GRADE.equals(5));

        // different CS2040_WORKLOAD -> returns false
        assertFalse(HIGH_WEIGHTED_GRADE.equals(MID_WEIGHTED_GRADE));

        // same values -> returns true
        WeightedGrade modifiedHighWeightedGrade = new WeightedGrade(
                HIGH_WEIGHTED_GRADE.getScore(), HIGH_WEIGHTED_GRADE.getModuleInfoCredits());
        assertTrue(HIGH_WEIGHTED_GRADE.equals(modifiedHighWeightedGrade));

        //different module info credits -> return false
        modifiedHighWeightedGrade = new WeightedGrade(
                HIGH_WEIGHTED_GRADE.getScore(), MID_WEIGHTED_GRADE.getModuleInfoCredits());
        assertFalse(HIGH_WEIGHTED_GRADE.equals(modifiedHighWeightedGrade));

        //different score -> return false
        modifiedHighWeightedGrade = new WeightedGrade(
                HIGH_WEIGHTED_GRADE.getScore(), MID_WEIGHTED_GRADE.getModuleInfoCredits());
        assertFalse(HIGH_WEIGHTED_GRADE.equals(modifiedHighWeightedGrade));
    }

}
