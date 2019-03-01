package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
//import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ModuleInfo;
import seedu.address.model.ModuleInfoList;

@JsonRootName(value = "moduleinfolist")
public class JsonSerializableModuleInfoList {

    private final List<JsonAdaptedModuleInfo> moduleInfoList = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableModuleInfoList} with the given Module Information.
     */
    @JsonCreator
    public JsonSerializableModuleInfoList(@JsonProperty("ModuleList") List<JsonAdaptedModuleInfo> moduleInfoList) {
        this.moduleInfoList.addAll(moduleInfoList);
    }

    /**
     * Converts this Module Info List into the model's {@code ModuleInfoList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ModuleInfoList toModelType() throws IllegalValueException {
        ModuleInfoList moduleinfolist = new ModuleInfoList();
        for (JsonAdaptedModuleInfo jsonAdaptedModuleInfo : moduleInfoList) {
            ModuleInfo moduleInfo = jsonAdaptedModuleInfo.toModelType();
            moduleinfolist.addModuleInfo(moduleInfo);
        }
        return moduleinfolist;
    }
}
