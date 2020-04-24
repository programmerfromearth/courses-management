package com.gmail.programmerfromearth.dao;

import com.gmail.programmerfromearth.model.Course;
import com.gmail.programmerfromearth.model.Student;

import java.util.List;

/**
 * Student DAO interface
 */
public interface StudentDao extends StudentColumn {

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

    /**
     * get all the students belong to the current course
     *
     * @param courseId a course id
     * @return {@link List} of students belong to the current course
     */
    List<Student> getStudentByIdOfCourse(Integer courseId);

}
