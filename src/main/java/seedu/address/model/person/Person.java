package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name moduleInfo;
    private final Semester semester;

    // Data fields
    private final Grade expectedMinGrade;
    private final Grade expectedMaxGrade;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name moduleInfo, Semester semester, Grade expectedMinGrade, Grade expectedMaxGrade, Set<Tag> tags) {
        requireAllNonNull(moduleInfo, semester, expectedMinGrade, expectedMaxGrade, tags);
        this.moduleInfo = moduleInfo;
        this.semester = semester;
        this.expectedMinGrade = expectedMinGrade;
        this.expectedMaxGrade = expectedMaxGrade;
        this.tags.addAll(tags);
    }

    public Name getModuleInfo() {
        return moduleInfo;
    }

    public Semester getSemester() {
        return semester;
    }

    public Grade getExpectedMinGrade() {
        return expectedMinGrade;
    }

    public Grade getExpectedMaxGrade() {
        return expectedMaxGrade;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getModuleInfo().equals(getModuleInfo())
                && (otherPerson.getSemester().equals(getSemester())
                || otherPerson.getExpectedMinGrade().equals(getExpectedMinGrade()));
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getModuleInfo().equals(getModuleInfo())
                && otherPerson.getSemester().equals(getSemester())
                && otherPerson.getExpectedMinGrade().equals(getExpectedMinGrade())
                && otherPerson.getExpectedMaxGrade().equals(getExpectedMaxGrade())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(moduleInfo, semester, expectedMinGrade, expectedMaxGrade, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getModuleInfo())
                .append(" Semester: ")
                .append(getSemester())
                .append(" Expected Min Grade: ")
                .append(getExpectedMinGrade())
                .append(" Expected Max Grade: ")
                .append(getExpectedMaxGrade())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}
