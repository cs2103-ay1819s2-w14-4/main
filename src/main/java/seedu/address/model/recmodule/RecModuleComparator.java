package seedu.address.model.recmodule;

import java.util.Comparator;

import seedu.address.model.course.CourseReqType;

/**
 * Compares two {@code RecModule} based on {@code CourseReqType} and {@code ModuleInfoCode}.
 */
public class RecModuleComparator implements Comparator<RecModule> {

    @Override
    public int compare(RecModule first, RecModule second) {
        // req type satisfied by first and second should be present at time of comparison
        if (first.getCourseReqType().isPresent() && second.getCourseReqType().isPresent()) {
            // different req type -> compare priority of req type
            CourseReqType firstReqType = first.getCourseReqType().get();
            CourseReqType secondReqType = second.getCourseReqType().get();
            if (!firstReqType.equals(secondReqType)) {
                return firstReqType.compareTo(secondReqType);
            }
        }

        // same req type -> compare module level
        int firstLevel = first.getModuleInfoCode().getLevel();
        int secondLevel = second.getModuleInfoCode().getLevel();
        if (firstLevel != secondLevel) {
            return (firstLevel - secondLevel);
        }

        // same module level -> compare lexicographically
        return first.getModuleInfoCode().toString().compareTo(second.getModuleInfoCode().toString());
    }
}
