package concurrentSolution.consumer;

import concurrentSolution.model.Course;
import java.util.concurrent.ConcurrentHashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CsvFileLineConsumerTest {

    CsvFileLineConsumer csvFileLineConsumer;
    BlockingQueue<String> bufferQueue;
    ConcurrentHashMap<Course, ConcurrentHashMap<Integer, Integer>> courseDateClicksMap;

    @BeforeEach
    void setUp() {
        bufferQueue = new ArrayBlockingQueue<>(50);
        csvFileLineConsumer = new StudentVleConsumer(bufferQueue, courseDateClicksMap);
    }

    @Test
    void consume() {
    }

    @Test
    void run() {
    }

    @Test
    void getBufferQueue() {
        assertEquals(bufferQueue, csvFileLineConsumer.getBufferQueue());
    }
}