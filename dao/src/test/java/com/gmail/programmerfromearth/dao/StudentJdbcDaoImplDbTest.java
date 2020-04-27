package com.gmail.programmerfromearth.dao;

import com.gmail.programmerfromearth.dao.student.StudentDao;
import com.gmail.programmerfromearth.model.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml", "classpath*:dao.xml"})
public class StudentJdbcDaoImplDbTest {

    @Autowired
    private StudentDao studentDao;

    @Test
    public void getStudents() throws SQLException {
        List<Student> students = studentDao. getStudents();
        assertTrue(students.size() > 0);
    }

    @Test
    public void getStudentById() throws SQLException {
        Student student = studentDao.getStudentById(2);
        assertNotNull(student);
        assertEquals("TT-2", student.getNumber());
        assertEquals("Bob", student.getName());
    }

    @Test
    public void addStudent() {
        Student student = new Student();
        student.setId(4);
        student.setNumber("TT-4");
        student.setName("Till");

        Integer id = studentDao.addStudent(student);
        assertEquals(4, id);
    }

    @Test
    public void updateStudent() {
        Student student = new Student();
        student.setId(1);
        student.setNumber("TT-1_updated");
        student.setName("Bob_updated");

        studentDao.updateStudent(student);

        Student updatedStudent = studentDao.getStudentById(1);
        assertEquals("TT-1_updated", updatedStudent.getNumber());
        assertEquals("Bob_updated", updatedStudent.getName());
    }

    @Test
    public void deleteStudent() {
        studentDao.deleteStudent(3);
        List<Student> students = studentDao.getStudents();
        assertEquals(2, students.size());
    }
}