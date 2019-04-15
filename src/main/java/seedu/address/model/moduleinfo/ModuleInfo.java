package seedu.address.model.moduleinfo;

import java.util.Objects;

/**
 * Represents Module Information about a particular module available in NUS.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class ModuleInfo {
    private ModuleInfoCode code;
    private ModuleInfoTitle title;
    private ModuleInfoCredits credits;
    private ModuleInfoDescription description;
    private ModuleInfoWorkload workload;
    private String preclusions;
    private ModuleInfoDepartment department;
    private ModuleInfoPrerequisites prerequisites;

    public ModuleInfo(String code, String title, double credits, String description, String workLoad,
                      String preclusions, String department, String prerequisites) {
        this.code = new ModuleInfoCode(code);
        this.title = new ModuleInfoTitle(title);
        this.credits = new ModuleInfoCredits(credits);
        this.description = new ModuleInfoDescription(description);
        this.workload = new ModuleInfoWorkload(workLoad);
        this.preclusions = preclusions;
        this.department = new ModuleInfoDepartment(department);
        this.prerequisites = new ModuleInfoPrerequisites(code, prerequisites);

        System.out.println("Module:" + code + " has been created");

        this.prerequisites.generatePrerequisiteTree();

        //System.out.println("generated Tree for: " + code);
    }

    // ================ Object String Methods ==============================

    public String getCodeString() {
        return this.code.toString();
    }

    public String getTitleString() {
        return this.title.toString();
    }

    public double getCredits() {
        return this.credits.getCredits();
    }

    public String getCreditString() {
        return this.credits.toString();
    }

    public String getDescriptionString() {
        return this.description.toString();
    }

    public String getWorkloadString() {
        return this.workload.toString();
    }

    public String getPreclusionsString() {
        return this.preclusions;
    }

    public String getDepartmentString() {
        return this.department.toString();
    }

    public String getPrerequisitesString() {
        return this.prerequisites.toString();
    }

    // ================ Object methods ==============================

    public ModuleInfoCode getModuleInfoCode() {
        return this.code;
    }

    public ModuleInfoTitle getModuleInfoTitle() {
        return this.title;
    }

    public ModuleInfoDescription getModuleInfoDescription() {
        return this.description;
    }

    public ModuleInfoCredits getModuleInfoCredits() {
        return this.credits;
    }

    public ModuleInfoDepartment getModuleInfoDepartment() {
        return this.department;
    }

    public ModuleInfoWorkload getModuleInfoWorkload() {
        return this.workload;
    }

    public ModuleInfoPrerequisites getModuleInfoPrerequisite() {
        return this.prerequisites;
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two ModuleInfo.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ModuleInfo)) {
            return false;
        }

        ModuleInfo otherModuleInfo = (ModuleInfo) other;
        return otherModuleInfo.getModuleInfoCode().equals(getModuleInfoCode())
                && otherModuleInfo.getModuleInfoTitle().equals(getModuleInfoTitle())
                && otherModuleInfo.getModuleInfoCredits().equals(getModuleInfoCredits())
                && otherModuleInfo.getModuleInfoDescription().equals(getModuleInfoDescription())
                && otherModuleInfo.getModuleInfoDepartment().equals(getModuleInfoDepartment())
                && otherModuleInfo.getPreclusionsString().equals(getPreclusionsString())
                && otherModuleInfo.getPrerequisitesString().equals(getPrerequisitesString())
                && otherModuleInfo.getModuleInfoWorkload().equals(getModuleInfoWorkload());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(code, title, description, department, preclusions, prerequisites, workload);
    }
}
