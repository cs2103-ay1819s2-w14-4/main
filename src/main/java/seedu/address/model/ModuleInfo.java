package seedu.address.model;

public class ModuleInfo {
    private String code;
    private String title;
    private int    credits;
    private String description;
    private String workLoad;
    private String preclusions;
    private String department;

    public ModuleInfo(String code, String title, int credits, String description,String workLoad,
                      String preclusions, String department){
        this.code = code;
        this.title = title;
        this.credits = credits;
        this.description = description;
        this.preclusions = preclusions;
        this.department = department;

        System.out.println("Module:" + code + " has been created");
    }

    public String getCode(){
        return this.code;
    }

    public String getTitle(){
        return this.title;
    }

    public int getCredits(){
        return this.credits;
    }

    public String getDescription(){
        return this.description;
    }

    public String getPreclusions(){
        return this.preclusions;
    }

    public String getDepartment(){
        return this.department;
    }
}