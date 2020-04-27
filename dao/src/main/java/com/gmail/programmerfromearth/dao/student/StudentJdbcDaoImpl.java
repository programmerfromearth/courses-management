package com.gmail.programmerfromearth.dao.student;

import com.gmail.programmerfromearth.dao.studentCourseFeedback.StudentCourseFeedbackColumn;
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
import java.util.List;

public class StudentJdbcDaoImpl implements StudentDao {

    @Value("${dao.student.selectAll}")
    private String selectAll;

    @Value("${dao.student.selectById}")
    private String selectById;

    @Value("${dao.student.selectAllByCourseId}")
    private String selectAllByCourseId;

    @Value("${dao.student.selectAllNotFromCourseByCourseId}")
    private String selectAllNotFromCourseByCourseId;

    @Value("${dao.student.add}")
    private String add;

    @Value("${dao.student.update}")
    private String update;

    @Value("${dao.student.delete}")
    private String delete;

    @Value("${dao.student.selectByIdCourse}")
    private String selectByIdCourse;

    private final NamedParameterJdbcTemplate template;

    public StudentJdbcDaoImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Student> getStudents() {
        return template.query(selectAll, new StudentRowMapper());
    }

    @Override
    public Student getStudentById(Integer studentId) {
        MapSqlParameterSource params = new MapSqlParameterSource(ID_S, studentId);
        List<Student> result =  template.query(selectById, params, new StudentRowMapper());
        return DataAccessUtils.uniqueResult(result);
    }

    @Override
    public List<Student> getStudentsFormCourse(Integer courseId) {
        MapSqlParameterSource params = new MapSqlParameterSource(StudentCourseFeedbackColumn.ID_C, courseId);
        return template.query(selectAllByCourseId, params, new StudentRowMapper());
    }

    @Override
    public List<Student> getStudentsNotFormCourse(Integer courseId) {
        MapSqlParameterSource params = new MapSqlParameterSource(StudentCourseFeedbackColumn.ID_C, courseId);
        return template.query(selectAllNotFromCourseByCourseId, params, new StudentRowMapper());
    }

    @Override
    public Integer addStudent(Student student) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = new MapSqlParameterSource(NUMBER_S, student.getNumber())
                .addValue(NAME_S, student.getName());
        template.update(add, params, keyHolder);
        return (Integer) keyHolder.getKey();
    }

    @Override
    public void updateStudent(Student student) {
        MapSqlParameterSource params = new MapSqlParameterSource(ID_S, student.getId())
                .addValue(NUMBER_S, student.getNumber())
                .addValue(NAME_S, student.getName());
        template.update(update, params);
    }

    @Override
    public void deleteStudent(Integer id) {
        MapSqlParameterSource params = new MapSqlParameterSource(ID_S, id);
        template.update(delete, params);
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
