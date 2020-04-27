package com.gmail.programmerfromearth.dao.feedback;

import com.gmail.programmerfromearth.model.Feedback;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FeedbackJdbcDaoImpl implements FeedbackDao {

    @Value("${dao.feedback.selectById}")
    private String selectById;

    @Value("${dao.feedback.selectByIdCAndIds}")
    private String selectByIdCAndIds;

    @Value("${dao.feedback.add}")
    private String add;

    @Value("${dao.feedback.update}")
    private String update;

    private final NamedParameterJdbcTemplate template;

    public FeedbackJdbcDaoImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    @Override
    public Feedback getFeedbackById(Integer feedbackId) {
        MapSqlParameterSource params = new MapSqlParameterSource(ID_F, feedbackId);
        List<Feedback> result =  template.query(selectById, params, new FeedbackJdbcDaoImpl.FeedbackRowMapper());
        return DataAccessUtils.uniqueResult(result);
    }

    @Override
    public Feedback getFeedbackByIdCAndIdS(Integer idC, Integer IdS) {
        MapSqlParameterSource params = new MapSqlParameterSource(ID_F, idC);
        List<Feedback> result =  template.query(selectById, params, new FeedbackJdbcDaoImpl.FeedbackRowMapper());
        return DataAccessUtils.uniqueResult(result);
    }

    @Override
    public Integer addFeedback(Feedback feedback) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = new MapSqlParameterSource(COMMENT_F, feedback.getFeedback())
                .addValue(VALUE_F, feedback.getValue());
        template.update(add, params, keyHolder);
        return (Integer) keyHolder.getKey();
    }

    @Override
    public void updateFeedback(Feedback feedback) {
        MapSqlParameterSource params = new MapSqlParameterSource(ID_F, feedback.getId())
                .addValue(COMMENT_F, feedback.getFeedback())
                .addValue(VALUE_F, feedback.getValue());
        template.update(update, params);
    }

    private static class FeedbackRowMapper implements RowMapper<Feedback> {
        @Override
        public Feedback mapRow(ResultSet resultSet, int i) throws SQLException {
            Feedback feedback = new Feedback();
            feedback.setId(resultSet.getInt(ID_F));
            feedback.setFeedback(resultSet.getString(COMMENT_F));
            feedback.setValue(resultSet.getInt(VALUE_F));
            return feedback;
        }
    }
}
