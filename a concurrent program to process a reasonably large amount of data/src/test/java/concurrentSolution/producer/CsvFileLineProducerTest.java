package concurrentSolution.producer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class CsvFileLineProducerTest {

    BlockingQueue<String> bufferQueue;
    CsvFileLineProducer csvFileLineProducer;
    String filePath;
    String fileName;

    @BeforeEach
    void setUp() {
        bufferQueue = new ArrayBlockingQueue<>(50);
        filePath = "resource";
        fileName = "studentVleForTest.csv";
        csvFileLineProducer = new CsvFileLineProducer(filePath, fileName, bufferQueue);
        // csvFileLineProducer.run();
    }

    @Test
    void produce() {
    }

    @Test
    void run() {
    }

    @Test
    void getBufferQueue() {
        assertEquals(bufferQueue, csvFileLineProducer.getBufferQueue());
    }

    @Test
    void setBufferQueue() {
        BlockingQueue<String> newBufferQueue = new ArrayBlockingQueue<>(50);
        csvFileLineProducer.setBufferQueue(newBufferQueue);
        assertEquals(newBufferQueue, csvFileLineProducer.getBufferQueue());
    }

    @Test
    void getFilePath() {
        assertEquals(filePath, csvFileLineProducer.getFilePath());
    }

    @Test
    void setFilePath() {
        String newFilePath = "newPath";
        csvFileLineProducer.setFilePath(newFilePath);
        assertEquals(newFilePath, csvFileLineProducer.getFilePath());
    }

    @Test
    void getFileName() {
        assertEquals(fileName, csvFileLineProducer.getFileName());
    }

    @Test
    void setFileName() {
        String newFileName = "newFileName";
        csvFileLineProducer.setFileName(newFileName);
        assertEquals(newFileName, csvFileLineProducer.getFileName());
    }

    @Test
    void testToString() {
        String expected = "CsvFileLineProducer{" +
                "filePath='" + filePath + '\'' +
                ", fileName='" + fileName + '\'' +
                ", bufferQueue=" + bufferQueue +
                '}';
        assertEquals(expected, csvFileLineProducer.toString());
    }

    @Test
    void testEquals() {
        CsvFileLineProducer csvFileLineProducer1 = new CsvFileLineProducer(filePath, fileName, bufferQueue);
        assertEquals(csvFileLineProducer, csvFileLineProducer1);
        assertEquals(csvFileLineProducer, csvFileLineProducer);
        assertNotEquals(csvFileLineProducer, null);
        assertNotEquals(csvFileLineProducer, new Object());
        assertNotEquals(csvFileLineProducer, new CsvFileLineProducer("newPath", fileName, bufferQueue));
        assertNotEquals(csvFileLineProducer, new CsvFileLineProducer(filePath, "newFileName", bufferQueue));
        assertNotEquals(csvFileLineProducer, new CsvFileLineProducer(filePath, fileName, new ArrayBlockingQueue<>(5)));
    }

    @Test
    void testHashCode() {
        CsvFileLineProducer csvFileLineProducer1 = new CsvFileLineProducer(filePath, fileName, bufferQueue);
        assertEquals(csvFileLineProducer.hashCode(), csvFileLineProducer1.hashCode());
    }
}