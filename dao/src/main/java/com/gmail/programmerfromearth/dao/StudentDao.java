package com.gmail.programmerfromearth.dao;

import com.gmail.programmerfromearth.model.Student;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;

public interface StudentDao {
    List<Student> getStudents();
    Student getStudentById(Integer studentId);
    Integer addStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(Integer studentId);
}
