package guitests.guihandles;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableMultiset;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

/**
 * Provides a handle to a person card in the person list panel.
 */
public class PersonCardHandle extends NodeHandle<Node> {
    private static final String ID_FIELD_ID = "#id";
    private static final String NAME_FIELD_ID = "#name";
    private static final String SEMESTER_FIELD_ID = "#semester";
    private static final String EXPECTED_MIN_GRADE_FIELD_ID = "#expectedMinGrade";
    private static final String EXPECTED_MAX_GRADE_FIELD_ID = "#expectedMaxGrade";
    private static final String TAGS_FIELD_ID = "#tags";

    private final Label idLabel;
    private final Label nameLabel;
    private final Label expectedMaxGradeLabel;
    private final Label semesterLabel;
    private final Label expectedMinGradeLabel;
    private final List<Label> tagLabels;

    public PersonCardHandle(Node cardNode) {
        super(cardNode);

        idLabel = getChildNode(ID_FIELD_ID);
        nameLabel = getChildNode(NAME_FIELD_ID);
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

    public String getName() {
        return nameLabel.getText();
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
     * Returns true if this handle contains {@code person}.
     */
    public boolean equals(Person person) {
        return getName().equals(person.getModuleInfo().fullName)
                && getSemester().equals(person.getSemester())
                && getExpectedMinGrade().equals(person.getExpectedMinGrade())
                && getExpectedMaxGrade().equals(person.getExpectedMaxGrade())
                && ImmutableMultiset.copyOf(getTags()).equals(ImmutableMultiset.copyOf(person.getTags().stream()
                        .map(tag -> tag.tagName)
                        .collect(Collectors.toList())));
    }
}
