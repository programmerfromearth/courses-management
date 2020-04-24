package com.gmail.programmerfromearth.dao;

import com.gmail.programmerfromearth.model.Course;
import com.gmail.programmerfromearth.model.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml", "classpath*:dao.xml"})
class CourseJdbcDaoImplDbTest {

    @Autowired
    private CourseJdbcDaoImpl courseJdbcDao;

    @Test
    void getCourses() {
        List<Course> courses = courseJdbcDao.getCourses();

        assertTrue(courses.size() > 0);
    }

    @Test
    void getCourseById() {
        Course course = courseJdbcDao.getCourseById(1);

        assertNotNull(course);
        assertEquals(1, course.getId());
        assertEquals("Math", course.getName());
        assertEquals("Math-description", course.getDescription());
        assertEquals(1, course.getTeacherId());
    }

    @Test
    void addCourse() {
        Course course = new Course();
        course.setId(1);
        course.setName("Geography");
        course.setDescription("Geography-description");
        course.setTeacherId(4);

        Integer id = courseJdbcDao.addCourse(course);

        assertEquals(4, id);
    }

    @Test
    void updateCourse() {
        Course course = new Course();
        course.setId(1);
        course.setName("Math_updated");
        course.setDescription("Math-description_updated");
        course.setTeacherId(5);

        courseJdbcDao.updateCourse(course);
        Course updatedCourse = courseJdbcDao.getCourseById(1);

        assertEquals("Math_updated", updatedCourse.getName());
        assertEquals("Math-description_updated", updatedCourse.getDescription());
        assertEquals(5, updatedCourse.getTeacherId());
    }

    @Test
    void deleteCourse() {
        courseJdbcDao.deleteCourse(3);
        List<Course> courses = courseJdbcDao.getCourses();

        assertEquals(2, courses.size());
    }
}