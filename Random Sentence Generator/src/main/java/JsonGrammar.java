import java.util.ArrayList;
import java.util.HashMap;

/**
 * JsonGrammar Class contains json file's information as a map
 * without redundant data like grammarTitle and grammarDesc
 */
public class JsonGrammar {

    private HashMap<String, ArrayList<String>> grammar;

    /**
     * Default constructor for JsonGrammar class
     * @param grammar HashMap contains json file's information
     */
    public JsonGrammar(HashMap<String, ArrayList<String>> grammar) {
        this.grammar = grammar;
    }

    /**
     * no-arg constructor for JsonGrammar
     */
    public JsonGrammar() {
        this.grammar = new HashMap<>();
    }

    /**
     * getter for grammar value by key
     *
     * @param key key of the grammar
     * @return grammar
     */
    public ArrayList<String> getGrammarValueList(String key) {
        try {
            if (grammar.containsKey(key)) {
                return grammar.get(key);
            } else {
                throw new IllegalArgumentException(InvalidArgsException.INVALID_ARGS);
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    /**
     * getter method for grammar
     *
     * @return grammar
     */
    public HashMap<String, ArrayList<String>> getGrammar() {
        return this.grammar;
    }

    /**
     * setter method for grammar
     *
     * @param grammar HashMap grammar
     */
    public void setGrammar(HashMap<String, ArrayList<String>> grammar) {
        this.grammar = grammar;
    }

    /**
     * override the toString method
     *
     * @return String
     */
    @Override
    public String toString() {
        return "JsonGrammar{" +
                "grammar=" + grammar +
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
        if (o == this)
            return true;
        if (!(o instanceof JsonGrammar)) {
            return false;
        }
        JsonGrammar jsonGrammar = (JsonGrammar) o;
        return grammar.equals(jsonGrammar.grammar);
    }

    /**
     * override the hashCode method
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return grammar.hashCode();
    }
}

