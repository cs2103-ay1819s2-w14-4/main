package seedu.address.storage;

import static org.junit.Assert.assertEquals;
import static seedu.address.storage.JsonAdaptedModuleTaken.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.TypicalModuleTaken.CS2101;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.moduleinfo.ModuleInfoCode;
import seedu.address.model.moduletaken.Grade;
import seedu.address.model.moduletaken.Hour;
import seedu.address.model.moduletaken.Semester;
import seedu.address.testutil.Assert;

public class JsonAdaptedModuleTakenTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_SEMESTER = "+651234";
    private static final String INVALID_EXPECTED_MAX_GRADE = " ";
    private static final String INVALID_EXPECTED_MIN_GRADE = "example.com";
    private static final String INVALID_LECTURE_HOUR = "f";
    private static final String INVALID_TUTORIAL_HOUR = "$";
    private static final String INVALID_LAB_HOUR = "2YE";
    private static final String INVALID_PROJECT_HOUR = "T^";
    private static final String INVALID_PREPARATION_HOUR = "f3";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = CS2101.getModuleInfoCode().toString();
    private static final String VALID_SEMESTER = CS2101.getSemester().name();
    private static final String VALID_EXPECTED_MIN_GRADE = CS2101.getExpectedMinGrade().name();
    private static final String VALID_EXPECTED_MAX_GRADE = CS2101.getExpectedMaxGrade().name();
    private static final String VALID_LECTURE_HOUR = CS2101.getLectureHour().toString();
    private static final String VALID_TUTORIAL_HOUR = CS2101.getTutorialHour().toString();
    private static final String VALID_LAB_HOUR = CS2101.getLabHour().toString();
    private static final String VALID_PROJECT_HOUR = CS2101.getProjectHour().toString();
    private static final String VALID_PREPARATION_HOUR = CS2101.getPreparationHour().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = CS2101.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validModuleTakenDetails_returnsModuleTaken() throws Exception {
        JsonAdaptedModuleTaken moduleTaken = new JsonAdaptedModuleTaken(CS2101);
        assertEquals(CS2101, moduleTaken.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedModuleTaken moduleTaken =
                new JsonAdaptedModuleTaken(INVALID_NAME, VALID_SEMESTER,
                        VALID_EXPECTED_MIN_GRADE, VALID_EXPECTED_MAX_GRADE, VALID_LECTURE_HOUR, VALID_TUTORIAL_HOUR,
                        VALID_LAB_HOUR, VALID_PROJECT_HOUR, VALID_PREPARATION_HOUR, VALID_TAGS);
        String expectedMessage = ModuleInfoCode.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, moduleTaken::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedModuleTaken moduleTaken =
                new JsonAdaptedModuleTaken(null, VALID_SEMESTER,
                VALID_EXPECTED_MIN_GRADE, VALID_EXPECTED_MAX_GRADE, VALID_LECTURE_HOUR, VALID_TUTORIAL_HOUR,
                VALID_LAB_HOUR, VALID_PROJECT_HOUR, VALID_PREPARATION_HOUR, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ModuleInfoCode.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, moduleTaken::toModelType);
    }

    @Test
    public void toModelType_invalidSemester_throwsIllegalValueException() {
        JsonAdaptedModuleTaken moduleTaken =
                new JsonAdaptedModuleTaken(VALID_NAME, INVALID_SEMESTER,
                        VALID_EXPECTED_MIN_GRADE, VALID_EXPECTED_MAX_GRADE, VALID_LECTURE_HOUR, VALID_TUTORIAL_HOUR,
                        VALID_LAB_HOUR, VALID_PROJECT_HOUR, VALID_PREPARATION_HOUR, VALID_TAGS);
        String expectedMessage = Semester.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, moduleTaken::toModelType);
    }

    @Test
    public void toModelType_nullSemester_throwsIllegalValueException() {
        JsonAdaptedModuleTaken moduleTaken = new JsonAdaptedModuleTaken(VALID_NAME, null,
                VALID_EXPECTED_MIN_GRADE, VALID_EXPECTED_MAX_GRADE, VALID_LECTURE_HOUR, VALID_TUTORIAL_HOUR,
                VALID_LAB_HOUR, VALID_PROJECT_HOUR, VALID_PREPARATION_HOUR, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Semester.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, moduleTaken::toModelType);
    }

    @Test
    public void toModelType_invalidExpectedMinGrade_throwsIllegalValueException() {
        JsonAdaptedModuleTaken moduleTaken =
                new JsonAdaptedModuleTaken(VALID_NAME, VALID_SEMESTER,
                        INVALID_EXPECTED_MIN_GRADE, VALID_EXPECTED_MAX_GRADE, VALID_LECTURE_HOUR, VALID_TUTORIAL_HOUR,
                        VALID_LAB_HOUR, VALID_PROJECT_HOUR, VALID_PREPARATION_HOUR, VALID_TAGS);
        String expectedMessage = Grade.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, moduleTaken::toModelType);
    }

    @Test
    public void toModelType_nullExpectedMinGrade_throwsIllegalValueException() {
        JsonAdaptedModuleTaken moduleTaken = new JsonAdaptedModuleTaken(VALID_NAME, VALID_SEMESTER,
                null, VALID_EXPECTED_MAX_GRADE, VALID_LECTURE_HOUR, VALID_TUTORIAL_HOUR,
                VALID_LAB_HOUR, VALID_PROJECT_HOUR, VALID_PREPARATION_HOUR, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Grade.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, moduleTaken::toModelType);
    }

    @Test
    public void toModelType_invalidExpectedMaxGrade_throwsIllegalValueException() {
        JsonAdaptedModuleTaken moduleTaken =
                new JsonAdaptedModuleTaken(VALID_NAME, VALID_SEMESTER,
                        VALID_EXPECTED_MIN_GRADE, INVALID_EXPECTED_MAX_GRADE, VALID_LECTURE_HOUR, VALID_TUTORIAL_HOUR,
                        VALID_LAB_HOUR, VALID_PROJECT_HOUR, VALID_PREPARATION_HOUR, VALID_TAGS);
        String expectedMessage = Grade.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, moduleTaken::toModelType);
    }

    @Test
    public void toModelType_nullExpectedMaxGrade_throwsIllegalValueException() {
        JsonAdaptedModuleTaken moduleTaken = new JsonAdaptedModuleTaken(VALID_NAME, VALID_SEMESTER,
                VALID_EXPECTED_MIN_GRADE, null, VALID_LECTURE_HOUR, VALID_TUTORIAL_HOUR,
                VALID_LAB_HOUR, VALID_PROJECT_HOUR, VALID_PREPARATION_HOUR, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Grade.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, moduleTaken::toModelType);
    }

    @Test
    public void toModelType_invalidLectureHour_throwsIllegalValueException() {
        JsonAdaptedModuleTaken moduleTaken =
                new JsonAdaptedModuleTaken(VALID_NAME, VALID_SEMESTER,
                        VALID_EXPECTED_MIN_GRADE, VALID_EXPECTED_MAX_GRADE, INVALID_LECTURE_HOUR, VALID_TUTORIAL_HOUR,
                        VALID_LAB_HOUR, VALID_PROJECT_HOUR, VALID_PREPARATION_HOUR, VALID_TAGS);
        String expectedMessage = Hour.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, moduleTaken::toModelType);
    }

    @Test
    public void toModelType_nullLectureHour_throwsIllegalValueException() {
        JsonAdaptedModuleTaken moduleTaken = new JsonAdaptedModuleTaken(VALID_NAME, VALID_SEMESTER,
                VALID_EXPECTED_MIN_GRADE, VALID_EXPECTED_MAX_GRADE, null, VALID_TUTORIAL_HOUR,
                VALID_LAB_HOUR, VALID_PROJECT_HOUR, VALID_PREPARATION_HOUR, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Hour.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, moduleTaken::toModelType);
    }

    @Test
    public void toModelType_invalidTutorialHour_throwsIllegalValueException() {
        JsonAdaptedModuleTaken moduleTaken =
                new JsonAdaptedModuleTaken(VALID_NAME, VALID_SEMESTER,
                        VALID_EXPECTED_MIN_GRADE, VALID_EXPECTED_MAX_GRADE, VALID_LECTURE_HOUR, INVALID_TUTORIAL_HOUR,
                        VALID_LAB_HOUR, VALID_PROJECT_HOUR, VALID_PREPARATION_HOUR, VALID_TAGS);
        String expectedMessage = Hour.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, moduleTaken::toModelType);
    }

    @Test
    public void toModelType_nullTutorialHour_throwsIllegalValueException() {
        JsonAdaptedModuleTaken moduleTaken = new JsonAdaptedModuleTaken(VALID_NAME, VALID_SEMESTER,
                VALID_EXPECTED_MIN_GRADE, VALID_EXPECTED_MAX_GRADE, VALID_LECTURE_HOUR, null,
                VALID_LAB_HOUR, VALID_PROJECT_HOUR, VALID_PREPARATION_HOUR, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Hour.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, moduleTaken::toModelType);
    }

    @Test
    public void toModelType_invalidLabHour_throwsIllegalValueException() {
        JsonAdaptedModuleTaken moduleTaken =
                new JsonAdaptedModuleTaken(VALID_NAME, VALID_SEMESTER,
                        VALID_EXPECTED_MIN_GRADE, VALID_EXPECTED_MAX_GRADE, VALID_LECTURE_HOUR, VALID_TUTORIAL_HOUR,
                        INVALID_LAB_HOUR, VALID_PROJECT_HOUR, VALID_PREPARATION_HOUR, VALID_TAGS);
        String expectedMessage = Hour.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, moduleTaken::toModelType);
    }

    @Test
    public void toModelType_nullLabHour_throwsIllegalValueException() {
        JsonAdaptedModuleTaken moduleTaken = new JsonAdaptedModuleTaken(VALID_NAME, VALID_SEMESTER,
                VALID_EXPECTED_MIN_GRADE, VALID_EXPECTED_MAX_GRADE, VALID_LECTURE_HOUR, VALID_TUTORIAL_HOUR,
                null, VALID_PROJECT_HOUR, VALID_PREPARATION_HOUR, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Hour.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, moduleTaken::toModelType);
    }

    @Test
    public void toModelType_invalidProjectHour_throwsIllegalValueException() {
        JsonAdaptedModuleTaken moduleTaken =
                new JsonAdaptedModuleTaken(VALID_NAME, VALID_SEMESTER,
                        VALID_EXPECTED_MIN_GRADE, VALID_EXPECTED_MAX_GRADE, VALID_LECTURE_HOUR, VALID_TUTORIAL_HOUR,
                        VALID_LAB_HOUR, INVALID_PROJECT_HOUR, VALID_PREPARATION_HOUR, VALID_TAGS);
        String expectedMessage = Hour.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, moduleTaken::toModelType);
    }

    @Test
    public void toModelType_nullProjectHour_throwsIllegalValueException() {
        JsonAdaptedModuleTaken moduleTaken = new JsonAdaptedModuleTaken(VALID_NAME, VALID_SEMESTER,
                VALID_EXPECTED_MIN_GRADE, VALID_EXPECTED_MAX_GRADE, VALID_LECTURE_HOUR, VALID_TUTORIAL_HOUR,
                VALID_LAB_HOUR, null, VALID_PREPARATION_HOUR, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Hour.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, moduleTaken::toModelType);
    }

    @Test
    public void toModelType_invalidPreparationHour_throwsIllegalValueException() {
        JsonAdaptedModuleTaken moduleTaken =
                new JsonAdaptedModuleTaken(VALID_NAME, VALID_SEMESTER,
                        VALID_EXPECTED_MIN_GRADE, VALID_EXPECTED_MAX_GRADE, VALID_LECTURE_HOUR, VALID_TUTORIAL_HOUR,
                        VALID_LAB_HOUR, VALID_PROJECT_HOUR, INVALID_PREPARATION_HOUR, VALID_TAGS);
        String expectedMessage = Hour.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, moduleTaken::toModelType);
    }

    @Test
    public void toModelType_nullreparationHour_throwsIllegalValueException() {
        JsonAdaptedModuleTaken moduleTaken = new JsonAdaptedModuleTaken(VALID_NAME, VALID_SEMESTER,
                VALID_EXPECTED_MIN_GRADE, VALID_EXPECTED_MAX_GRADE, VALID_LECTURE_HOUR, VALID_TUTORIAL_HOUR,
                VALID_LAB_HOUR, VALID_PROJECT_HOUR, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Hour.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, moduleTaken::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedModuleTaken moduleTaken =
                new JsonAdaptedModuleTaken(VALID_NAME, VALID_SEMESTER,
                        VALID_EXPECTED_MIN_GRADE, VALID_EXPECTED_MAX_GRADE, VALID_LECTURE_HOUR, VALID_TUTORIAL_HOUR,
                        VALID_LAB_HOUR, VALID_PROJECT_HOUR, VALID_PREPARATION_HOUR, invalidTags);
        Assert.assertThrows(IllegalValueException.class, moduleTaken::toModelType);
    }

}
