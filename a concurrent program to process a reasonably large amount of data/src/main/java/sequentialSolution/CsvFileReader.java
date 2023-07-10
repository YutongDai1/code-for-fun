package sequentialSolution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * csv file reader reads the csv file and stores the processed information in a HashMap
 */
public class CsvFileReader implements ICsvFileReader {

    private static final Integer COURSE_MODULE_INDEX = 0;
    private static final Integer CODE_PRESENTATION_INDEX = 1;
    private static final Integer DATE_INDEX = 4;
    private static final Integer SUM_CLICKS_INDEX = 5;
    private static final String COMMA_DELIMITER = ",";
    private static final String REGEX_EXP = "(\").*?(\")";
    private static final String REGEX = "\"";
    private static final String EMPTY_STRING = "";
    private HashMap<String, Map<Integer, Integer>> processedDataMap;

    /**
     * constructor for CsvFileReader
     */
    public CsvFileReader() {
        this.processedDataMap = new HashMap<>();
    }

    /**
     * Reads the CSV file and returns the processed information
     *
     * @param csvPath course data path
     * @return the processed information in a HashMap format
     */
    @Override
    public HashMap<String, Map<Integer, Integer>> readCsvFile(Path csvPath) {
        System.out.println("Reading the csv file...");
        try {
            InputStream information = Files.newInputStream(csvPath);
            BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(information));
            String title = reader.readLine();
            // read the first line of the file
            System.out.println("Reading " + csvPath.getFileName() + "...");
            processCsvFile(reader);
        } catch (Exception e) {
            System.err.println("In function readCsvFile: " + e.getMessage());
        }
        return processedDataMap;
    }


    /**
     * Processes the csv file and stores the processed information in a HashMap
     * @param reader the buffered reader
     */
    public void processCsvFile(BufferedReader reader) {
        try {
            Pattern pattern = Pattern.compile(REGEX_EXP);
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                String[] courseList = line.split(COMMA_DELIMITER);
                String courseModule = courseList[COURSE_MODULE_INDEX].replaceAll(REGEX, EMPTY_STRING);
                String codePresentation = courseList[CODE_PRESENTATION_INDEX].replaceAll(REGEX, EMPTY_STRING);
                // System.out.println("Processing " + courseModule + " " + codePresentation + "...");
                Matcher dateMatcher = pattern.matcher(courseList[DATE_INDEX]);
                Matcher sumClicksMatcher = pattern.matcher(courseList[SUM_CLICKS_INDEX]);
                int date;
                int sumClicks;
                if (dateMatcher.find()) {
                    date = Integer.parseInt(dateMatcher.group().replaceAll(REGEX, EMPTY_STRING));
                } else {
                    date = Integer.parseInt(courseList[DATE_INDEX]);
                }
                if (sumClicksMatcher.find()) {
                    sumClicks = Integer.parseInt(sumClicksMatcher.group().replaceAll(REGEX, EMPTY_STRING));
                } else {
                    sumClicks = Integer.parseInt(courseList[SUM_CLICKS_INDEX]);
                }
                String key = courseModule + "_" + codePresentation;
                if (processedDataMap.containsKey(key)) {
                    // if the key is already present in the map, then update the value
                    updateMap(key, date, sumClicks);
                } else {
                    // if the key is not in the map, create a new map and put it in the map
                    createNewMap(key, date, sumClicks);
                }
            }
        } catch (Exception e) {
            System.err.println("In function processCsvFile: " + e.getMessage());
        }
    }

    /**
     * updates the map with the new values
     *
     * @param key        the key
     * @param date       the date
     * @param sumClicks  the sum clicks
     */
    private void updateMap(String key, Integer date, Integer sumClicks) {
        if (processedDataMap.get(key).containsKey(date)) {
            // if the date is already present, add the clicks to the existing value
            processedDataMap.get(key).put(date, processedDataMap.get(key).get(date) + sumClicks);
        } else {
            // if the date is not present, add the date and clicks to the map
            processedDataMap.get(key).put(date, sumClicks);
        }
    }

    /**
     * Creates a new map and put it in the map
     *
     * @param key       the key
     * @param date      the date
     * @param sumClicks the sum clicks
     */
    private void createNewMap(String key, Integer date, Integer sumClicks) {
        Map<Integer, Integer> dateClickMap = new HashMap<>();
        dateClickMap.put(date, sumClicks);
        processedDataMap.put(key, dateClickMap);
    }

    /**
     * Gets the processed data map.
     *
     * @return the processed data map
     */
    public HashMap<String, Map<Integer, Integer>> getProcessedDataMap() {
        return processedDataMap;
    }

    /**
     * Sets the processed data map.
     *
     * @param processedDataMap the new processed data map
     */
    public void setProcessedDataMap(HashMap<String, Map<Integer, Integer>> processedDataMap) {
        this.processedDataMap = processedDataMap;
    }

    /**
     * override the toString method
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "CsvFileReader [processedDataMap=" + processedDataMap + "]";
    }

    /**
     * override the equals method
     *
     * @param obj the object
     * @return the boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CsvFileReader other = (CsvFileReader) obj;
        if (processedDataMap == null) {
            if (other.processedDataMap != null) {
                return false;
            }
        } else if (!processedDataMap.equals(other.processedDataMap)) {
            return false;
        }
        return true;
    }

    /**
     * override the hashCode method
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((processedDataMap == null) ? 0 : processedDataMap.hashCode());
        return result;
    }
}
