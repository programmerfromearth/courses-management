package com.gmail.programmerfromearth.service;

import com.gmail.programmerfromearth.dao.feedback.FeedbackDao;
import com.gmail.programmerfromearth.dao.studentCourseFeedback.StudentCourseFeedbackDao;
import com.gmail.programmerfromearth.model.Feedback;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService {

    private FeedbackDao feedbackDao;
    private StudentCourseFeedbackDao studentCourseFeedbackDao;

    public FeedbackServiceImpl(FeedbackDao feedbackDao, StudentCourseFeedbackDao studentCourseFeedbackDao) {
        this.feedbackDao = feedbackDao;
        this.studentCourseFeedbackDao = studentCourseFeedbackDao;
    }

    @Override
    public Feedback getFeedbackById(Integer feedbackId) {
        return feedbackDao.getFeedbackById(feedbackId);
    }

    @Override
    public Integer addFeedback(Feedback feedback, Integer idC, Integer idS) {
        Integer idF = feedbackDao.addFeedback(feedback);
        studentCourseFeedbackDao.addFeedback(idC, idS, idF);
        return idF;
    }

    @Override
    public void updateFeedback(Feedback feedback) {
        feedbackDao.updateFeedback(feedback);
    }
}
