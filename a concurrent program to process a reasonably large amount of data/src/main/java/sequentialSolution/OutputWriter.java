package sequentialSolution;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * output writer writes the processed information to csv files
 */
public class OutputWriter {

    /**
     *  default constructor
     */
    public OutputWriter() {
    }


    /**
     * Reads data from processedDataMap and Writes them to files
     * @param processedDataMap the processed data map
     * @param outputDir the output directory
     * @throws IOException throw IOException when there is an IO error
     */
    public void writeOutputDirectory(HashMap<String, Map<Integer, Integer>> processedDataMap, Path outputDir) throws IOException {
        // if directory is not exists, create it
        isOutputDirExists(outputDir);
        for (Map.Entry<String, Map<Integer, Integer>> entry : processedDataMap.entrySet()) {
            // get the key
            String courseModule = entry.getKey();
            String lineSeparator = System.getProperty("line.separator");
            write(courseModule, lineSeparator, entry, outputDir);
        }
    }

    /**
     * Writes the processed data to files
     *
     * @param courseModule  the course module
     * @param lineSeparator the line separator
     * @param entry         the entry
     * @param outputDir     the output directory
     */
    public void write(String courseModule, String lineSeparator, Map.Entry<String, Map<Integer, Integer>> entry, Path outputDir) {
        try {
            FileWriter fileWriter = new FileWriter(outputDir + "/" + courseModule + ".csv");
            fileWriter.write("date,total_clicks" + lineSeparator);
            // Get the processed data: [Date]-[Sum of Clicks] key-value pair, and write to file.
            Map<Integer, Integer> dateClicksMap = entry.getValue();
            for (Map.Entry<Integer, Integer> dateClicksEntry : dateClicksMap.entrySet()) {
                fileWriter.write(dateClicksEntry.getKey() + "," + dateClicksEntry.getValue() + lineSeparator);
            }
            fileWriter.close();
        } catch (Exception e) {
            System.err.println("In function write: " + e.getMessage());
        }
    }


    /**
     * Checks if the output directory exists, if not, creates it
     * @param outputDir the output directory
     * @throws IOException throw IOException when there is an IO error
     */
    protected void isOutputDirExists(Path outputDir) throws IOException {
        if (!Files.exists(outputDir)) {
            Files.createDirectory(outputDir);
        }
    }

    /**
     * override the toString method
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "OutputWriter{}";
    }

    /**
     * override the equals method
     *
     * @param o the object
     * @return the boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OutputWriter that = (OutputWriter) o;
        return toString().equals(that.toString());
    }

    /**
     * override the hashCode method
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return toString().hashCode();
    }

}
