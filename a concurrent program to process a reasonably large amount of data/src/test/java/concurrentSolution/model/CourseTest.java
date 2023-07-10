package concurrentSolution.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    Course course;
    String courseModule;
    String codePresentation;
    Integer length;
    Course validateCourse;
    String validateCourseModule;
    String validateCodePresentation;

    @BeforeEach
    void setUp() {
        courseModule = "AAA";
        codePresentation = "BBB";
        length = 10;
        course = new Course(courseModule, codePresentation, length);
        validateCourseModule = "CourseModule";
        validateCodePresentation = "CodePresentation";
        validateCourse = new Course(validateCourseModule, validateCodePresentation);
    }

    @Test
    void getCourseModule() {
        assertEquals(courseModule, course.getCourseModule());
    }

    @Test
    void setCourseModule() {
        course.setCourseModule(validateCourseModule);
        assertEquals(validateCourseModule, course.getCourseModule());
    }

    @Test
    void getCodePresentation() {
        assertEquals(codePresentation, course.getCodePresentation());
    }

    @Test
    void setCodePresentation() {
        course.setCodePresentation(validateCodePresentation);
        assertEquals(validateCodePresentation, course.getCodePresentation());
    }

    @Test
    void getLength() {
        assertEquals(length, course.getLength());
    }

    @Test
    void setLength() {
        course.setLength(20);
        assertEquals(20, course.getLength());
    }

    @Test
    void testToString() {
        assertEquals("Course{courseModule='AAA', codePresentation='BBB', length=10}", course.toString());
    }

    @Test
    void testEquals() {
        assertEquals(course, course);
        assertNotEquals(course, null);
        assertNotEquals(course, new Object());
        assertNotEquals(course, new Course(courseModule, codePresentation));
        assertNotEquals(course, new Course(courseModule, validateCodePresentation));
        assertNotEquals(course, new Course(validateCourseModule, codePresentation));
        assertEquals(course, new Course(courseModule, codePresentation, length));
    }

    @Test
    void testHashCode() {
        assertEquals(course.hashCode(), course.hashCode());
        assertNotEquals(course.hashCode(), validateCourse.hashCode());
        assertNotEquals(course.hashCode(), new Course(null, codePresentation).hashCode());
        assertNotEquals(course.hashCode(), new Course(courseModule, null).hashCode());
    }
}