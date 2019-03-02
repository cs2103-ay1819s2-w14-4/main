package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Grade;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Semester;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String semester;
    private final String expectedMinGrade;
    private final String expectedMaxGrade;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("semester") String semester,
                             @JsonProperty("expectedMinGrade") String expectedMinGrade,
                             @JsonProperty("expectedMaxGrade") String expectedMaxGrade,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.semester = semester;
        this.expectedMinGrade = expectedMinGrade;
        this.expectedMaxGrade = expectedMaxGrade;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getModuleInfo().fullName;
        semester = source.getSemester().name();
        expectedMinGrade = source.getExpectedMinGrade().toString();
        expectedMaxGrade = source.getExpectedMaxGrade().toString();
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (semester == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Semester.class.getSimpleName()));
        }
        if (!Semester.isValidSemester(semester)) {
            throw new IllegalValueException(Semester.MESSAGE_CONSTRAINTS);
        }
        final Semester modelSemester = Semester.valueOf(semester);

        if (expectedMinGrade == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Grade.class.getSimpleName()));
        }
        if (!Grade.isValidGrade(expectedMinGrade)) {
            throw new IllegalValueException(Grade.MESSAGE_CONSTRAINTS);
        }
        final Grade modelExpectedMinGrade = Grade.getGrade(expectedMinGrade);

        if (expectedMaxGrade == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Grade.class.getSimpleName()));
        }
        if (!Grade.isValidGrade(expectedMaxGrade)) {
            throw new IllegalValueException(Grade.MESSAGE_CONSTRAINTS);
        }
        final Grade modelExpectedMaxGrade = Grade.getGrade(expectedMaxGrade);

        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Person(modelName, modelSemester, modelExpectedMinGrade, modelExpectedMaxGrade, modelTags);
    }

}
