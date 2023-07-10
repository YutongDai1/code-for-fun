package sequentialSolution;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class OutputWriterTest {
    OutputWriter outputWriter;
    CsvFileReader csvFileReader;
    Path outputDir = Paths.get("src" + File.separator + "main" + File.separator + "resources" + File.separator + "output");
    Path courseData = Paths.get("src" + File.separator + "main" + File.separator + "resources" + File.separator + "csv" + File.separator + "studentVleForTest.csv");
    HashMap<String, Map<Integer, Integer>> processedDataMap;

    @BeforeEach
    void setUp() {
        outputWriter = new OutputWriter();
        csvFileReader = new CsvFileReader();
        processedDataMap = csvFileReader.readCsvFile(courseData);
    }

    @Test
    void writeOutputDirectory() throws IOException {
        outputWriter.writeOutputDirectory(processedDataMap, outputDir);
        for (File file : outputDir.toFile().listFiles()) {
            if (file.isFile()) {
                Files.delete(file.toPath());
            }
        }
    }

    @Test
    void testToString() {
        String s = "OutputWriter{}";
        assertEquals(s, outputWriter.toString());
    }

    @Test
    void testEquals() {
        assertEquals(outputWriter, outputWriter);
        assertNotEquals(outputWriter, null);
        assertNotEquals(outputWriter.equals(null), null);
        Integer a = 1;
        assertFalse(outputDir.equals(a));
        assertEquals(new OutputWriter(), outputWriter);
        assertNotEquals(new Object(), outputWriter);
    }

    @Test
    void testHashCode() {
        assertEquals(-1069606058, outputWriter.hashCode());
    }

    @Test
    void write() throws IOException {
        outputWriter.write("", "", null, outputDir);
        for (File file : outputDir.toFile().listFiles()) {
            if (file.isFile()) {
                Files.delete(file.toPath());
            }
        }
    }


    @Test
    void isOutputDirExists() throws IOException {

        Path outputDir1 = Paths.get("src" + File.separator + "main" + File.separator + "resources" + File.separator + "output");
        outputWriter.isOutputDirExists(outputDir1);
        //Files.deleteIfExists(outputDir1);
        //outputWriter.isOutputDirExists(outputDir1);
    }
}