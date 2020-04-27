package com.gmail.programmerfromearth.dao.studentCourseFeedback;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.SqlRowSetResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class StudentCourseFeedbackJdbcDaoImpl implements StudentCourseFeedbackDao {

    @Value("${dao.studentCourseFeedback.addFeedback}")
    private String addFeedback;

    @Value("${dao.studentCourseFeedback.addCourseStudent}")
    private String addCourseStudent;

    @Value("${dao.studentCourseFeedback.deleteCourseStudent}")
    private String deleteCourseStudent;

    @Value("${dao.studentCourseFeedback.selectIdFByIdCAndIds}")
    private String selectIdFByIdCAndIds;

    private final NamedParameterJdbcTemplate template;

    public StudentCourseFeedbackJdbcDaoImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    @Override
    public void addFeedback(Integer idC, Integer idS, Integer idF) {
        MapSqlParameterSource param = new MapSqlParameterSource(ID_C, idC)
                .addValue(ID_S, idS)
                .addValue(ID_F, idF);
        template.update(addFeedback, param);
    }

    @Override
    public void addCourseStudent(Integer idC, Integer idS) {
        MapSqlParameterSource param = new MapSqlParameterSource(ID_C, idC)
                .addValue(ID_S, idS);
        template.update(addCourseStudent, param);
    }

    @Override
    public void deleteCourseStudent(Integer idC, Integer idS) {
        MapSqlParameterSource param = new MapSqlParameterSource(ID_C, idC)
                .addValue(ID_S, idS);
        template.update(deleteCourseStudent, param);
    }

    @Override
    public Integer selectIdFByIdCAndIds(Integer idC, Integer idS) {
        MapSqlParameterSource param = new MapSqlParameterSource(ID_C, idC)
                .addValue(ID_S, idS);
        ResultSetExtractor<SqlRowSet> resultSetExtractor = new SqlRowSetResultSetExtractor();
        SqlRowSet rowSet = template.query(selectIdFByIdCAndIds, param, resultSetExtractor);
        rowSet.next();
        Integer idF =  rowSet.getInt(ID_F);
        if (rowSet.wasNull()) {
            idF = null;
        }
        return idF;
    }
}