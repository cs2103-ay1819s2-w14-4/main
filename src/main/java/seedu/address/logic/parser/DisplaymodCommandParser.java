package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.DisplaymodCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.CodeContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new DisplaymodCommand object
 */
//TODO: varying arguments to be used so that searches can be more streamlined i.e. use of prefixes
public class DisplaymodCommandParser implements Parser<DisplaymodCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DisplaymodCommand
     * and returns an DisplaymodCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DisplaymodCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DisplaymodCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new DisplaymodCommand(new CodeContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }
}
