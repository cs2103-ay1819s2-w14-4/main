package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EXPECTED_MAX_GRADE_DESC_CS1010;
import static seedu.address.logic.commands.CommandTestUtil.EXPECTED_MAX_GRADE_DESC_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.EXPECTED_MIN_GRADE_DESC_CS1010;
import static seedu.address.logic.commands.CommandTestUtil.EXPECTED_MIN_GRADE_DESC_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CS2103T_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EXPECTED_MAX_GRADE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EXPECTED_MIN_GRADE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SEMESTER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.SEMESTER_DESC_CS1010;
import static seedu.address.logic.commands.CommandTestUtil.SEMESTER_DESC_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPECTED_MAX_GRADE_CS1010;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPECTED_MAX_GRADE_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPECTED_MIN_GRADE_CS1010;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPECTED_MIN_GRADE_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_INFO_CODE_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SEMESTER_CS1010;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SEMESTER_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE_TAKEN;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MODULE_TAKEN;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_MODULE_TAKEN;

import org.junit.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditModuleTakenDescriptor;
import seedu.address.model.moduleinfo.ModuleInfoCode;
import seedu.address.model.moduletaken.Grade;
import seedu.address.model.moduletaken.Semester;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditModuleTakenDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_MODULE_INFO_CODE_CS2103T, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_CS2103T, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_CS2103T, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_CS2103T_DESC, ModuleInfoCode.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_SEMESTER_DESC, Semester.MESSAGE_CONSTRAINTS); // invalid semester
        assertParseFailure(parser, "1" + INVALID_EXPECTED_MIN_GRADE_DESC, Grade.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_EXPECTED_MAX_GRADE_DESC, Grade.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid semester followed by valid min expected grade
        assertParseFailure(parser, "1" + INVALID_SEMESTER_DESC
                + EXPECTED_MIN_GRADE_DESC_CS2103T, Semester.MESSAGE_CONSTRAINTS);

        // valid semester followed by invalid semester. The test case for invalid semester followed by valid semester
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + SEMESTER_DESC_CS1010
                + INVALID_SEMESTER_DESC, Semester.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code ModuleTaken} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND
                + TAG_DESC_HUSBAND + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND
                + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND
                + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_CS2103T_DESC
                + INVALID_EXPECTED_MIN_GRADE_DESC + VALID_EXPECTED_MAX_GRADE_CS2103T
                + VALID_SEMESTER_CS2103T, ModuleInfoCode.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_MODULE_TAKEN;
        String userInput = targetIndex.getOneBased() + SEMESTER_DESC_CS1010 + TAG_DESC_HUSBAND
                + EXPECTED_MIN_GRADE_DESC_CS2103T + EXPECTED_MAX_GRADE_DESC_CS2103T
                + NAME_DESC_CS2103T + TAG_DESC_FRIEND;

        EditCommand.EditModuleTakenDescriptor descriptor = new EditModuleTakenDescriptorBuilder()
                .withName(VALID_MODULE_INFO_CODE_CS2103T)
                .withSemester(VALID_SEMESTER_CS1010)
                .withExpectedMinGrade(VALID_EXPECTED_MIN_GRADE_CS2103T)
                .withExpectedMaxGrade(VALID_EXPECTED_MAX_GRADE_CS2103T)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_MODULE_TAKEN;
        String userInput = targetIndex.getOneBased() + SEMESTER_DESC_CS1010 + EXPECTED_MIN_GRADE_DESC_CS2103T;

        EditModuleTakenDescriptor descriptor = new EditModuleTakenDescriptorBuilder()
                .withSemester(VALID_SEMESTER_CS1010)
                .withExpectedMinGrade(VALID_EXPECTED_MIN_GRADE_CS2103T).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_MODULE_TAKEN;
        String userInput = targetIndex.getOneBased() + NAME_DESC_CS2103T;
        EditCommand.EditModuleTakenDescriptor descriptor = new EditModuleTakenDescriptorBuilder()
                .withName(VALID_MODULE_INFO_CODE_CS2103T).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // semester
        userInput = targetIndex.getOneBased() + SEMESTER_DESC_CS2103T;
        descriptor = new EditModuleTakenDescriptorBuilder().withSemester(VALID_SEMESTER_CS2103T).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // expected min grade
        userInput = targetIndex.getOneBased() + EXPECTED_MIN_GRADE_DESC_CS2103T;
        descriptor = new EditModuleTakenDescriptorBuilder()
                .withExpectedMinGrade(VALID_EXPECTED_MIN_GRADE_CS2103T)
                .build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // expected max grade
        userInput = targetIndex.getOneBased() + EXPECTED_MAX_GRADE_DESC_CS2103T;
        descriptor = new EditModuleTakenDescriptorBuilder()
                .withExpectedMaxGrade(VALID_EXPECTED_MAX_GRADE_CS2103T)
                .build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_FRIEND;
        descriptor = new EditModuleTakenDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_MODULE_TAKEN;
        String userInput = targetIndex.getOneBased() + SEMESTER_DESC_CS2103T
                + EXPECTED_MAX_GRADE_DESC_CS2103T + EXPECTED_MIN_GRADE_DESC_CS2103T
                + TAG_DESC_FRIEND + SEMESTER_DESC_CS2103T + EXPECTED_MAX_GRADE_DESC_CS2103T
                + EXPECTED_MIN_GRADE_DESC_CS2103T + TAG_DESC_FRIEND + SEMESTER_DESC_CS1010
                + EXPECTED_MAX_GRADE_DESC_CS1010 + EXPECTED_MIN_GRADE_DESC_CS1010 + TAG_DESC_HUSBAND;

        EditCommand.EditModuleTakenDescriptor descriptor = new EditModuleTakenDescriptorBuilder()
                .withSemester(VALID_SEMESTER_CS1010)
                .withExpectedMinGrade(VALID_EXPECTED_MIN_GRADE_CS1010)
                .withExpectedMaxGrade(VALID_EXPECTED_MAX_GRADE_CS1010)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_MODULE_TAKEN;
        String userInput = targetIndex.getOneBased() + INVALID_SEMESTER_DESC + SEMESTER_DESC_CS1010;
        EditCommand.EditModuleTakenDescriptor descriptor = new EditModuleTakenDescriptorBuilder()
                .withSemester(VALID_SEMESTER_CS1010).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EXPECTED_MIN_GRADE_DESC_CS1010
                + INVALID_SEMESTER_DESC + EXPECTED_MAX_GRADE_DESC_CS1010
                + SEMESTER_DESC_CS1010;
        descriptor = new EditModuleTakenDescriptorBuilder()
                .withSemester(VALID_SEMESTER_CS1010)
                .withExpectedMinGrade(VALID_EXPECTED_MIN_GRADE_CS1010)
                .withExpectedMaxGrade(VALID_EXPECTED_MAX_GRADE_CS1010).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_MODULE_TAKEN;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditModuleTakenDescriptor descriptor = new EditModuleTakenDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
