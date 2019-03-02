package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Grade;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Semester;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_SEMESTER = "Y1S1";
    public static final String DEFAULT_EXPECTED_MIN_GRADE = "F";
    public static final String DEFAULT_EXPECTED_MAX_GRADE = "B";

    private Name name;
    private Semester semester;
    private Grade expectedMinGrade;
    private Grade expectedMaxGrade;
    private Set<Tag> tags;

    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        semester = Semester.valueOf(DEFAULT_SEMESTER);
        expectedMinGrade = Grade.valueOf(DEFAULT_EXPECTED_MIN_GRADE);
        expectedMaxGrade = Grade.valueOf(DEFAULT_EXPECTED_MAX_GRADE);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getModuleInfo();
        semester = personToCopy.getSemester();
        expectedMinGrade = personToCopy.getExpectedMinGrade();
        expectedMaxGrade = personToCopy.getExpectedMaxGrade();
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withExpectedMaxGrade(String expectedMaxGrade) {
        this.expectedMaxGrade = Grade.valueOf(expectedMaxGrade);
        return this;
    }

    /**
     * Sets the {@code Semester} of the {@code Person} that we are building.
     */
    public PersonBuilder withSemester(String semester) {
        this.semester = Semester.valueOf(semester);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withExpectedMinGrade(String expectedMinGrade) {
        this.expectedMinGrade = Grade.valueOf(expectedMinGrade);
        return this;
    }

    public Person build() {
        return new Person(name, semester, expectedMinGrade, expectedMaxGrade, tags);
    }

}
