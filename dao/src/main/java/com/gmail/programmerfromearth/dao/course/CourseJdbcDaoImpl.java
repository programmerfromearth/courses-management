package com.gmail.programmerfromearth.dao.course;

import com.gmail.programmerfromearth.model.Course;
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

public class CourseJdbcDaoImpl implements CourseDao {

    @Value("${dao.course.selectAll}")
    private String selectAll;

    @Value("${dao.course.selectById}")
    private String selectById;

    @Value("${dao.course.add}")
    private String add;

    @Value("${dao.course.update}")
    private String update;

    @Value("${dao.course.delete}")
    private String delete;

    private final NamedParameterJdbcTemplate template;

    public CourseJdbcDaoImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Course> getCourses() {
        return template.query(selectAll, new CourseJdbcDaoImpl.CourseRowMapper());
    }

    @Override
    public Course getCourseById(Integer courseId) {
        MapSqlParameterSource params = new MapSqlParameterSource(ID_C, courseId);
        List<Course> result =  template.query(selectById, params, new CourseJdbcDaoImpl.CourseRowMapper());
        return DataAccessUtils.uniqueResult(result);
    }

    @Override
    public Integer addCourse(Course course) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = new MapSqlParameterSource(NAME_C, course.getName())
                .addValue(DESCRIPTION_C, course.getDescription())
                .addValue(ID_T, course.getTeacherId());
        template.update(add, params, keyHolder);
        return (Integer) keyHolder.getKey();
    }

    @Override
    public void updateCourse(Course course) {
        MapSqlParameterSource params = new MapSqlParameterSource(ID_C, course.getId())
                .addValue(NAME_C, course.getName())
                .addValue(DESCRIPTION_C, course.getDescription())
                .addValue(ID_T, course.getTeacherId());
        template.update(update, params);
    }

    @Override
    public void deleteCourse(Integer courseId) {
        MapSqlParameterSource params = new MapSqlParameterSource(ID_C, courseId);
        template.update(delete, params);
    }

    private static class CourseRowMapper implements RowMapper<Course> {
        @Override
        public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
            Course course = new Course();
            course.setId(rs.getInt(ID_C));
            course.setName(rs.getString(NAME_C));
            course.setDescription(rs.getString(DESCRIPTION_C));
            course.setTeacherId(rs.getInt(ID_T));
            return course;
        }
    }
}
