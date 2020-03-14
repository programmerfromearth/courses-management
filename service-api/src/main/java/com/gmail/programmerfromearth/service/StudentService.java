package com.gmail.programmerfromearth.service;

import com.gmail.programmerfromearth.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> getStudents();
    Student getStudentById(Integer studentId);
    Integer addStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(Integer studentId);
}
