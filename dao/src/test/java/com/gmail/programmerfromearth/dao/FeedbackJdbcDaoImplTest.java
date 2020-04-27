package com.gmail.programmerfromearth.dao;

import com.gmail.programmerfromearth.dao.feedback.FeedbackDao;
import com.gmail.programmerfromearth.model.Feedback;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml", "classpath*:dao.xml"})
class FeedbackJdbcDaoImplTest {

    @Autowired
    private FeedbackDao feedbackDao;

    @Test
    void getFeedbackById() {
        Feedback feedback = feedbackDao.getFeedbackById(1);

        assertNotNull(feedback);
        assertEquals("a good student", feedback.getFeedback());
        assertEquals(5, feedback.getValue());
    }

    @Test
    void addFeedback() {
        Feedback feedback = new Feedback();
        feedback.setFeedback("a new comment");
        feedback.setValue(5);

        Integer id = feedbackDao.addFeedback(feedback);

        assertEquals(3, id);
    }

    @Test
    void updateFeedback() {
        Feedback feedback = new Feedback();
        feedback.setId(1);
        feedback.setFeedback("an updated comment");
        feedback.setValue(5);

        feedbackDao.updateFeedback(feedback);

        Feedback updatedFeedback = feedbackDao.getFeedbackById(1);
        assertEquals("an updated comment", updatedFeedback.getFeedback());
        assertEquals(5, updatedFeedback.getValue());
    }
}