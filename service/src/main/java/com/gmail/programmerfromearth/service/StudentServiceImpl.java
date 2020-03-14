package com.gmail.programmerfromearth.service;

import com.gmail.programmerfromearth.dao.StudentDao;
import com.gmail.programmerfromearth.model.Student;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao;

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> getStudents() {
        return studentDao.getStudents();
    }

    @Override
    public Student getStudentById(Integer studentId) {
        return studentDao.getStudentById(studentId);
    }

    @Override
    public Integer addStudent(Student student) {
        return studentDao.addStudent(student);
    }

    @Override
    public void updateStudent(Student student) {
        studentDao.updateStudent(student);
    }

    @Override
    public void deleteStudent(Integer studentId) {
        studentDao.deleteStudent(studentId);
    }
}
