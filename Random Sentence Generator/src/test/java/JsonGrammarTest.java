import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonGrammarTest {

    private JsonGrammar jsonGrammar;
    private JsonGrammar validateJsonGrammar;
    private JsonFileReader jsonFileReader;
    String fileName = "grammar/poem_grammar.json";
    JsonGrammar grammar;
    HashMap<String, ArrayList<String>> grammarMap;

    @BeforeEach
    void setUp() {
        jsonFileReader = new JsonFileReader(fileName);
        grammar = jsonFileReader.readJsonFile();
        grammarMap = grammar.getGrammar();
        jsonGrammar = new JsonGrammar(grammarMap);
        validateJsonGrammar = new JsonGrammar();
    }

    @Test
    void getGrammarValue() {
        assertEquals(grammar.getGrammarValueList("start").toString(), "[The <object> <verb> tonight.]");
        assertEquals(grammar.getGrammarValueList("abc"), null);
    }

    @Test
    void getGrammar() {
        assertEquals(grammarMap, grammar.getGrammar());
    }

    @Test
    void setGrammar() {
        validateJsonGrammar.setGrammar(grammarMap);
        assertEquals(grammarMap, validateJsonGrammar.getGrammar());
    }

    @Test
    void testEquals() {
        assertEquals(false, jsonGrammar.equals(validateJsonGrammar));
        assertEquals(false, jsonGrammar.equals(null));
        assertEquals(true, jsonGrammar.equals(jsonGrammar));
    }

    @Test
    void testHashCode() {
        assertEquals(true, jsonGrammar.hashCode() != 0);
    }

    @Test
    void testToString() {
        assertEquals(true, jsonGrammar.toString() != null);
    }

}