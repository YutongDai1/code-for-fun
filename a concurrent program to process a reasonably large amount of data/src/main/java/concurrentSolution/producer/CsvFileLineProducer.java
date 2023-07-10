package concurrentSolution.producer;

import concurrentSolution.Regex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * csv file line producer.
 */
public class CsvFileLineProducer implements Runnable {

    private String filePath;
    private String fileName;
    // memory buffer
    private BlockingQueue<String> bufferQueue;

    /**
     * Constructor for CsvFileLineProducer.
     *
     * @param filePath    the file path
     * @param fileName    the file name
     * @param bufferQueue the buffer queue
     */
    public CsvFileLineProducer(String filePath, String fileName, BlockingQueue<String> bufferQueue) {
        this.filePath = filePath;
        this.fileName = fileName;
        this.bufferQueue = bufferQueue;
    }

    /**
     * put the line from the file reader into the buffer queue(memory buffer)
     *
     * @param singleLine the single line
     */
    public void produce(String singleLine) {
        try {
            // Inserts the singleLine element at the tail of this queue
            bufferQueue.put(singleLine);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        System.out.println("Start to produce the line from the file ..." + fileName);
        String singleLine;
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(filePath + File.separator + fileName));
            bufferedReader.readLine();
            while ((singleLine = bufferedReader.readLine()) != null) {
                produce(singleLine);
            }
            bufferedReader.close();
            produce(Regex.POISON_PILL.getRegex());
            System.out.println("put the poison pill into the bufferQueue: " + fileName);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


    /**
     * Gets buffer queue.
     *
     * @return the buffer queue
     */
    public BlockingQueue<String> getBufferQueue() {
        return bufferQueue;
    }

    /**
     * Sets buffer queue.
     *
     * @param bufferQueue the buffer queue
     */
    public void setBufferQueue(BlockingQueue<String> bufferQueue) {
        this.bufferQueue = bufferQueue;
    }

    /**
     * Gets file path.
     *
     * @return the file path
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Sets file path.
     *
     * @param filePath the file path
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Gets file name.
     *
     * @return the file name
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets file name.
     *
     * @param fileName the file name
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * override the toString method
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "CsvFileLineProducer{" +
                "filePath='" + filePath + '\'' +
                ", fileName='" + fileName + '\'' +
                ", bufferQueue=" + bufferQueue +
                '}';
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
        CsvFileLineProducer that = (CsvFileLineProducer) o;
        return Objects.equals(filePath, that.filePath) &&
                Objects.equals(fileName, that.fileName) &&
                Objects.equals(bufferQueue, that.bufferQueue);
    }

    /**
     * override the hashCode method
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(filePath, fileName, bufferQueue);
    }
}
