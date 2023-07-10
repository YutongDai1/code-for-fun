package sequentialSolution;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Run class is the main class of the program
 */
public class Run {

    /**
     * The entry point of application.
     * @param args the command line arguments
     * @throws IOException  throw IOException when there is an IO error
     */
    public static void main(String[] args) throws IOException {
        long startTime=System.currentTimeMillis();
        Path courseData = Paths.get("src" + File.separator + "main" + File.separator + "resources" + File.separator + "csv" + File.separator + "studentVleForTest.csv");
        Path outputDir = Paths.get("src" + File.separator + "main" + File.separator + "resources" + File.separator + "output");

        CsvFileReader csvFileReader = new CsvFileReader();
        HashMap<String, Map<Integer, Integer>> processedDataMap = csvFileReader.readCsvFile(courseData);

        OutputWriter outputWriter = new OutputWriter();
        outputWriter.writeOutputDirectory(processedDataMap, outputDir);
        long endTime=System.currentTimeMillis();
        System.out.println("total running time: "+(endTime-startTime)+"ms");
    }
}
