import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    Main main;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    InputStream in;
    String input;

    @BeforeEach
    void setUp() {
        main = new Main();
    }

    @Test
    void main() {
        try {
            System.setOut(new PrintStream(outContent));
            input = "q" + System.getProperty("line.separator");
            in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
            main.main(new String[0]);
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
}