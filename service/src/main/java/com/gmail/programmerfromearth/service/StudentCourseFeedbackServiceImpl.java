package com.gmail.programmerfromearth.service;

import com.gmail.programmerfromearth.dao.studentCourseFeedback.StudentCourseFeedbackDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class StudentCourseFeedbackServiceImpl implements StudentCourseFeedbackService {

    private StudentCourseFeedbackDao studentCourseFeedbackDao;

    public StudentCourseFeedbackServiceImpl(StudentCourseFeedbackDao studentCourseFeedbackDao) {
        this.studentCourseFeedbackDao = studentCourseFeedbackDao;
    }

    @Override
    public void addFeedback(Integer idC, Integer idS, Integer idF) {
        studentCourseFeedbackDao.addFeedback(idC, idS, idF);
    }

    @Override
    public void addCourseStudent(Integer idC, Integer idS) {
        studentCourseFeedbackDao.addCourseStudent(idC, idS);
    }

    @Override
    public void deleteCourseStudent(Integer idC, Integer idS) {
        studentCourseFeedbackDao.deleteCourseStudent(idC, idS);
    }

    @Override
    public Integer selectIdFByIdCAndIds(Integer idC, Integer idS) {
        return studentCourseFeedbackDao.selectIdFByIdCAndIds(idC, idS);
    }
}
