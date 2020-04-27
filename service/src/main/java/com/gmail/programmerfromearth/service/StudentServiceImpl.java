package com.gmail.programmerfromearth.service;

import com.gmail.programmerfromearth.dao.studentCourseFeedback.StudentCourseFeedbackDao;
import com.gmail.programmerfromearth.dao.student.StudentDao;
import com.gmail.programmerfromearth.model.Student;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao;
    private StudentCourseFeedbackDao studentCourseFeedbackDao;

    public StudentServiceImpl(StudentDao studentDao, StudentCourseFeedbackDao studentCourseFeedbackDao) {
        this.studentDao = studentDao;
        this.studentCourseFeedbackDao = studentCourseFeedbackDao;
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
    public List<Student> getStudentsFormCourse(Integer courseId) {
        return studentDao.getStudentsFormCourse(courseId);
    }

    @Override
    public List<Student> getStudentsNotFormCourse(Integer courseId) {
        return studentDao.getStudentsNotFormCourse(courseId);
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

    @Override
    public List<Student> getStudentByIdOfCourse(Integer courseId) {
        return studentDao.getStudentByIdOfCourse(courseId);
    }
}
