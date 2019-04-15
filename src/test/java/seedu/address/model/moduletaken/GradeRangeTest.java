package seedu.address.model.moduletaken;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.testutil.TypicalWorkloads.CS1010_WORKLOAD;
import static seedu.address.testutil.TypicalWorkloads.CS2040_WORKLOAD;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.testutil.WorkloadBuilder;

public class GradeRangeTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(CS2040_WORKLOAD.equals(CS2040_WORKLOAD));

        // different type -> returns false
        assertFalse(CS2040_WORKLOAD.equals(5));

        // different CS2040_WORKLOAD -> returns false
        assertFalse(CS2040_WORKLOAD.equals(CS1010_WORKLOAD));

        // same values -> returns true
        Workload modifiedCS2040Workload = new WorkloadBuilder()
                .withLectureHour(CS2040_WORKLOAD.getLectureHour().toString())
                .withTutorialHour(CS2040_WORKLOAD.getTutorialHour().toString())
                .withLabHour(CS2040_WORKLOAD.getLabHour().toString())
                .withProjectHour(CS2040_WORKLOAD.getProjectHour().toString())
                .withPreparationHour(CS2040_WORKLOAD.getPreparationHour().toString()).build();
        assertTrue(CS2040_WORKLOAD.equals(modifiedCS2040Workload));

        //different lecture hours -> return false
        modifiedCS2040Workload = new WorkloadBuilder()
                .withLectureHour("2")
                .withTutorialHour(CS2040_WORKLOAD.getTutorialHour().toString())
                .withLabHour(CS2040_WORKLOAD.getLabHour().toString())
                .withProjectHour(CS2040_WORKLOAD.getProjectHour().toString())
                .withPreparationHour(CS2040_WORKLOAD.getPreparationHour().toString()).build();
        assertFalse(CS2040_WORKLOAD.equals(modifiedCS2040Workload));

        //different tutorial hours -> return false
        modifiedCS2040Workload = new WorkloadBuilder()
                .withLectureHour(CS2040_WORKLOAD.getLectureHour().toString())
                .withTutorialHour("2")
                .withLabHour(CS2040_WORKLOAD.getLabHour().toString())
                .withProjectHour(CS2040_WORKLOAD.getProjectHour().toString())
                .withPreparationHour(CS2040_WORKLOAD.getPreparationHour().toString()).build();
        assertFalse(CS2040_WORKLOAD.equals(modifiedCS2040Workload));

        //different lab hours -> return false
        modifiedCS2040Workload = new WorkloadBuilder()
                .withLectureHour(CS2040_WORKLOAD.getLectureHour().toString())
                .withTutorialHour(CS2040_WORKLOAD.getTutorialHour().toString())
                .withLabHour("2")
                .withProjectHour(CS2040_WORKLOAD.getProjectHour().toString())
                .withPreparationHour(CS2040_WORKLOAD.getPreparationHour().toString()).build();
        assertFalse(CS2040_WORKLOAD.equals(modifiedCS2040Workload));

        //different project hours -> return false
        modifiedCS2040Workload = new WorkloadBuilder().withLectureHour(CS2040_WORKLOAD.getLectureHour().toString())
                .withTutorialHour(CS2040_WORKLOAD.getTutorialHour().toString())
                .withLabHour(CS2040_WORKLOAD.getLabHour().toString())
                .withProjectHour("2")
                .withPreparationHour(CS2040_WORKLOAD.getPreparationHour().toString()).build();
        assertFalse(CS2040_WORKLOAD.equals(modifiedCS2040Workload));

        //different preparation hours -> return false
        modifiedCS2040Workload = new WorkloadBuilder().withLectureHour(CS2040_WORKLOAD.getLectureHour().toString())
                .withTutorialHour(CS2040_WORKLOAD.getTutorialHour().toString())
                .withLabHour(CS2040_WORKLOAD.getLabHour().toString())
                .withProjectHour(CS2040_WORKLOAD.getProjectHour().toString())
                .withPreparationHour("2").build();
        assertFalse(CS2040_WORKLOAD.equals(modifiedCS2040Workload));
    }

}
