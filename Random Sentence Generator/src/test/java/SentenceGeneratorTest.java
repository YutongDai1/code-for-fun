import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SentenceGeneratorTest {

    SentenceGenerator sentenceGenerator;
    String fileName = "grammar/poem_grammar.json";
    JsonGrammar grammar;
    HashMap<String, ArrayList<String>> grammarMap;
    JsonFileReader jsonFileReader;
    String attributeKey;
    SentenceGenerator validateSentenceGenerator;


    @BeforeEach
    void setUp() {
        jsonFileReader = new JsonFileReader(fileName);
        grammar = jsonFileReader.readJsonFile();
        grammarMap = grammar.getGrammar();
        sentenceGenerator = new SentenceGenerator(grammar);
        validateSentenceGenerator = new SentenceGenerator(new JsonGrammar());
        attributeKey = "start";
    }

    @Test
    void generateRandomSentence() {
        assertEquals(true, sentenceGenerator.generateRandomSentence(attributeKey).length() > 0);
        assertEquals(false, validateSentenceGenerator.generateRandomSentence("attributeKey").length() > 0);
        String testAttributeKey = "object";
        ArrayList<String> testAttributeValueList = new ArrayList<>();
        testAttributeValueList.add("waves");
        testAttributeValueList.add("big yellow flowers");
        testAttributeValueList.add("slugs");
        assertEquals(true, testAttributeValueList.contains(sentenceGenerator.generateRandomSentence(testAttributeKey)));
    }

    @Test
    void getAttributeValue() {
        assertEquals(true, sentenceGenerator.getAttributeValue(attributeKey).length() > 0);
    }

    @Test
    void isGrammarEmpty() {
        assertEquals(true, sentenceGenerator.isGrammarNotEmpty());
        assertEquals(false, validateSentenceGenerator.isGrammarNotEmpty());
    }

    @Test
    void replacePlaceholder() {
        // assertEquals(true, sentenceGenerator.replaceAttributeValue("sigh <adverb>").length() > 0);
        // System.out.println(sentenceGenerator.replaceAttributeValue("sigh <adverb>"));
    }

    @Test
    void generateRandomNumber() {
        assertEquals(true, sentenceGenerator.generateRandomNumber(3) <= 3);
    }

    @Test
    void getJsonGrammar() {
        assertEquals(grammar, sentenceGenerator.getJsonGrammar());
    }

    @Test
    void setJsonGrammar() {
        sentenceGenerator.setJsonGrammar(grammar);
        assertEquals(grammar, sentenceGenerator.getJsonGrammar());
    }

    @Test
    void testToString() {
        sentenceGenerator.generateRandomSentence(attributeKey);
        assertEquals("SentenceGenerator{jsonGrammar=JsonGrammar{grammar={start=[The <object> <verb> tonight.], verb=[sigh <adverb>, portend like <object>, die <adverb>], adverb=[warily, grumpily], object=[waves, big yellow flowers, slugs]}}}", sentenceGenerator.toString());
    }

    @Test
    void testEquals() {
        assertEquals(true, sentenceGenerator.equals(sentenceGenerator));
        assertEquals(false, sentenceGenerator.equals(validateSentenceGenerator));
        assertEquals(false, sentenceGenerator.equals(null));
        assertEquals(false, sentenceGenerator.equals(new Object()));
        assertEquals(false, sentenceGenerator.equals(new SentenceGenerator(new JsonGrammar())));
    }

    @Test
    void testHashCode() {
        assertEquals(sentenceGenerator.hashCode() == sentenceGenerator.hashCode(), true);
    }
}