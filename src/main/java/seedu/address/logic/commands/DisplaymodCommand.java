package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODCODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODDEPT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODNAME;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.CodeContainsKeywordsPredicate;
import seedu.address.model.Model;


/**
 * Command to show the module information being searched for.
 * Limitations: only can find a list of module per command and find only based on Keyword search
 *              and yet to use prefix allocated for search; for later versions
 */
public class DisplaymodCommand extends Command {

    public static final String COMMAND_WORD = "displaymod";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds one module Information based on given "
            + "parameters. "
            + "Parameters: "
            + PREFIX_MODCODE + "MODULE CODE"
            + PREFIX_MODNAME + "MODULE NAME"
            + PREFIX_MODDEPT + "MODULE DEPARTMENT"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODCODE + "CS2103T"
            + PREFIX_MODNAME + "Software Engineering"
            + PREFIX_MODDEPT + "Computer Science";

    public static final String MESSAGE_SUCCESS = "Module Found : \n"
                                                 + "Module Code : %1$s \n"
                                                 + "Module Title : %2$s \n"
                                                 + "Department : %3$s \n"
                                                 + "Module Credits : %4$s \n"
                                                 + "Module Description : %5$s \n"
                                                 + "WorkLoad : %6$s \n"
                                                 + "Preclusions : %7$s \n"
                                                 + "Prerequisites : %8$s ";

    public static final String MESSAGE_NO_MODULE = "Unable to find the Module. Please check the parameters";

    private final CodeContainsKeywordsPredicate keywords;

    public DisplaymodCommand(CodeContainsKeywordsPredicate keywords) {
        this.keywords = keywords;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        model.updateDisplayList(keywords);

        if (model.getDisplayList().isEmpty()) {
            throw new CommandException(MESSAGE_NO_MODULE);
        }
        return new CommandResult(generateResultString(model));
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DisplaymodCommand // instanceof handles nulls
                && keywords.equals(((DisplaymodCommand) other).keywords)); // state check
    }


    /**
     * returns a String for the Command result if the list is not empty
     * @param model
     * @return String result
     */
    public static String generateResultString(Model model) {
        String result = "";

        for (int i = 0; i < model.getDisplayList().size(); i++) {
            result = result + "\n \n" + String.format(MESSAGE_SUCCESS,
                                                      model.getDisplayList().get(i).getCode(),
                                                      model.getDisplayList().get(i).getTitle(),
                                                      model.getDisplayList().get(i).getDepartment(),
                                                      Double.toString(model.getDisplayList().get(i).getCredits()),
                                                      model.getDisplayList().get(i).getDescription(),
                                                      model.getDisplayList().get(i).getWorkLoad(),
                                                      model.getDisplayList().get(i).getPreclusions(),
                                                      model.getDisplayList().get(i).getPrerequisites());
        }
        return result;
    }

}
