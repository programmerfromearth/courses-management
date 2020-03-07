package com.gmail.programmerfromearth.dao;

import com.gmail.programmerfromearth.model.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SuppressWarnings("unchecked")
public class StudentJdbcDaoImplTest {

    @InjectMocks
    private StudentJdbcDaoImpl studentDao;

    @Mock
    private NamedParameterJdbcTemplate template;

    @Captor
    private ArgumentCaptor<RowMapper<Student>> mapper;

    @Captor
    private ArgumentCaptor<MapSqlParameterSource> param;

    @AfterEach
    void after() {
        verifyNoMoreInteractions(template);
    }

    @Test
    public void getStudents() throws SQLException {
        int id = 1;
        String number = "tt-1";
        String name = "name";
        Student student = new Student();
        ResultSet rs = mock(ResultSet.class);

        when(template.query(anyString(), any(RowMapper.class)))
                .thenReturn(Collections.singletonList(student));
        when(rs.getInt("ID_S")).thenReturn(id);
        when(rs.getString("NUMBER_S")).thenReturn(number);
        when(rs.getString("NAME_S")).thenReturn(name);

        List<Student> students = studentDao.getStudents();
        assertNotNull(students);
        assertEquals(1, students.size());
        Student stud = students.get(0);
        assertNotNull(stud);
        assertSame(stud, student);

        verify(template).query(anyString(), mapper.capture());

        RowMapper<Student> rowMapper = mapper.getValue();
        assertNotNull(rowMapper);
        Student result = rowMapper.mapRow(rs, 0);
        assertNotNull(result);
        assertEquals(id, result.getId().intValue());
        assertEquals(name, result.getName());
    }

    @Test
    public void getStudentById() throws SQLException {
        int id = 1;
        String number = "tt-1";
        String name = "name";
        Student student = new Student();
        ResultSet rs = mock(ResultSet.class);

        when(template.query(anyString(), any(MapSqlParameterSource.class), any(RowMapper.class)))
                .thenReturn(Collections.singletonList(student));
        when(rs.getInt("ID_S")).thenReturn(id);
        when(rs.getString("NUMBER_S")).thenReturn(number);
        when(rs.getString("NAME_S")).thenReturn(name);

        Student returnStudent = studentDao.getStudentById(id);
        assertNotNull(returnStudent);
        assertEquals(student, returnStudent);

        verify(template).query(anyString(), any(MapSqlParameterSource.class), mapper.capture());

        RowMapper<Student> rowMapper = mapper.getValue();
        assertNotNull(rowMapper);
        Student result = rowMapper.mapRow(rs, 0);
        assertNotNull(result);
        assertEquals(id, result.getId().intValue());
        assertEquals(name, result.getName());
    }

    @Test
    public void addStudent() {
        String number = "tt-1";
        String name = "name";
        Student student = new Student();
        student.setNumber(number);
        student.setName(name);

        when(template.update(anyString(), any(MapSqlParameterSource.class), any(KeyHolder.class))).thenReturn(1);

        Integer studentId = studentDao.addStudent(student);

        verify(template, times(1))
                .update(anyString(), any(MapSqlParameterSource.class), any(KeyHolder.class));

        assertNull(studentId);
    }

    @Test
    public void updateStudent() {
        int id = 1;
        String name = "name";
        Student student = new Student();
        student.setId(id);
        student.setName(name);

        when(template.update(anyString(), any(MapSqlParameterSource.class))).thenReturn(1);

        studentDao.updateStudent(student);

        verify(template).update(anyString(), param.capture());

        MapSqlParameterSource map = param.getValue();
        assertNotNull(map);
        assertEquals(id, map.getValue("id"));
        assertEquals(name, map.getValue("name"));
    }

    @Test
    public void deleteStudent() {
        int id = 1;
        String name = "name";
        Student student = new Student();
        student.setId(id);
        student.setName(name);

        when(template.update(anyString(), any(MapSqlParameterSource.class))).thenReturn(1);

        studentDao.deleteStudent(id);

        verify(template).update(anyString(), param.capture());

        MapSqlParameterSource map = param.getValue();
        assertNotNull(map);
        assertEquals(id, map.getValue("id"));
    }
}