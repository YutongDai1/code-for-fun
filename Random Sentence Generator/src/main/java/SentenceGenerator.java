import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * SentenceGenerator class generates sentence randomly
 */
public class SentenceGenerator {

    private JsonGrammar jsonGrammar;
    private static final String NON_TERMINAL_PATTERN = "<(.+?)>";
    private static final String KEY_PREFIX = "<";
    private static final String KEY_SUFFIX = ">";
    private static final String REGEX_SPACE = "\\s+";
    private static final String REGEX_SPACE_REPLACE = " ";

    private static final String REGEX_COMMA = "(\s+?),";
    private static final String REGEX_COMMA_REPLACE = ",";

    private static final String REGEX_PERIOD = "(\s+?)[.]";
    private static final String REGEX_PERIOD_REPLACE = ".";
    private static final String REGEX_QUO = "(\s+?)'";
    private static final String REGEX_QUO_REPLACE = "\'";

    /**
     * Default constructor for SentenceGenerator class, requires JsonGrammar object to be passed
     *
     * @param jsonGrammar JsonGrammar object created from JSONFileParser
     */
    public SentenceGenerator(JsonGrammar jsonGrammar) {
        this.jsonGrammar = jsonGrammar;
    }

    /**
     * random generate a sentence from the grammar
     *
     * @param jsonKey String jsonKey to get the value of the key in the JSON data
     * @return the generated sentence
     */
    public String generateRandomSentence(String jsonKey) {
        isGrammarNotEmpty();
        String[] res = new String[1];
        res[0] = "";
        // whether JSON data with the key attribute exists
        try {
            if (jsonGrammar.getGrammar().containsKey(jsonKey)) {
                // get the value of key attribute in the JSON data
                String value = getAttributeValue(jsonKey);
                // sentence is equal to the value after replacing the placeholder
                res[0] = value;
                // replace the placeholder with the value of the attribute in the JSON data recursively
                replaceAttributeValue(res);
            } else {
                throw new IllegalArgumentException(InvalidArgsException.NOT_DEFINED);
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
        res[0] = res[0].replaceAll(REGEX_SPACE, REGEX_SPACE_REPLACE);
        res[0] = res[0].replaceAll(REGEX_COMMA, REGEX_COMMA_REPLACE);
        res[0] = res[0].replaceAll(REGEX_PERIOD, REGEX_PERIOD_REPLACE);
        res[0] = res[0].replaceAll(REGEX_QUO, REGEX_QUO_REPLACE);
        return res[0];
    }

    /**
     * replace the placeholder with the value of the attribute in the JSON data recursively
     *
     * @param res the value of the attribute
     */
    public void replaceAttributeValue(String[] res) {
        // find pattern in the value
        Pattern pattern = Pattern.compile(NON_TERMINAL_PATTERN);
        Matcher matcher = pattern.matcher(res[0]);
        // match the pattern and replace it with the value of the attribute in the JSON data recursively
        while (matcher.find()) {
            // get the placeholder
            String key = matcher.group();
            StringBuilder sb = new StringBuilder(key);
            sb.deleteCharAt(0);
            sb.deleteCharAt(sb.length() - 1);
            key = sb.toString();
            // get the value of placeholder
            String newAttributeValue = generateRandomSentence(key);
            // replace the placeholder with the value of placeholder
            res[0] = res[0].replaceAll(KEY_PREFIX + key + KEY_SUFFIX, newAttributeValue);
        }
    }

    /**
     * Get the value of the key attribute
     *
     * @param jsonKey the key of the attribute
     * @return the value of the attribute
     */
    public String getAttributeValue(String jsonKey) {
        ArrayList<String> valueList = jsonGrammar.getGrammarValueList(jsonKey);
        return valueList.get(generateRandomNumber(valueList.size()));
    }

    /**
     * Judge if the grammar empty
     *
     * @return true if the grammar is not empty
     * @throws IllegalArgumentException if the grammar is empty
     */
    public boolean isGrammarNotEmpty() {
        if (jsonGrammar.getGrammar().isEmpty()) {
            try {
                throw new InvalidArgsException(InvalidArgsException.EMPTY_GRAMMAR);
            } catch (InvalidArgsException e) {
                System.err.println(e.getMessage());
            }
            return false;
        } else {
            return true;
        }
    }


    /**
     * generate an integer random number from 0 to the max number
     *
     * @param boundary the boundary of the random number
     * @return the random number
     */
    public int generateRandomNumber(int boundary) {
        // Reference: https://stackoverflow.com/questions/738629/math-random-versus-random-nextintint
        // Random.nextInt(n) uses Random.next() less than twice on average - it uses it once
        return new Random().nextInt(boundary);
    }


    /**
     * getter method for jsonGrammar
     *
     * @return jsonGrammar
     */
    public JsonGrammar getJsonGrammar() {
        return this.jsonGrammar;
    }

    /**
     * setter method for jsonGrammar
     *
     * @param jsonGrammar JsonGrammar jsonGrammar
     */
    public void setJsonGrammar(JsonGrammar jsonGrammar) {
        this.jsonGrammar = jsonGrammar;
    }

    /**
     * toString method for SentenceGenerator class
     *
     * @return String
     */
    @Override
    public String toString() {
        return "SentenceGenerator{" +
                "jsonGrammar=" + jsonGrammar +
                '}';
    }

    /**
     * override the equals method
     *
     * @param o Object
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SentenceGenerator)) {
            return false;
        }
        SentenceGenerator that = (SentenceGenerator) o;
        return Objects.equals(getJsonGrammar(), that.getJsonGrammar());
    }

    /**
     * override the hashCode method
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(getJsonGrammar());
    }
}
