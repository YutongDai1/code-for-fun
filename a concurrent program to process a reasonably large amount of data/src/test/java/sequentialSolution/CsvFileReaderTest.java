package sequentialSolution;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CsvFileReaderTest {
    CsvFileReader csvFileReader;
    Path courseData = Paths.get("src" + File.separator + "main" + File.separator + "resources" + File.separator + "csv" + File.separator + "studentVleForTest.csv");

    @BeforeEach
    void setUp() {
        csvFileReader = new CsvFileReader();
    }

    @Test
    void readCsvFile() {
        csvFileReader.readCsvFile(courseData);
        csvFileReader.readCsvFile(Path.of("s"));
    }

    @Test
    void getProcessedDataMap() {
        HashMap<String, Map<Integer, Integer>> processedDataMap = csvFileReader.readCsvFile(courseData);
        assertEquals(processedDataMap, csvFileReader.getProcessedDataMap());
    }

    @Test
    void setProcessedDataMap() {
        HashMap<String, Map<Integer, Integer>> processedDataMap = new HashMap<>();
        csvFileReader.setProcessedDataMap(processedDataMap);
    }

    @Test
    void testToString() {
        String s = "CsvFileReader [processedDataMap={}]";
        assertEquals(s, csvFileReader.toString());
    }

    @Test
    void testEquals() {
        assertEquals(csvFileReader, csvFileReader);
        assertFalse(csvFileReader.equals(null));
        Integer a = 1;
        assertFalse(csvFileReader.equals(a));
        CsvFileReader csvFileReader1 = new CsvFileReader();
        assertEquals(csvFileReader1, csvFileReader);
        csvFileReader1.readCsvFile(courseData);
        assertNotEquals(csvFileReader, csvFileReader1);
        csvFileReader.setProcessedDataMap(null);
        assertFalse(csvFileReader.equals(csvFileReader1));
    }

    @Test
    void testHashCode() {
        assertEquals(31, csvFileReader.hashCode());
        CsvFileReader csvFileReader1 = new CsvFileReader();
        csvFileReader1.setProcessedDataMap(null);
        assertEquals(csvFileReader.hashCode(), csvFileReader1.hashCode());
    }

    @Test
    void processCsvFile() {
        csvFileReader.processCsvFile(null);
    }
}