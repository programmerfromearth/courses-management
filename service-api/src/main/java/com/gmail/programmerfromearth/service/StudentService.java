package com.gmail.programmerfromearth.service;

import com.gmail.programmerfromearth.model.Course;
import com.gmail.programmerfromearth.model.Student;

import java.util.List;

/**
 * Service interface of Student
 *
 */

public interface StudentService {

    /**
     * Get all the students
     *
     * @return {@link List} of students
     */
    List<Student> getStudents();

    /**
     * Get student via student id
     *
     * @param studentId a student id
     * @return {@link Course} corresponding to the current student id
     */
    Student getStudentById(Integer studentId);

    /**
     * Get all the students from the current course
     *
     * @param courseId course id
     * @return {@link List} of students from the current course
     */
    List<Student> getStudentsFormCourse(Integer courseId);

    /**
     * Get all the students who are not from the current course
     *
     * @param courseId course id
     * @return {@link List} of students who are not from the current course
     */
    List<Student> getStudentsNotFormCourse(Integer courseId);

    /**
     * Add a current student to the store
     *
     * @param student the added student
     * @return an unique identifier of added student
     */
    Integer addStudent(Student student);

    /**
     * Update a current student
     *
     * @param student the updated student with id which store has
     */
    void updateStudent(Student student);

    /**
     * delete student from store
     *
     * @param studentId a student id of the deleted student
     */
    void deleteStudent(Integer studentId);
}
