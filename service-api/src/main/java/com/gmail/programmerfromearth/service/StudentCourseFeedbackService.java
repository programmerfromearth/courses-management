package com.gmail.programmerfromearth.service;

/**
 * StudentCourseFeedback service interface
 *
 */
public interface StudentCourseFeedbackService {

    /**
     * Add id of feedback to STUDENT_COURSE_FEEDBACK table
     *
     * @param idC course id
     * @param idS student id
     * @param idF feedback id
     */
    void addFeedback(Integer idC, Integer idS, Integer idF);

    /**
     * Add id of course and id of student to
     *
     * @param idC course id
     * @param idS student id
     */
    void addCourseStudent(Integer idC, Integer idS);

    /**
     * Add id of course and id of student to
     *
     * @param idC course id
     * @param idS student id
     */
    void deleteCourseStudent(Integer idC, Integer idS);

    /**
     * Get feedback id is corresponded to course id and student id
     *
     * @param idC course id
     * @param idS student id
     * @return feedback id
     */
    Integer selectIdFByIdCAndIds(Integer idC, Integer idS);
}
