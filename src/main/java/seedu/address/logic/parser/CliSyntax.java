package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");

    /*Prefix related to finding Module Information */
    public static final Prefix PREFIX_MODCODE = new Prefix("c/");
    public static final Prefix PREFIX_MODNAME = new Prefix("n/");
    public static final Prefix PREFIX_MODDEPT = new Prefix("d/");

}
