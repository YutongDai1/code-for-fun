import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserInterfaceTest {

    private UserInterface userInterface;
    String fileName = "grammar/poem_grammar.json";
    String rootPath;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    InputStream in;
    String input;

    @BeforeEach
    void setUp() {
        rootPath = new File("").getAbsolutePath();
        userInterface = new UserInterface();
    }

    @Test
    void setFileList() {
        userInterface.setFileList();
        assertEquals(true, userInterface.getFileList().size() > 0);
        ArrayList<String> testFileList = new ArrayList<>();
        testFileList.add("insult_grammar.json");
        testFileList.add("poem_grammar.json");
        testFileList.add("poem_grammar_without_title_des.json");
        testFileList.add("term_paper_grammar.json");
        assertEquals(testFileList, userInterface.getFileList());

    }

    @Test
    void testGetFileList() {
        userInterface.setFileList();
        assertEquals(true, userInterface.getFileList().size() > 0);
        System.out.println(userInterface.getFileList());
    }

    @Test
    void testRun() {
        // error number case
        try {
            System.setOut(new PrintStream(outContent));
            System.setErr(new PrintStream(errContent));
            String input = "6" + System.getProperty("line.separator") + "q" + System.getProperty("line.separator");
            InputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
            userInterface.run();
        } finally {
            System.setOut(originalOut);
        }
        assertEquals("Loading grammars ...\n" +
                "\n" +
                "The following grammars are available: \n" +
                "\n" +
                "1. insult_grammar.json\n" +
                "2. poem_grammar.json\n" +
                "3. poem_grammar_without_title_des.json\n" +
                "4. term_paper_grammar.json\n" +
                "\n" +
                "Which would you like to use? (q to quit)\n" +
                "The following grammars are available: \n" +
                "\n" +
                "1. insult_grammar.json\n" +
                "2. poem_grammar.json\n" +
                "3. poem_grammar_without_title_des.json\n" +
                "4. term_paper_grammar.json\n" +
                "\n" +
                "Which would you like to use? (q to quit)\n" +
                "Goodbye my friend!\n", outContent.toString());
        assertEquals("Input is not defined!\n", errContent.toString());
    }
    @Test
    void testRun2() {

        // blank case
        try {
            System.setOut(new PrintStream(outContent));
            input = "q" + System.getProperty("line.separator");
            in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
            userInterface.run();
        } finally {
            System.setOut(originalOut);
        }
        assertEquals("Loading grammars ...\n" +
                "\n" +
                "The following grammars are available: \n" +
                "\n" +
                "1. insult_grammar.json\n" +
                "2. poem_grammar.json\n" +
                "3. poem_grammar_without_title_des.json\n" +
                "4. term_paper_grammar.json\n" +
                "\n" +
                "Which would you like to use? (q to quit)\n" +
                "Goodbye my friend!\n", outContent.toString());
    }

    @Test
    void testRun3() {
        try {
            System.setOut(new PrintStream(outContent));
            System.setErr(new PrintStream(errContent));
            input = "2" + System.getProperty("line.separator") + "Y" + System.getProperty("line.separator") + "3" + System.getProperty("line.separator") + "N" + System.getProperty("line.separator")+ "q" + System.getProperty("line.separator");
            in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
            userInterface.run();
        } finally {
            System.setOut(originalOut);
        }
        assertEquals("'3' is wrong Input! Please try again.\n", errContent.toString());
        assertEquals(true, outContent.toString().contains("Loading grammars ..."));
    }

    @Test
    void testSetJsonFileReader() {
        userInterface.setJsonFileReader(new JsonFileReader(fileName));
        assertEquals(fileName, userInterface.getJsonFileReader().getFileName());
    }

    @Test
    void testSetJsonGrammar() {
        userInterface.setJsonGrammar(new JsonGrammar());
        assertEquals(true, userInterface.getJsonGrammar() != null);
    }

    @Test
    void testSetSentenceGenerator() {
        userInterface.setSentenceGenerator(new SentenceGenerator(new JsonGrammar()));
        assertEquals(true, userInterface.getSentenceGenerator() != null);
    }

    @Test
    void testToString() {
        userInterface.setFileList();
        assertEquals("UserInterface{fileList=[insult_grammar.json, poem_grammar.json, poem_grammar_without_title_des.json, term_paper_grammar.json], jsonFileReader=JsonFileReader{fileName='null'}, jsonGrammar=JsonGrammar{grammar={}}, sentenceGenerator=SentenceGenerator{jsonGrammar=JsonGrammar{grammar={}}}}", userInterface.toString());
    }

    @Test
    void testHashCode() {
        userInterface.setFileList();
        assertEquals(1719722227, userInterface.hashCode());
    }

    @Test
    void testEquals() {
        userInterface.setFileList();
        UserInterface userInterface2 = new UserInterface();
        userInterface2.setFileList();
        assertEquals(true, userInterface.equals(userInterface2));
        assertEquals(true, userInterface.equals(userInterface));
        assertEquals(false, userInterface.equals(null));
        assertEquals(false, userInterface.equals(new Object()));
    }
}