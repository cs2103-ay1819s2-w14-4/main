package seedu.address.testutil;

import seedu.address.model.moduletaken.Hour;
import seedu.address.model.moduletaken.Workload;

/**
 * Helper class to build modules for testing
 */
public class WorkloadBuilder {

    public static final String DEFAULT_LECTUREHOURS = "0";
    public static final String DEFAULT_TUTORIALHOURS = "0";
    public static final String DEFAULT_LABHOURS = "0";
    public static final String DEFAULT_PROJECTHOURS = "0";
    public static final String DEFAULT_PREPARATIONHOURS = "0";

    private Hour lectureHour;
    private Hour tutorialHour;
    private Hour labHour;
    private Hour projectHour;
    private Hour preparationHour;

    public WorkloadBuilder() {
        lectureHour = new Hour(DEFAULT_LECTUREHOURS);
        tutorialHour = new Hour(DEFAULT_TUTORIALHOURS);
        labHour = new Hour(DEFAULT_LABHOURS);
        projectHour = new Hour(DEFAULT_PROJECTHOURS);
        preparationHour = new Hour(DEFAULT_PREPARATIONHOURS);
    }

    /**
     * Sets the {@code lectureHour} of the {@code Hour} that we are building
     */
    public WorkloadBuilder withLectureHour(String lectureHour) {
        this.lectureHour = new Hour(lectureHour);
        return this;
    }

    /**
     * Sets the {@code tutorialHour} of the {@code Hour} that we are building.
     */
    public WorkloadBuilder withTutorialHour(String tutorialHour) {
        this.tutorialHour = new Hour(tutorialHour);
        return this;
    }

    /**
     * Sets the {@code labHour} of the {@code Hour} that we are building.
     */
    public WorkloadBuilder withLabHour(String labHour) {
        this.labHour = new Hour(labHour);
        return this;
    }

    /**
     * Sets the {@code projectHour} of the {@code Hour} that we are building.
     */
    public WorkloadBuilder withProjectHour(String projectHour) {
        this.projectHour = new Hour(projectHour);
        return this;
    }

    /**
     * Sets the {@code preparationHour} of the {@code Hour} that we are building
     */
    public WorkloadBuilder withPreparationHour(String preparationHour) {
        this.preparationHour = new Hour(preparationHour);
        return this;
    }

    public Workload build() {
        return new Workload(lectureHour, tutorialHour, labHour, projectHour, preparationHour);
    }
}
