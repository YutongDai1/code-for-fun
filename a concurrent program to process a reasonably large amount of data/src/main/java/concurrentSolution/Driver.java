package concurrentSolution;

import concurrentSolution.consumer.CsvFileLineConsumer;
import concurrentSolution.consumer.StudentVleConsumer;
import concurrentSolution.model.Course;
import concurrentSolution.producer.CsvFileLineProducer;
import concurrentSolution.util.CsvFileWriteUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

import static concurrentSolution.Regex.CSV_FILE_PATH;
import static concurrentSolution.Regex.OUTPUT_FILE_PATH;

/**
 * driver class.
 */
public class Driver {
    static int CAPACITY = 20000;
    static String FILENAME = "studentVle.csv";

    /**
     * @param args pass the command line String[]
     * @throws InterruptedException throw InterruptedException when there is an InterruptedException error
     * @throws IOException          throw IOException when there is an IO error
     */
    public static void execute(String[] args) throws InterruptedException, IOException {
        BlockingQueue<String> bufferQueue = new ArrayBlockingQueue<>(CAPACITY);
        ConcurrentHashMap<Course, ConcurrentHashMap<Integer, Integer>> courseDateClicksMap = new ConcurrentHashMap<>();

        CsvFileLineProducer producer = new CsvFileLineProducer(CSV_FILE_PATH.getRegex(), FILENAME, bufferQueue);

        CsvFileLineConsumer consumer = new StudentVleConsumer(bufferQueue, courseDateClicksMap);

        Thread producerThread = new Thread(producer);
        producerThread.start();
        List<Thread> produceThreadList = new ArrayList<>();
        for (int i = 0; i < 5; i++) { // number of threads
            Thread consumerThread = new Thread(consumer);
            produceThreadList.add(consumerThread);
            consumerThread.start();
        }
        producerThread.join();
        for (int i = 0; i < 5; i++) { // number of threads
            produceThreadList.get(i).join();
        }
        CsvFileWriteUtil.writeCSV(courseDateClicksMap, OUTPUT_FILE_PATH.getRegex());
        int threshold;
        if (args.length == 0) {
            threshold = 0;
        } else {
            threshold = Integer.parseInt(args[0]);
        }
        CsvFileWriteUtil.writeCSVWithThreshold(courseDateClicksMap, OUTPUT_FILE_PATH.getRegex(), threshold);
    }
}
