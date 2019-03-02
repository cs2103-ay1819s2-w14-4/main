package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.util.InvalidationListenerManager;
import seedu.address.model.ModuleInfo;

/**
 * Wraps all data at the Module Info List level
 * Duplicates are not allowed (by  comparison)
 */
public class ModuleInfoList {

    private final List<ModuleInfo> moduleInfoList;
    private final InvalidationListenerManager invalidationListenerManager = new InvalidationListenerManager();

    {
        moduleInfoList = new ArrayList<>();
    }

    public ModuleInfoList() {}

    public void addModuleInfo(ModuleInfo module){
        this.moduleInfoList.add(module);
        System.out.println("new module added:"+ module.getCode());
    }

    public ModuleInfo getModule(String code){
        ModuleInfo module = null;
        for(int i = 0; i < moduleInfoList.size(); i++){
            if(moduleInfoList.get(i).getCode().equals(code)){
                module = moduleInfoList.get(i);
            }
        }
        return module;
    }

    public ObservableList<ModuleInfo> getObservableList(){
        ObservableList<ModuleInfo> observableList = FXCollections.observableArrayList(moduleInfoList);
        return observableList;
    }
}
