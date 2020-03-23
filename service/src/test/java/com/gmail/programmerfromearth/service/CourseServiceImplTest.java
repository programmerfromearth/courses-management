package com.gmail.programmerfromearth.service;

import com.gmail.programmerfromearth.model.Course;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-service.xml"})
class CourseServiceImplTest {

    private CourseService courseService;

    @Autowired
    public CourseServiceImplTest(CourseService courseService) {
        this.courseService = courseService;
    }

    @Test
    void getCourses() {
        List<Course> courses = courseService.getCourses();

        assertNotNull(courses);
        assertTrue(courses.size() > 0);
    }

    @Test
    void getCourseById() {
        int id = 1;
        String name = "Math";
        String description = "Math-description";
        int teacherId = 1;

        Course course = courseService.getCourseById(id);

        assertNotNull(course);
        assertEquals(id, course.getId());
        assertEquals(name, course.getName());
        assertEquals(description, course.getDescription());
        assertEquals(teacherId, course.getTeacherId());
    }

    @Test
    void addCourse() {
        String name = "Name";
        String description = "Description";
        int teacherId = 4;
        Course course = new Course();
        course.setName(name);
        course.setDescription(description);
        course.setTeacherId(teacherId);

        int generatedId = courseService.addCourse(course);
        Course courseFromDb = courseService.getCourseById(generatedId);

        assertNotNull(courseFromDb);
        assertEquals(generatedId, courseFromDb.getId());
        assertEquals(name, courseFromDb.getName());
        assertEquals(description, courseFromDb.getDescription());
        assertEquals(teacherId, courseFromDb.getTeacherId());
    }

    @Test
    void updateCourse() {
        int id = 1;
        String name = "Updated_Name";
        String description = "Updated_Description";
        int teacherId = 5;
        Course course = new Course();
        course.setId(id);
        course.setName(name);
        course.setDescription(description);
        course.setTeacherId(teacherId);

        courseService.updateCourse(course);
        Course updatedCourse = courseService.getCourseById(id);

        assertNotNull(updatedCourse);
        assertEquals(id, updatedCourse.getId());
        assertEquals(name, updatedCourse.getName());
        assertEquals(description, updatedCourse.getDescription());
        assertEquals(teacherId, updatedCourse.getTeacherId());
    }

    @Test
    void deleteCourse() {
        int id = 3;

        List<Course> courseBeforeDeleting = courseService.getCourses();
        courseService.deleteCourse(id);
        List<Course> courseAfterDeleting = courseService.getCourses();

        assertEquals(courseBeforeDeleting.size() - 1, courseAfterDeleting.size());
    }
}