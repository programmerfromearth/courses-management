package com.gmail.programmerfromearth.dao;

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
    private StudentJdbcDaoImpl studentJdbcDao;

    @Test
    public void getStudents() throws SQLException {
        List<Student> students = studentJdbcDao. getStudents();
        assertTrue(students.size() > 0);
    }

    @Test
    public void getStudentById() throws SQLException {
        Student student = studentJdbcDao.getStudentById(2);
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

        Integer id = studentJdbcDao.addStudent(student);
        assertEquals(4, id);
    }

    @Test
    public void updateStudent() {
        Student student = new Student();
        student.setId(1);
        student.setNumber("TT-1_updated");
        student.setName("Bob_updated");

        studentJdbcDao.updateStudent(student);

        Student updatedStudent = studentJdbcDao.getStudentById(1);
        assertEquals("TT-1_updated", updatedStudent.getNumber());
        assertEquals("Bob_updated", updatedStudent.getName());

    }

    @Test
    public void getStudentByIdOfCourse() {
        int idC = 1; //id of the course

        List<Student> students = studentJdbcDao.getStudentByIdOfCourse(idC);

        assertTrue(students.size() > 0);
    }

    @Test
    public void deleteStudent() {
        studentJdbcDao.deleteStudent(3);
        List<Student> students = studentJdbcDao.getStudents();
        assertEquals(2, students.size());
    }
}