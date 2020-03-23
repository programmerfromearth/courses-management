package com.gmail.programmerfromearth.service;

import com.gmail.programmerfromearth.model.Course;

import java.util.List;

public interface CourseService {
    List<Course> getCourses();
    Course getCourseById(Integer courseId);
    Integer addCourse(Course course);
    void updateCourse(Course course);
    void deleteCourse(Integer courseId);
}
