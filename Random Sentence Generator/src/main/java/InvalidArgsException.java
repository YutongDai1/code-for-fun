/**
 * InvalidArgsException is thrown when the arguments passed to the program are invalid.
 */
public class InvalidArgsException extends Exception {

    /**
     * invalid args message
     */
    public static final String INVALID_ARGS = "Invalid arguments. Please enter valid arguments.";
    /**
     * NOT_DEFINED non-terminal
     */
    public static final String NOT_DEFINED = "Sorry, encountering a case which has no result, please try again!";
    /**
     * empty args message
     */
    public static final String EMPTY_GRAMMAR = "The grammar is empty. Please enter a valid grammar.";

    /**
     * Not defined input
     */
    public static final String INPUT_NOT_DEFINED = "Input is not defined!";

    /**
     * Constructor for InvalidArgsException and prints the error message.
     *
     * @param message the error message
     */
    public InvalidArgsException(String message) {
        super(message);
    }

}
