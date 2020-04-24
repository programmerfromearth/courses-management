package com.gmail.programmerfromearth.dao;

import com.gmail.programmerfromearth.model.Student;
import org.springframework.beans.factory.annotation.Value;
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

public class StudentJdbcDaoImpl implements StudentDao {

    @Value("${dao.student.selectAllStudents}")
    private String selectAllStudents;

    @Value("${dao.student.selectStudentById}")
    private String selectById;

    @Value("${dao.student.addStudent}")
    private String addStudent;

    @Value("${dao.student.updateStudent}")
    private String updateStudent;

    @Value("${dao.student.deleteStudent}")
    private String deleteStudent;

    @Value("${dao.student.selectStudentByIdCourse}")
    private String selectStudentByIdCourse;

    private final NamedParameterJdbcTemplate template;

    public StudentJdbcDaoImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Student> getStudents() {
        return template.query(selectAllStudents, new StudentRowMapper());
    }

    @Override
    public Student getStudentById(Integer studentId) {
        MapSqlParameterSource params = new MapSqlParameterSource(ID_S, studentId);
        List<Student> result =  template.query(selectById, params, new StudentRowMapper());
        return DataAccessUtils.uniqueResult(result);
    }

    @Override
    public Integer addStudent(Student student) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String, Object> params = new HashMap<>();
        params.put(NUMBER_S, student.getNumber());
        params.put(NAME_S, student.getName());
        template.update(addStudent, new MapSqlParameterSource(params), keyHolder);
        return (Integer) keyHolder.getKey();
    }

    @Override
    public void updateStudent(Student student) {
        Map<String, Object> params = new HashMap<>();
        params.put(ID_S, student.getId());
        params.put(NUMBER_S, student.getNumber());
        params.put(NAME_S, student.getName());
        template.update(updateStudent, new MapSqlParameterSource(params));
    }

    @Override
    public void deleteStudent(Integer id) {
        MapSqlParameterSource params = new MapSqlParameterSource(ID_S, id);
        template.update(deleteStudent, params);
    }

    @Override
    public List<Student> getStudentByIdOfCourse(Integer courseId) {
        MapSqlParameterSource params = new MapSqlParameterSource(ID_S, courseId);
        return template.query(selectStudentByIdCourse, params, new StudentRowMapper());
    }

    private static class StudentRowMapper implements RowMapper<Student> {
        @Override
        public Student mapRow(ResultSet resultSet, int i) throws SQLException {
            Student student = new Student();
            student.setId(resultSet.getInt(ID_S));
            student.setNumber(resultSet.getString(NUMBER_S));
            student.setName(resultSet.getString(NAME_S));
            return student;
        }
    }
}
