package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EXPECTED_MAX_GRADE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EXPECTED_MAX_GRADE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EXPECTED_MIN_GRADE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EXPECTED_MIN_GRADE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EXPECTED_MAX_GRADE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EXPECTED_MIN_GRADE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SEMESTER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.SEMESTER_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.SEMESTER_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPECTED_MAX_GRADE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPECTED_MAX_GRADE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPECTED_MIN_GRADE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPECTED_MIN_GRADE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SEMESTER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SEMESTER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Grade;
import seedu.address.model.person.Name;
import seedu.address.model.person.Semester;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditPersonDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_SEMESTER_DESC, Semester.MESSAGE_CONSTRAINTS); // invalid semester
        assertParseFailure(parser, "1" + INVALID_EXPECTED_MIN_GRADE_DESC, Grade.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_EXPECTED_MAX_GRADE_DESC, Grade.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid semester followed by valid email
        assertParseFailure(parser, "1" + INVALID_SEMESTER_DESC
                + EXPECTED_MIN_GRADE_DESC_AMY, Semester.MESSAGE_CONSTRAINTS);

        // valid semester followed by invalid semester. The test case for invalid semester followed by valid semester
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + SEMESTER_DESC_BOB
                + INVALID_SEMESTER_DESC, Semester.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND
                + TAG_DESC_HUSBAND + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND
                + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND
                + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC
                + INVALID_EXPECTED_MIN_GRADE_DESC + VALID_EXPECTED_MAX_GRADE_AMY
                + VALID_SEMESTER_AMY, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + SEMESTER_DESC_BOB + TAG_DESC_HUSBAND
                + EXPECTED_MIN_GRADE_DESC_AMY + EXPECTED_MAX_GRADE_DESC_AMY
                + NAME_DESC_AMY + TAG_DESC_FRIEND;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withSemester(VALID_SEMESTER_BOB)
                .withExpectedMinGrade(VALID_EXPECTED_MIN_GRADE_AMY)
                .withExpectedMaxGrade(VALID_EXPECTED_MAX_GRADE_AMY)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + SEMESTER_DESC_BOB + EXPECTED_MIN_GRADE_DESC_AMY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withSemester(VALID_SEMESTER_BOB)
                .withExpectedMinGrade(VALID_EXPECTED_MIN_GRADE_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // semester
        userInput = targetIndex.getOneBased() + SEMESTER_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withSemester(VALID_SEMESTER_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EXPECTED_MIN_GRADE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder()
                .withExpectedMinGrade(VALID_EXPECTED_MIN_GRADE_AMY)
                .build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + EXPECTED_MAX_GRADE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder()
                .withExpectedMaxGrade(VALID_EXPECTED_MAX_GRADE_AMY)
                .build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_FRIEND;
        descriptor = new EditPersonDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + SEMESTER_DESC_AMY
                + EXPECTED_MAX_GRADE_DESC_AMY + EXPECTED_MIN_GRADE_DESC_AMY
                + TAG_DESC_FRIEND + SEMESTER_DESC_AMY + EXPECTED_MAX_GRADE_DESC_AMY
                + EXPECTED_MIN_GRADE_DESC_AMY + TAG_DESC_FRIEND + SEMESTER_DESC_BOB
                + EXPECTED_MAX_GRADE_DESC_BOB + EXPECTED_MIN_GRADE_DESC_BOB + TAG_DESC_HUSBAND;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withSemester(VALID_SEMESTER_BOB)
                .withExpectedMinGrade(VALID_EXPECTED_MIN_GRADE_BOB)
                .withExpectedMaxGrade(VALID_EXPECTED_MAX_GRADE_BOB)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + INVALID_SEMESTER_DESC + SEMESTER_DESC_BOB;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withSemester(VALID_SEMESTER_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EXPECTED_MIN_GRADE_DESC_BOB
                + INVALID_SEMESTER_DESC + EXPECTED_MAX_GRADE_DESC_BOB
                + SEMESTER_DESC_BOB;
        descriptor = new EditPersonDescriptorBuilder()
                .withSemester(VALID_SEMESTER_BOB)
                .withExpectedMinGrade(VALID_EXPECTED_MIN_GRADE_BOB)
                .withExpectedMaxGrade(VALID_EXPECTED_MAX_GRADE_BOB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
