package concurrentSolution.util;

import org.junit.jupiter.api.Test;

import static concurrentSolution.util.CsvFilePreprocessUtil.preprocess;

class CsvFilePreprocessUtilTest {

    @Test
    void testPreprocess() {
        String line = "\"AAA\",\"2013J\",\"28400\",\"546652\",\"-10\",\"4\"\n" +
                "\"AAA\",\"2013J\",\"28400\",\"546652\",\"-10\",\"1\"\n" +
                "\"AAA\",\"2013J\",\"28400\",\"546652\",\"-10\",\"1\"\n" +
                "\"AAA\",\"2013J\",\"28400\",\"546614\",\"-10\",\"11\"\n" +
                "\"AAA\",\"2013J\",\"28400\",\"546714\",\"-10\",\"1\"\n" +
                "\"AAA\",\"2013J\",\"28400\",\"546652\",\"-10\",\"8\"\n" +
                "\"AAA\",\"2013J\",\"28400\",\"546876\",\"-10\",\"2\"\n" +
                "\"AAA\",\"2013J\",\"28400\",\"546688\",\"-10\",\"15\"\n" +
                "\"AAA\",\"2013J\",\"28400\",\"546662\",\"-10\",\"17\"\n" +
                "\"AAA\",\"2013J\",\"28400\",\"546890\",\"-10\",\"1\"\n";
        preprocess(line);
    }
}