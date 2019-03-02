package seedu.address.model.Course;

import java.util.function.Predicate;

import seedu.address.model.person.Person;

/**
 * A specified condition to meet to graduate from a course
 */
public class CourseRequirement {
    private Predicate<Person> predicate;
    private String descr;

    public CourseRequirement(Predicate<Person> predicate, String descr) {
        this.predicate = predicate;
        this.descr = descr;
    }

    public boolean test(Person p) {
        return this.predicate.test(p);
    }
}
