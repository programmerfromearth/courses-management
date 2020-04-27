package com.gmail.programmerfromearth.dao.feedback;

import com.gmail.programmerfromearth.model.Feedback;

/**
 * Feedback DAO interface
 */
public interface FeedbackDao extends FeedbackColumn {

    /**
     * Get feedback via feedback id
     *
     * @param feedbackId a feedback id
     * @return {@link Feedback} corresponding to the current feedback id
     */
    Feedback getFeedbackById(Integer feedbackId);

    /**
     * Get feddback cia course id and student id
     *
     * @param idC course id
     * @param IdS student id
     * @return {@link Feedback} corresponding to the current course id and student id
     */
    Feedback getFeedbackByIdCAndIdS(Integer idC, Integer IdS);

    /**
     * Add a current feedback to the store
     *
     * @param feedback the added feedback
     * @return an unique identifier of added feedback
     */
    Integer addFeedback(Feedback feedback);

    /**
     * Update a current feedback
     *
     * @param feedback the updated feedback with id which store has
     */
    void updateFeedback(Feedback feedback);

}
