package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_SEMESTER = new Prefix("s/");
    public static final Prefix PREFIX_EXPECTED_MIN_GRADE = new Prefix("min/");
    public static final Prefix PREFIX_EXPECTED_MAX_GRADE = new Prefix("max/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");

    /*Prefix related to finding Module Information */
    public static final Prefix PREFIX_MODCODE = new Prefix("c/");
    public static final Prefix PREFIX_MODNAME = new Prefix("n/");
    public static final Prefix PREFIX_MODDEPT = new Prefix("d/");

}
