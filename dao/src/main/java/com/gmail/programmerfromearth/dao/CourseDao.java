package com.gmail.programmerfromearth.dao;

import com.gmail.programmerfromearth.model.Course;

import java.util.List;

public interface CourseDao {
    List<Course> getCourses();
    Course getCourseById(Integer courseId);
    Integer addCourse(Course course);
    void updateCourse(Course course);
    void deleteCourse(Integer courseId);
}
