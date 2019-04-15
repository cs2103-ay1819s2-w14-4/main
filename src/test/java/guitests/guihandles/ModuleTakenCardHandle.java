package guitests.guihandles;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableMultiset;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.moduletaken.ModuleTaken;

/**
 * Provides a handle to a moduleTaken card in the moduleTaken list panel.
 */
public class ModuleTakenCardHandle extends NodeHandle<Node> {
    private static final String ID_FIELD_ID = "#id";
    private static final String MODULE_INFO_CODE_ID = "#moduleInfoCode";
    private static final String SEMESTER_FIELD_ID = "#semester";
    private static final String EXPECTED_MIN_GRADE_FIELD_ID = "#expectedMinGrade";
    private static final String EXPECTED_MAX_GRADE_FIELD_ID = "#expectedMaxGrade";
    private static final String TAGS_FIELD_ID = "#tags";

    private final Label idLabel;
    private final Label moduleInfoCodeLabel;
    private final Label expectedMaxGradeLabel;
    private final Label semesterLabel;
    private final Label expectedMinGradeLabel;
    private final List<Label> tagLabels;

    public ModuleTakenCardHandle(Node cardNode) {
        super(cardNode);

        idLabel = getChildNode(ID_FIELD_ID);
        moduleInfoCodeLabel = getChildNode(MODULE_INFO_CODE_ID);
        expectedMaxGradeLabel = getChildNode(EXPECTED_MAX_GRADE_FIELD_ID);
        semesterLabel = getChildNode(SEMESTER_FIELD_ID);
        expectedMinGradeLabel = getChildNode(EXPECTED_MIN_GRADE_FIELD_ID);

        Region tagsContainer = getChildNode(TAGS_FIELD_ID);
        tagLabels = tagsContainer
                .getChildrenUnmodifiable()
                .stream()
                .map(Label.class::cast)
                .collect(Collectors.toList());
    }

    public String getId() {
        return idLabel.getText();
    }

    public String getModuleInfoCode() {
        return moduleInfoCodeLabel.getText();
    }

    public String getExpectedMaxGrade() {
        return expectedMaxGradeLabel.getText();
    }

    public String getSemester() {
        return semesterLabel.getText();
    }

    public String getExpectedMinGrade() {
        return expectedMinGradeLabel.getText();
    }

    public List<String> getTags() {
        return tagLabels
                .stream()
                .map(Label::getText)
                .collect(Collectors.toList());
    }

    /**
     * Returns true if this handle contains {@code moduleTaken}.
     */
    public boolean equals(ModuleTaken moduleTaken) {
        return getModuleInfoCode().equals(moduleTaken.getModuleInfoCode().toString())
                && getSemester().equals(moduleTaken.getSemester().toString())
                && getExpectedMinGrade().equals(moduleTaken.getExpectedMinGrade().toString())
                && getExpectedMaxGrade().equals(moduleTaken.getExpectedMaxGrade().toString())
                && ImmutableMultiset.copyOf(getTags()).equals(ImmutableMultiset.copyOf(moduleTaken.getTags().stream()
                        .map(tag -> tag.tagName)
                        .collect(Collectors.toList())));
    }
}
