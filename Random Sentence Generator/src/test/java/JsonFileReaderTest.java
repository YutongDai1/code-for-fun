import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class JsonFileReaderTest {

    private JsonFileReader jsonFileReader;
    // root directory of the project
    String fileName = "grammar/poem_grammar.json";
    private JsonFileReader validateJsonFileReader;

    @BeforeEach
    void setUp() {
        jsonFileReader = new JsonFileReader(fileName);
        validateJsonFileReader = new JsonFileReader("fileName");
        System.out.println(jsonFileReader.readJsonFile().getGrammar());
    }

    @Test
    void readJsonFile() {
        JsonGrammar grammar = jsonFileReader.readJsonFile();
        HashMap<String, ArrayList<String>> grammarMap = grammar.getGrammar();
        // {start=[The <object> <verb> tonight.], verb=[sigh <adverb>, portend like <object>, die <adverb>],
        // adverb=[warily, grumpily], object=[waves, big yellow flowers, slugs]}
        System.out.println(grammarMap);
        assertEquals(4, grammarMap.size());
        // assertEquals(stringListHashMap.get("grammarTitle"), null);
        assertEquals(grammarMap.get("start").toString(), "[The <object> <verb> tonight.]");
        assertEquals(grammarMap.get("abc"), null);

        // error case
        validateJsonFileReader.setFileName("invalid.json");
        validateJsonFileReader.readJsonFile();
    }

    @Test
    void removeRedundantData() {
        jsonFileReader.setFileName("grammar/poem_grammar_without_title_des.json");
        JsonGrammar grammar = jsonFileReader.readJsonFile();
        HashMap<String, ArrayList<String>> stringListHashMap = grammar.getGrammar();
        jsonFileReader.removeRedundantData(stringListHashMap);
    }

    @Test
    void testToString() {
        assertEquals("JsonFileReader{fileName='grammar/poem_grammar.json'}", jsonFileReader.toString());
    }

    @Test
    void testEquals() {
        assertEquals(false, jsonFileReader.equals(validateJsonFileReader));
        assertEquals(true, jsonFileReader.equals(jsonFileReader));

        assertEquals(false, jsonFileReader.equals(null));
        assertEquals(false, jsonFileReader.equals(new Object()));
        assertEquals(false, jsonFileReader.equals(new JsonFileReader(fileName+"5")));
    }

    @Test
    void testHashCode() {
        assertNotEquals(jsonFileReader.hashCode(), validateJsonFileReader.hashCode());
    }
}