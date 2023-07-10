package concurrentSolution.model;

import concurrentSolution.Regex;

import java.util.Objects;

/**
 * Course class.
 */
public class Course {

    private String courseModule;
    private String codePresentation;
    private Integer length;

    /**
     * Constructor for Course.
     *
     * @param courseModule the course module
     * @param codePresentation the code presentation
     * @param length the length
     */
    public Course(String courseModule, String codePresentation, Integer length) {
        this.courseModule = courseModule;
        this.codePresentation = codePresentation;
        this.length = length;
    }

    /** Constructor for Course.
     *
     * @param courseModule module
     * @param codePresentation presentation
     */
    public Course(String courseModule, String codePresentation) {
        this.courseModule = courseModule;
        this.codePresentation = codePresentation;
    }

    /**
     * get course
     * @return String
     */
    public String getCourse() {
        return courseModule + Regex.UNDERSCORE.getRegex() + codePresentation;
    }

    /**
     * get the course module
     *
     * @return the course module
     */
    public String getCourseModule() {
        return courseModule;
    }

    /**
     * set the course module
     *
     * @param courseModule the course module
     */
    public void setCourseModule(String courseModule) {
        this.courseModule = courseModule;
    }

    /**
     * get the code presentation
     *
     * @return the code presentation
     */
    public String getCodePresentation() {
        return codePresentation;
    }

    /**
     * set the code presentation
     *
     * @param codePresentation the code presentation
     */
    public void setCodePresentation(String codePresentation) {
        this.codePresentation = codePresentation;
    }

    /**
     * get the length
     *
     * @return the length
     */
    public Integer getLength() {
        return length;
    }

    /**
     * set the length
     *
     * @param length the length
     */
    public void setLength(Integer length) {
        this.length = length;
    }

    /**
     * override the toString method
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "Course{" +
                "courseModule='" + courseModule + '\'' +
                ", codePresentation='" + codePresentation + '\'' +
                ", length=" + length +
                '}';
    }

    /**
     * override the equals method
     *
     * @param o the o
     * @return the boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (!Objects.equals(courseModule, course.courseModule)) return false;
        if (!Objects.equals(codePresentation, course.codePresentation))
            return false;
        return Objects.equals(length, course.length);
    }

    /**
     * override the hashCode method
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        int result = courseModule != null ? courseModule.hashCode() : 0;
        result = 31 * result + (codePresentation != null ? codePresentation.hashCode() : 0);
        result = 31 * result + (length != null ? length.hashCode() : 0);
        return result;
    }
}
