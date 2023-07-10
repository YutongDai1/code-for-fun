package concurrentSolution;

import java.io.File;
import java.util.regex.Pattern;

/**
 * enum
 */
public enum Regex {
    /**
     * QUOTE_REGEX
     */
    QUOTE_REGEX("\""),
    /**
     * CSV_SUFFIX
     */
    CSV_SUFFIX(".csv"),
    /**
     * EMPTY_STRING
     */
    EMPTY_STRING(""),
    /**
     * UNDERSCORE
     */
    UNDERSCORE("_"),
    /**
     * DASH
     */
    DASH("-"),
    /**
     * POISON_PILL
     */
    POISON_PILL("POISON_PILL"),
    /**
     * QUOTE_REGEX_EXP
     */
    QUOTE_REGEX_EXP(Pattern.quote("\"") + "(.*?)" + Pattern.quote("\"")),
    /**
     * CSV_FILE_PATH
     */
    CSV_FILE_PATH("src" + File.separator + "main" + File.separator + "resources" + File.separator + "csv"),
    /**
     * OUTPUT_FILE_PATH
     */
    OUTPUT_FILE_PATH("src" + File.separator + "main" + File.separator + "resources" + File.separator + "output" + File.separator),
    /**
     * ACTIVITY
     */
    ACTIVITY("activity");
    private final String regex;

    /**
     * @param regex constructor
     */
    Regex(String regex) {
        this.regex = regex;
    }

    /**
     * @return getRegex
     */
    public String getRegex() {
        return regex;
    }

}
