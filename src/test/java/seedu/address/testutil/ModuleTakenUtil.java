package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPECTED_MAX_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPECTED_MIN_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FINISHED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE_HOUR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_INFO_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEMESTER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditModuleTakenDescriptor;
import seedu.address.logic.commands.FindCommand.FindModuleDescriptor;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.moduletaken.ModuleTaken;
import seedu.address.model.tag.Tag;

/**
 * A utility class for ModuleTaken.
 */
public class ModuleTakenUtil {

    /**
     * Returns an add command string for adding the {@code moduleTaken}.
     */
    public static String getAddCommand(ModuleTaken moduleTaken) {
        return AddCommand.COMMAND_WORD + " " + getModuleTakenDetails(moduleTaken);
    }

    /**
     * Returns the part of command string for the given {@code moduleTaken}'s details.
     */
    public static String getModuleTakenDetails(ModuleTaken moduleTaken) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_MODULE_INFO_CODE + moduleTaken.getModuleInfoCode().toString() + " ");
        sb.append(PREFIX_SEMESTER + moduleTaken.getSemester().toString() + " ");
        sb.append(PREFIX_EXPECTED_MIN_GRADE + moduleTaken.getExpectedMinGrade().name() + " ");
        sb.append(PREFIX_EXPECTED_MAX_GRADE + moduleTaken.getExpectedMaxGrade().name() + " ");
        moduleTaken.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditModuleTakenDescriptor}'s details.
     */
    public static String getEditModuleTakenDescriptorDetails(EditModuleTakenDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getModuleInfoCode().ifPresent(infoCode ->
                sb.append(PREFIX_MODULE_INFO_CODE).append(infoCode.toString()).append(" "));
        descriptor.getSemester().ifPresent(semester ->
                sb.append(PREFIX_SEMESTER)
                        .append(semester.name())
                        .append(" "));
        descriptor.getExpectedMinGrade().ifPresent(expectedMinGrade ->
                sb.append(PREFIX_EXPECTED_MIN_GRADE)
                        .append(expectedMinGrade.name())
                        .append(" "));
        descriptor.getExpectedMaxGrade().ifPresent(expectedMaxGrade ->
                sb.append(PREFIX_EXPECTED_MAX_GRADE)
                        .append(expectedMaxGrade.name())
                        .append(" "));
        descriptor.getLectureHour().ifPresent(lectureHour ->
                sb.append(PREFIX_LECTURE_HOUR)
                        .append(lectureHour.toString())
                        .append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code FindModuleDescriptor}'s details.
     */
    public static String getFindModuleDescriptorDetails(FindModuleDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getSubCode().ifPresent(code -> sb.append(PREFIX_MODULE_INFO_CODE).append(code).append(" "));
        descriptor.getSemester().ifPresent(semester ->
                sb.append(PREFIX_SEMESTER)
                        .append(semester.name())
                        .append(" "));
        descriptor.getGrade().ifPresent(grade ->
                sb.append(PREFIX_GRADE)
                        .append(grade.name())
                        .append(" "));
        descriptor.isFinished().ifPresent(isFinished ->
                sb.append(PREFIX_FINISHED)
                        .append(ParserUtil.booleanToFinishedStatus(isFinished))
                        .append(" "));

        return sb.toString();
    }
}
