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

public class JsonAdaptedModuleInfo {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Module has a missing field %s!";

    private final String code;
    private final  String title;
    private final int    credits;
    private final String description;
    private final String workLoad;
    private final String preclusions;
    private final String department;

    /**
     * Constructs a {@code JsonAdaptedModuleInfo} with the given Module details from JSON file.
     */
    @JsonCreator
    public JsonAdaptedModuleInfo(@JsonProperty("ModuleCode") String code,
                                 @JsonProperty("ModuleTitle") String title,
                                 @JsonProperty("Department") String department,
                                 @JsonProperty("ModuleDescription") String description,
                                 @JsonProperty("ModuleCredit") int credits,
                                 @JsonProperty("Workload") String workLoad,
                                 @JsonProperty("Preclusion") String preclusions) {
        this.code = code;
        this.title = title;
        this.credits = credits;
        this.description = description;
        this.workLoad = workLoad;
        this.department = department;

        if(preclusions == null){
            this.preclusions = " ";
        }else{
            this.preclusions = preclusions;
        }
    }

    /**
     * Converts this Jackson-friendly adapted ModuleInfo object into the model's {@code ModuleInfo} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted ModuleInfo.
     */
    public ModuleInfo toModelType() throws IllegalValueException{
        if(code == null){
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT," module code"));
        }
        if(title == null){
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT," module title"));
        }
        if(description == null){
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT," module description"));
        }
        if(workLoad == null){
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT," module work load"));
        }
        if(department == null){
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT," module department"));
        }

        return new ModuleInfo(code,title,credits,description,workLoad,preclusions,department);
    }
}
