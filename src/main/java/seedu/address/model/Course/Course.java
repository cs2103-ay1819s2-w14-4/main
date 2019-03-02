package seedu.address.model.Course;

import java.util.ArrayList;

/**
 *  Represents course of the user that is enrolled in.
 */
public class Course {
    private CourseName courseName;
    private CourseDescription courseDescription;
    private ArrayList<CourseRequirement> courseRequirements;

    public CourseName getCourseName() {
        return courseName;
    }

    public CourseDescription getCourseDescription() {
        return courseDescription;
    }

    public ArrayList<CourseRequirement> getCourseRequirements() {
        return courseRequirements;
    }
}
