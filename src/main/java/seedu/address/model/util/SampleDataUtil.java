package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Grade;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Semester;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), Semester.valueOf("Y1S1"), Grade.valueOf("C"),
                Grade.valueOf("A"),
                getTagSet("friends")),
            new Person(new Name("Bernice Yu"), Semester.valueOf("Y1S1"), Grade.valueOf("C"),
                Grade.valueOf("A"),
                getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), Semester.valueOf("Y2S1"), Grade.valueOf("C"),
                Grade.valueOf("A"),
                getTagSet("neighbours")),
            new Person(new Name("David Li"), Semester.valueOf("Y2S2"), Grade.valueOf("C"),
                Grade.valueOf("A"),
                getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), Semester.valueOf("Y2S1"), Grade.valueOf("C"),
                Grade.valueOf("A"),
                getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), Semester.valueOf("Y2S2"), Grade.valueOf("C"),
                Grade.valueOf("A"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
