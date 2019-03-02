package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Grade;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Semester;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    public EditPersonDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptorBuilder(Person person) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(person.getModuleInfo());
        descriptor.setSemester(person.getSemester());
        descriptor.setExpectedMinGrade(person.getExpectedMinGrade());
        descriptor.setExpectedMaxGrade(person.getExpectedMaxGrade());
        descriptor.setTags(person.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Semester} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withSemester(String semester) {
        descriptor.setSemester(Semester.valueOf(semester));
        return this;
    }

    /**
     * Sets the {@code ExpectedMinGrade} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withExpectedMinGrade(String expectedMinGrade) {
        descriptor.setExpectedMinGrade(Grade.valueOf(expectedMinGrade));
        return this;
    }

    /**
     * Sets the {@code ExpectedMaxGrade} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withExpectedMaxGrade(String expectedMaxGrade) {
        descriptor.setExpectedMaxGrade(Grade.valueOf(expectedMaxGrade));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }
}
