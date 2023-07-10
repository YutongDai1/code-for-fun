package concurrentSolution.consumer;

import concurrentSolution.Regex;

import java.util.concurrent.BlockingQueue;

/**
 * consumer for the csv file line
 */
public abstract class CsvFileLineConsumer implements Runnable {

    /**
     *  This BlockingQueue stores String
     */
    protected final BlockingQueue<String> bufferQueue;

    /**
     *
     * @param bufferQueue constructor
     */
    protected CsvFileLineConsumer(BlockingQueue<String> bufferQueue) {
        this.bufferQueue = bufferQueue;
    }

    /**
     * consume the csv line
     *
     * @param line the line
     */
    abstract void consume(String line);

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
        String csvLine;
        while (true) {
            try {
                // Retrieves and removes the head of bufferQueue
                // waiting if necessary until an element becomes available.
                csvLine = bufferQueue.take();
                if (csvLine.equals(Regex.POISON_PILL.getRegex())) {
                    // if the csvLine is equal to the poison pill, then break the loop
                    bufferQueue.put(csvLine);
                    break;
                }
                consume(csvLine);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Gets buffer deque.
     *
     * @return the buffer deque
     */
    public BlockingQueue<String> getBufferQueue() {
        return bufferQueue;
    }
}
