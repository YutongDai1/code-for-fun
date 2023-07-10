package sequentialSolution;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public interface ICsvFileReader {

    /**
     * Reads the CSV file and returns the processed information
     *
     * @param csvPath course data path
     * @return the processed information in a HashMap format
     */
    HashMap<String, Map<Integer, Integer>> readCsvFile(Path csvPath);
}
