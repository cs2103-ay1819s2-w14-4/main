package seedu.address.model;

/**
 * Represents Module Information about a particular module available in NUS.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class ModuleInfo {
    private String code;
    private String title;
    private double credits;
    private String description;
    private String workLoad;
    private String preclusions;
    private String department;
    private String prerequisites;

    public ModuleInfo(String code, String title, double credits, String description, String workLoad,
                      String preclusions, String department, String prerequisites) {
        this.code = code;
        this.title = title;
        this.credits = credits;
        this.description = description;
        this.workLoad = workLoad;
        this.preclusions = preclusions;
        this.department = department;
        this.prerequisites = prerequisites;

        System.out.println("Module:" + code + " has been created");
    }

    public String getCode() {
        return this.code;
    }

    public String getTitle() {
        return this.title;
    }

    public double getCredits() {
        return this.credits;
    }

    public String getDescription() {
        return this.description;
    }

    public String getWorkLoad() { return this.workLoad; }

    public String getPreclusions() {
        return this.preclusions;
    }

    public String getDepartment() {
        return this.department;
    }

    public String getPrerequisites() { return this.prerequisites; }
}
