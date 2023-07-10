package concurrentSolution.consumer;

import concurrentSolution.Driver;
import concurrentSolution.model.Course;
import concurrentSolution.producer.CsvFileLineProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

import static concurrentSolution.Regex.CSV_FILE_PATH;
import static concurrentSolution.Regex.OUTPUT_FILE_PATH;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class StudentVleConsumerTest {

    StudentVleConsumer studentVleConsumer;
    BlockingQueue<String> queue;
    ConcurrentHashMap<Course, ConcurrentHashMap<Integer, Integer>> courseDateClicksMap;
    Course course;
    CsvFileLineProducer csvFileLineProducer;
    String filePath;
    String fileName;
    String outputFilePath;
    String[] args;
    @BeforeEach
    void setUp() {
        queue = new ArrayBlockingQueue<>(50);
        filePath = CSV_FILE_PATH.getRegex();
        fileName = "concurrentStudentVleForTest.csv";
        outputFilePath = OUTPUT_FILE_PATH.getRegex();
        csvFileLineProducer = new CsvFileLineProducer(filePath, fileName, queue);
        course = new Course("AAA", "BBB", 10);
        courseDateClicksMap = new ConcurrentHashMap<>();
        courseDateClicksMap.put(course, new ConcurrentHashMap<>());
        courseDateClicksMap.put(new Course("CCC", "DDD", 20), new ConcurrentHashMap<>());
        studentVleConsumer = new StudentVleConsumer(queue, courseDateClicksMap);
        args = new String[1];
        args[0] = "11000";
    }
    @Test
    void consume() throws InterruptedException, IOException {
        Driver.execute(args);
    }

    @Test
    void updateDateSumClicks() {
        int date = 1;
        int date2 = 2;
        int clicks = 10;
        int clicks2 = 20;
        studentVleConsumer.updateDateSumClicks(course, date, clicks);
        studentVleConsumer.updateDateSumClicks(course, date2, clicks);
        studentVleConsumer.updateDateSumClicks(course, date2, clicks2);
        assertEquals(clicks, courseDateClicksMap.get(course).get(date));
        assertEquals(clicks2 + clicks, courseDateClicksMap.get(course).get(date2));
    }


    @Test
    void testToString() {
        String expected = "StudentVleConsumer{" +
                "bufferQueue=" + queue +
                ", courseDateClicksMap=" + courseDateClicksMap +
                '}';
        assertEquals(expected, studentVleConsumer.toString());
    }

    @Test
    void testEquals() {
        assertEquals(studentVleConsumer, studentVleConsumer);
        assertNotEquals(studentVleConsumer, null);
        assertNotEquals(studentVleConsumer, new Object());
        StudentVleConsumer studentVleConsumer1 = new StudentVleConsumer(queue, courseDateClicksMap);
        assertEquals(studentVleConsumer, studentVleConsumer1);
    }

    @Test
    void testHashCode() {
        assertNotEquals(0, studentVleConsumer.hashCode());
        assertEquals(studentVleConsumer.hashCode(), studentVleConsumer.hashCode());
        assertNotEquals(studentVleConsumer.hashCode(), new StudentVleConsumer(queue, null).hashCode());
        assertEquals(studentVleConsumer.hashCode(), new StudentVleConsumer(queue, courseDateClicksMap).hashCode());
    }

    @Test
    void getConcurrentClicksMap() {
        assertEquals(courseDateClicksMap, studentVleConsumer.getCourseDateClicksMap());
    }

    @Test
    void setConcurrentClicksMap() {
        ConcurrentHashMap<Course, ConcurrentHashMap<Integer, Integer>> concurrentClicksMap1 = new ConcurrentHashMap<>();
        concurrentClicksMap1.put(new Course("EEE", "FFF", 30), new ConcurrentHashMap<>());
        concurrentClicksMap1.put(new Course("GGG", "HHH", 40), new ConcurrentHashMap<>());
        studentVleConsumer.setCourseDateClicksMap(concurrentClicksMap1);
        assertEquals(concurrentClicksMap1, studentVleConsumer.getCourseDateClicksMap());
    }
}