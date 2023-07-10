package concurrentSolution.consumer;

import concurrentSolution.model.Course;
import concurrentSolution.util.CsvFilePreprocessUtil;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

import static concurrentSolution.CodeIndex.*;

/**
 * studentVleConsumer represents the consumer of the studentVle file.
 */
public class StudentVleConsumer extends CsvFileLineConsumer {

    // ConcurrentHashMap is thread-safe and can be accessed by multiple threads at the same time without any synchronization issues.
    private ConcurrentHashMap<Course, ConcurrentHashMap<Integer, Integer>> courseDateClicksMap;

    /**
     * Constructor for StudentVleConsumer.
     *
     * @param bufferQueue         the buffer deque
     * @param courseDateClicksMap the concurrent clicks
     */
    public StudentVleConsumer(BlockingQueue<String> bufferQueue, ConcurrentHashMap<Course, ConcurrentHashMap<Integer, Integer>> courseDateClicksMap) {
        super(bufferQueue);
        this.courseDateClicksMap = courseDateClicksMap;
    }

    /**
     * consume a line of the csv file and add it to the concurrent map of clicks and the concurrent map of sum clicks per course
     *
     * @param line the line
     */
    @Override
    void consume(String line) {
        List<String> singleLineList = CsvFilePreprocessUtil.preprocess(line);
        // System.out.println("StudentVleConsumer: " + singleLineList);
        String courseModule = singleLineList.get(COURSE_MODULE_INDEX.getIndex());
        String codePresentation = singleLineList.get(CODE_PRESENTATION_INDEX.getIndex());
        int date = Integer.parseInt(singleLineList.get(DATE_INDEX.getIndex()));
        int sumClicks = Integer.parseInt(singleLineList.get(SUM_CLICKS_INDEX.getIndex()));
        Course course = new Course(courseModule, codePresentation);
        // If course is not already associated with a value (or is mapped to null),
        // attempts to compute its value using ConcurrentHashMap constructor and enters it into this map unless null.
        updateDateSumClicks(course, date, sumClicks);
    }

    /**
     * update the concurrent map of clicks per date per course with the new sum of clicks
     *
     * @param course course
     * @param date date
     * @param sumClicks sumClicks
     */
    public void updateDateSumClicks(Course course, Integer date, Integer sumClicks) {
        // If *course* is not already associated with a value (or is mapped to null),
        // attempts to compute its value and enters it into this map unless null.
        ConcurrentHashMap<Integer, Integer> concurrentDateClicksMap =
                courseDateClicksMap.computeIfAbsent(course, k -> new ConcurrentHashMap<>());

        Integer oldClicks, newClicks;
        // If *date* is not already associated with a value (or is mapped to null),
        // attempts to compute its value and enters it into this map unless null.
        do {
            oldClicks = concurrentDateClicksMap.computeIfAbsent(date, k -> 0);
            newClicks = oldClicks + sumClicks;
        } while (!concurrentDateClicksMap.replace(date, oldClicks, newClicks));
    }

    /**
     * get the concurrent map of clicks per date per course
     *
     * @return ConcurrentMap of clicks per date per course
     */
    public ConcurrentHashMap<Course, ConcurrentHashMap<Integer, Integer>> getCourseDateClicksMap() {
        return courseDateClicksMap;
    }

    /**
     * set the concurrent map of clicks per date per course
     *
     * @param courseDateClicksMap courseDateClicksMap
     */
    public void setCourseDateClicksMap(ConcurrentHashMap<Course, ConcurrentHashMap<Integer, Integer>> courseDateClicksMap) {
        this.courseDateClicksMap = courseDateClicksMap;
    }

    /**
     * override toString method
     *
     * @return String
     */
    @Override
    public String toString() {
        return "StudentVleConsumer{" +
                "bufferQueue=" + bufferQueue +
                ", courseDateClicksMap=" + courseDateClicksMap +
                // ", courseSumClicksMap=" + courseSumClicksMap +
                '}';
    }

    /**
     * override equals method
     *
     * @param o o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentVleConsumer that = (StudentVleConsumer) o;
        return Objects.equals(courseDateClicksMap, that.courseDateClicksMap);
    }

    /**
     * override hashCode method
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(courseDateClicksMap);
    }
}
