package com.gmail.programmerfromearth.service;

import com.gmail.programmerfromearth.model.Feedback;

/**
 * Service interface of Student
 *
 */
public interface FeedbackService {

    /**
     * Get feedback via feedback id
     *
     * @param feedbackId a feedback id
     * @return {@link Feedback} corresponding to the current feedback id
     */
    Feedback getFeedbackById(Integer feedbackId);

    /**
     * Add a current feedback to the store
     *
     * @param feedback the added feedback
     * @param idC course id
     * @param idS student id
     * @return an unique identifier of added feedback
     */
    Integer addFeedback(Feedback feedback, Integer idC, Integer idS);

    /**
     * Update a current feedback
     *
     * @param feedback the updated feedback with id which store has
     */
    void updateFeedback(Feedback feedback);
}
