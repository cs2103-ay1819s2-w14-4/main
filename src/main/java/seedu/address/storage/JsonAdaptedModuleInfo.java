package seedu.address.storage;

//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ModuleInfo;

//TODO: Find a way to split the prerequisites into class Module Info to that module infos can be linked.

/**
 * Class to extract information form JSON file and convert it into ModuleInfo Objects
 */
public class JsonAdaptedModuleInfo {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Module has a missing field %s!";

    private final String code;
    private final String title;
    private final double credits;
    private final String description;
    private final String workLoad;
    private final String preclusions;
    private final String department;
    private final String prerequisites;

    /**
     * Constructs a {@code JsonAdaptedModuleInfo} with the given Module details from JSON file.
     */
    @JsonCreator
    public JsonAdaptedModuleInfo(@JsonProperty("ModuleCode") String code,
                                 @JsonProperty("ModuleTitle") String title,
                                 @JsonProperty("Department") String department,
                                 @JsonProperty("ModuleDescription") String description,
                                 @JsonProperty("ModuleCredit") double credits,
                                 @JsonProperty("Workload") String workLoad,
                                 @JsonProperty("Preclusion") String preclusions,
                                 @JsonProperty("Prerequisite")String prerequisite) {
        this.code = code;
        this.title = title;
        this.credits = credits;
        this.department = department;

        if (prerequisite == null) {
          this.prerequisites = " No prerequisites needed";
        } else {
            this.prerequisites = prerequisite;
        }

        if (description == null) {
            this.description = "No work load information provided";
        } else {
            this.description = description;
        }

        if (workLoad == null) {
            this.workLoad = "No work load information provided";
        } else {
            this.workLoad = workLoad;
        }

        if (preclusions == null) {
            this.preclusions = "No preclusions provided";
        } else {
            this.preclusions = preclusions;
        }
    }

    /**
     * Converts this Jackson-friendly adapted ModuleInfo object into the model's {@code ModuleInfo} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted ModuleInfo.
     */
    public ModuleInfo toModelType() throws IllegalValueException {
        if (code == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, " module code"));
        }
        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, " module title"));
        }

        if (department == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, " module department"));
        }

        return new ModuleInfo(code, title, credits, description, workLoad, preclusions, department, prerequisites);
    }
}
