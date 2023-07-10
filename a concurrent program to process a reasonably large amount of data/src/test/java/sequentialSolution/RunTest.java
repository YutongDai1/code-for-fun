package sequentialSolution;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RunTest {

    Run run;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    InputStream in;
    String input;

    @BeforeEach
    void setUp() {
        run = new Run();
        //System.setOut(new PrintStream(outContent));
    }

    @Test
    void main()  {
        try {
            System.setOut(new PrintStream(outContent));
            input = "q" + System.getProperty("line.separator");
            in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
            run.main(new String[0]);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            System.setOut(originalOut);
        }

    }

}