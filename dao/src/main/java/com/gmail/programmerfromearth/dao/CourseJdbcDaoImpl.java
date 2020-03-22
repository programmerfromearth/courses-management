package com.gmail.programmerfromearth.dao;

import com.gmail.programmerfromearth.model.Course;
import com.gmail.programmerfromearth.model.Student;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseJdbcDaoImpl implements CourseDao {

    private String selectCoursesSql =
            "SELECT C.ID_C, C.NAME_C, C.DESCRIPTION_C, C.ID_T FROM COURSE C;";
    private String selectCourseByIdSql =
            "SELECT C.ID_C, C.NAME_C, C.DESCRIPTION_C, C.ID_T FROM COURSE C WHERE C.ID_C = :id;";
    private String addCourseSql =
            "INSERT INTO COURSE(NAME_C, DESCRIPTION_C, ID_T) VALUES (:name, :description, :teacherId);";
    private String updateCourseSql =
            "UPDATE COURSE SET NAME_C = :name, DESCRIPTION_C = :description, ID_T = :teacherId WHERE ID_C = :id;";
    private String deleteCourseSql =
            "DELETE FROM COURSE where ID_C = :id;";


    private final NamedParameterJdbcTemplate template;

    public CourseJdbcDaoImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Course> getCourses() {
        return template.query(selectCoursesSql, new CourseJdbcDaoImpl.CourseRowMapper());
    }

    @Override
    public Course getCourseById(Integer courseId) {
        MapSqlParameterSource params = new MapSqlParameterSource("id", courseId);
        List<Course> result =  template.query(selectCourseByIdSql, params, new CourseJdbcDaoImpl.CourseRowMapper());
        return DataAccessUtils.uniqueResult(result);
    }

    @Override
    public Integer addCourse(Course course) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String, Object> params = new HashMap<>();
        params.put("name", course.getName());
        params.put("description", course.getDescription());
        params.put("teacherId", course.getTeacherId());
        template.update(addCourseSql, new MapSqlParameterSource(params), keyHolder);
        return (Integer) keyHolder.getKey();
    }

    @Override
    public void updateCourse(Course course) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", course.getId());
        params.put("name", course.getName());
        params.put("description", course.getDescription());
        params.put("teacherId", course.getTeacherId());
        template.update(updateCourseSql, new MapSqlParameterSource(params));
    }

    @Override
    public void deleteCourse(Integer courseId) {
        MapSqlParameterSource params = new MapSqlParameterSource("id", courseId);
        template.update(deleteCourseSql, params);
    }

    private static class CourseRowMapper implements RowMapper<Course> {
        @Override
        public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
            Course course = new Course();
            course.setId(rs.getInt("ID_C"));
            course.setName(rs.getString("NAME_C"));
            course.setDescription(rs.getString("DESCRIPTION_C"));
            course.setTeacherId(rs.getInt("ID_T"));
            return course;
        }
    }
}
