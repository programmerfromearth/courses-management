package com.gmail.programmerfromearth.dao;

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

public class StudentJdbcDaoImpl implements StudentDao {
    private String selectSql =
            "SELECT S.ID_S, S.NUMBER_S, S.NAME_S FROM STUDENT S;";
    private String selectByIdSql =
            "SELECT S.ID_S, S.NUMBER_S, S.NAME_S FROM STUDENT S WHERE S.ID_S = :id;";
    private String addStudentSql =
            "INSERT INTO STUDENT(NUMBER_S, NAME_S) VALUE(:number, :name);";
    private String updateStudentSql =
            "UPDATE STUDENT s SET NUMBER_S = :studentNumber, NAME_S = :name WHERE ID_S = :id;";
    private String deleteStudentSql =
            "DELETE FROM STUDENT where ID_S = :id;";


    private final NamedParameterJdbcTemplate template;

    public StudentJdbcDaoImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Student> getStudents() {
        return template.query(selectSql, new StudentRowMapper());
    }

    @Override
    public Student getStudentById(Integer studentId) {
        MapSqlParameterSource params = new MapSqlParameterSource("id", studentId);
        List<Student> result =  template.query(selectByIdSql, params, new StudentRowMapper());
        return DataAccessUtils.uniqueResult(result);
    }

    @Override
    public Integer addStudent(Student student) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String, Object> params = new HashMap<>();
        params.put("number", student.getNumber());
        params.put("name", student.getName());
        template.update(addStudentSql, new MapSqlParameterSource(params), keyHolder);
        return (Integer) keyHolder.getKey();
    }

    @Override
    public void updateStudent(Student student) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", student.getId());
        params.put("name", student.getName());
        template.update(updateStudentSql, new MapSqlParameterSource(params));
    }

    @Override
    public void deleteStudent(Integer id) {
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        template.update(deleteStudentSql, params);
    }

    private static class StudentRowMapper implements RowMapper<Student> {
        @Override
        public Student mapRow(ResultSet resultSet, int i) throws SQLException {
            Student student = new Student();
            student.setId(resultSet.getInt("id"));
            student.setName(resultSet.getString("number"));
            student.setName(resultSet.getString("name"));
            return student;
        }
    }
}
