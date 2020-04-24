package com.gmail.programmerfromearth.dao;

import com.gmail.programmerfromearth.model.Course;

import java.util.List;

/**
 * Course DAO interface
 */
public interface CourseDao extends CourseColumn {

    /**
     * Get all the courses
     *
     * @return {@link List} of courses
     */
    List<Course> getCourses();

    /**
     * Get courses via course id
     *
     * @param courseId a course id
     * @return {@link Course} corresponding to the current course id
     */
    Course getCourseById(Integer courseId);

    /**
     * Add a current course to the store
     *
     * @param course the added course
     * @return an unique identifier of added course
     */
    Integer addCourse(Course course);

    /**
     * Update a current course
     *
     * @param course the updated course with id which store has
     */
    void updateCourse(Course course);

    /**
     * delete course from store
     *
     * @param courseId a course id of the deleted course
     */
    void deleteCourse(Integer courseId);
}
