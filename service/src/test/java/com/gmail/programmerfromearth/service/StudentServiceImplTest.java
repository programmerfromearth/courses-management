package com.gmail.programmerfromearth.service;

import com.gmail.programmerfromearth.model.Student;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-service.xml"})
class StudentServiceImplTest {

    private StudentService studentService;

    @Autowired
    public StudentServiceImplTest(StudentService studentService) {
        this.studentService = studentService;
    }

    @Test
    void getStudents() {
        List<Student> students = studentService.getStudents();

        assertNotNull(students);
        assertTrue(students.size() > 0);
    }

    @Test
    void getStudentById() {
        int id = 2;
        String name = "Bob";
        String number = "TT-2";

        Student student = studentService.getStudentById(id);

        assertNotNull(student);
        assertEquals(id, student.getId());
        assertEquals(name, student.getName());
        assertEquals(number, student.getNumber());

    }

    @Test
    void addStudent() {
        String name = "New_Studetn";
        String number = "New_number";
        Student student = new Student();
        student.setName(name);
        student.setNumber(number);

        int generatedId = studentService.addStudent(student);
        Student studentFromDb = studentService.getStudentById(generatedId);

        assertNotNull(studentFromDb);
        assertEquals(generatedId, studentFromDb.getId());
        assertEquals(name, studentFromDb.getName());
        assertEquals(number, studentFromDb.getNumber());
    }

    @Test
    void updateStudent() {
        int id = 1;
        String name = "Updated_name";
        String number = "Updated_number";
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setNumber(number);

        studentService.updateStudent(student);
        Student updatedStudent = studentService.getStudentById(id);

        assertNotNull(updatedStudent);
        assertEquals(id, updatedStudent.getId());
        assertEquals(name, updatedStudent.getName());
        assertEquals(number, updatedStudent.getNumber());
    }

    @Test
    void deleteStudent() {
        int id = 3; //an id of a deleted student

        List<Student> studentsBeforeDeleting = studentService.getStudents();
        studentService.deleteStudent(id);
        List<Student> studentsAfterDeleting = studentService.getStudents();

        assertEquals(studentsBeforeDeleting.size() - 1, studentsAfterDeleting.size());

    }
}