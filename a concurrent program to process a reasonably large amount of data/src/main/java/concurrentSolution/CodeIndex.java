package concurrentSolution;

/**
 *  CodeIndex
 */
public enum CodeIndex {
    /**
     *
     */
    COURSE_MODULE_INDEX(0),
    /**
     *
     */
    CODE_PRESENTATION_INDEX(1),
    /**
     *
     */
    ID_STUDENT(2),
    /**
     *
     */
    ASSESSMENT_TYPE_INDEX(3),
    /**
     *
     */
    DATE_INDEX(4),
    /**
     *
     */
    SUM_CLICKS_INDEX(5),
    /**
     *
     */
    MAX_BUFFER_SIZE(100);

    private int index;

    /**
     *
     * @param index Constructor
     */
    CodeIndex(int index) {
        this.index = index;
    }

    /**
     *
     * @return getIndex
     */
    public int getIndex() {
        return index;
    }

}
