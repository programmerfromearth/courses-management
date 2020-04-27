package com.gmail.programmerfromearth.service;

import com.gmail.programmerfromearth.model.Feedback;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-service.xml", "classpath*:dao.xml"})
class FeedbackServiceImplTest {

    private FeedbackService feedbackService;

    @Autowired
    public FeedbackServiceImplTest(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @Test
    void getFeedbackById() {
        Feedback feedback = feedbackService.getFeedbackById(1);

        assertNotNull(feedback);
        assertEquals("a good student", feedback.getFeedback());
        assertEquals(5, feedback.getValue());
    }

    @Test
    void addFeedback() {
        Feedback feedback = new Feedback();
        feedback.setFeedback("a new comment");
        feedback.setValue(5);

        Integer id = feedbackService.addFeedback(feedback, 1, 3);

        assertEquals(3, id);
    }

    @Test
    void updateFeedback() {
        Feedback feedback = new Feedback();
        feedback.setId(1);
        feedback.setFeedback("an updated comment");
        feedback.setValue(5);

        feedbackService.updateFeedback(feedback);

        Feedback updatedFeedback = feedbackService.getFeedbackById(1);
        assertEquals("an updated comment", updatedFeedback.getFeedback());
        assertEquals(5, updatedFeedback.getValue());
    }
}