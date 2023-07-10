package concurrentSolution.util;

import concurrentSolution.Regex;
import concurrentSolution.model.Course;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static concurrentSolution.Regex.CSV_SUFFIX;

/**
 * csv file write util
 */
public interface CsvFileWriteUtil {

    /**
     * @param courseDateClicksMap stores course as key and concurrentHashMap as value
     * @param outputFilePath      filePath
     * @throws IOException throw IOException when there is an IO error
     */
    static void writeCSV(
            ConcurrentHashMap<Course, ConcurrentHashMap<Integer, Integer>> courseDateClicksMap,
            String outputFilePath) throws IOException {
        Path path = Path.of(outputFilePath);
        isOutputDirExists(path);
        writeAction(courseDateClicksMap, outputFilePath);
    }

    /**
     * write action to csv file
     *
     * @param courseDateClicksMap stores course as key and concurrentHashMap as value
     * @param outputFilePath      filePath
     */
    static void writeAction(ConcurrentHashMap<Course, ConcurrentHashMap<Integer, Integer>> courseDateClicksMap, String outputFilePath) {
        for (Course course : courseDateClicksMap.keySet()) {
            String filename = course.getCourse() + CSV_SUFFIX.getRegex();
            try {
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFilePath, filename));
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.Builder.create().setHeader("date", "total_clicks").build());
                ConcurrentHashMap<Integer, Integer> dateClicksMap = courseDateClicksMap.get(course);
                List<Integer> dateList = new ArrayList<>(dateClicksMap.keySet());
                Collections.sort(dateList);
                for (Integer date : dateList) {
                    csvPrinter.printRecord(date, dateClicksMap.get(date));
                }
                flushAndClose(csvPrinter, writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * flush and close
     *
     * @param csvPrinter csvPrinter
     * @param bufferedWriter bufferedWriter
     */
    static void flushAndClose(CSVPrinter csvPrinter, BufferedWriter bufferedWriter) {
        try {
            csvPrinter.flush();
            bufferedWriter.close();
            csvPrinter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param courseDateClicksMap stores course as key and concurrentHashMap as value
     * @param outputFilePath      filePath
     * @param threshold           threshold
     * @throws IOException throw IOException when there is an IO error
     */
    static void writeCSVWithThreshold(
            ConcurrentHashMap<Course, ConcurrentHashMap<Integer, Integer>> courseDateClicksMap,
            String outputFilePath, int threshold) throws IOException {
        String filename = Regex.ACTIVITY.getRegex() + Regex.DASH.getRegex() + threshold + CSV_SUFFIX.getRegex();
        BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(outputFilePath + filename));
        CSVPrinter csvPrinter = new CSVPrinter(bufferedWriter, CSVFormat.Builder.create().setHeader("module_presentation", "date", "total_clicks").build());
        for (Course course : courseDateClicksMap.keySet()) {
            ConcurrentHashMap<Integer, Integer> dateClicksMap = courseDateClicksMap.get(course);
            List<Integer> dateList = new ArrayList<>(dateClicksMap.keySet());
            Collections.sort(dateList);
            for (Integer date : dateList) {
                if (dateClicksMap.get(date) > threshold) {
                    csvPrinter.printRecord(course.getCourse(), date, dateClicksMap.get(date));
                }
            }
        }
        flushAndClose(csvPrinter, bufferedWriter);
    }

    /**
     * @param outputDir dir
     * @throws IOException throw IOException when there is an IO error
     */
    static void isOutputDirExists(Path outputDir) throws IOException {
        if (!Files.exists(outputDir)) {
            Files.createDirectory(outputDir);
        }
    }
}
