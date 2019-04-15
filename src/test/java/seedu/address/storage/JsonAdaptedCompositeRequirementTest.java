package seedu.address.storage;

import static org.junit.Assert.assertEquals;
import static seedu.address.model.course.CompositeRequirement.LogicalConnector.AND;
import static seedu.address.model.course.CourseReqType.GE;
import static seedu.address.storage.coursestorage.JsonAdaptedCompositeRequirement.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.course.CompositeRequirement;
import seedu.address.model.course.CompositeRequirement.LogicalConnector;
import seedu.address.model.course.CourseReqType;
import seedu.address.model.course.CourseRequirement;
import seedu.address.model.course.PrimitiveRequirement;
import seedu.address.model.util.SampleCourseRequirement;
import seedu.address.storage.coursestorage.JsonAdaptedCompositeRequirement;
import seedu.address.storage.coursestorage.JsonAdaptedPrimitiveRequirement;

public class JsonAdaptedCompositeRequirementTest {
    private final CourseRequirement firstRequirement = SampleCourseRequirement.UNIVERSITY_LEVEL_REQUIREMENT;
    private final CourseRequirement secondRequirement = SampleCourseRequirement.INDUSTRIAL_SYSTEM_EXPERIENCE;
    private final CompositeRequirement firstAndSecond =
            (CompositeRequirement) firstRequirement.and(secondRequirement);
    private final CompositeRequirement secondOrFirst =
            (CompositeRequirement) secondRequirement.or(firstRequirement);
    private final String courseReqTypeGe = GE.toString();
    private final String logicalConnectorAnd = AND.toString();
    private final String invalidEnum = "invalid";

    @Test
    public void toModelType_validCompositeRequirement_returnsCompositeRequirement() throws Exception {
        JsonAdaptedCompositeRequirement test = new JsonAdaptedCompositeRequirement(firstAndSecond);
        assertEquals(firstAndSecond, test.toModelType());
        test = new JsonAdaptedCompositeRequirement(secondOrFirst);
        assertEquals(secondOrFirst, test.toModelType());
    }

    @Test
    public void toModelType_missingFirst_throwIllegalValueException() {
        JsonAdaptedCompositeRequirement test = new JsonAdaptedCompositeRequirement(null,
                new JsonAdaptedCompositeRequirement((CompositeRequirement) secondRequirement),
                logicalConnectorAnd, courseReqTypeGe);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "first requirement");
        assertThrows(IllegalValueException.class, expectedMessage, test::toModelType);
    }

    @Test
    public void toModelType_missingSecond_throwIllegalValueException() {
        JsonAdaptedCompositeRequirement test = new JsonAdaptedCompositeRequirement(
                new JsonAdaptedPrimitiveRequirement((PrimitiveRequirement) firstRequirement),
                null,
                logicalConnectorAnd, courseReqTypeGe);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "second requirement");
        assertThrows(IllegalValueException.class, expectedMessage, test::toModelType);
    }

    @Test
    public void toModelType_missingLogicalConnector_throwIllegalValueException() {
        JsonAdaptedCompositeRequirement test = new JsonAdaptedCompositeRequirement(
                new JsonAdaptedPrimitiveRequirement((PrimitiveRequirement) firstRequirement),
                new JsonAdaptedCompositeRequirement((CompositeRequirement) secondRequirement),
                null, courseReqTypeGe);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, LogicalConnector.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, test::toModelType);
    }

    @Test
    public void toModelType_invalidLogicalConnector_throwIllegalValueException() {
        JsonAdaptedCompositeRequirement test = new JsonAdaptedCompositeRequirement(
                new JsonAdaptedPrimitiveRequirement((PrimitiveRequirement) firstRequirement),
                new JsonAdaptedCompositeRequirement((CompositeRequirement) secondRequirement),
                invalidEnum, courseReqTypeGe);
        assertThrows(IllegalValueException.class, test::toModelType);
    }

    @Test
    public void toModel_missingCourseReqType_throwIllegalValueException() {
        JsonAdaptedCompositeRequirement test = new JsonAdaptedCompositeRequirement(
                new JsonAdaptedPrimitiveRequirement((PrimitiveRequirement) firstRequirement),
                new JsonAdaptedCompositeRequirement((CompositeRequirement) secondRequirement),
                logicalConnectorAnd, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, CourseReqType.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, test::toModelType);
    }

    @Test
    public void toModelType_invalidCourseReqType_throwIllegalValueException() {
        JsonAdaptedCompositeRequirement test = new JsonAdaptedCompositeRequirement(
                new JsonAdaptedPrimitiveRequirement((PrimitiveRequirement) firstRequirement),
                new JsonAdaptedCompositeRequirement((CompositeRequirement) secondRequirement),
                courseReqTypeGe, invalidEnum);
        assertThrows(IllegalValueException.class, test::toModelType);
    }

}
