package com.gmail.programmerfromearth.controller;

import com.gmail.programmerfromearth.dao.StudentDao;
import com.gmail.programmerfromearth.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {
    private final StudentDao studentDao;

    @Autowired
    public StudentController(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @GetMapping(value = "/students")
    public String getStudent(Model model) {
        model.addAttribute("students", studentDao.getStudents());
        return "students";
    }

    @GetMapping(value = "/student")
    public String goToAddStudent(Model model) {
        model.addAttribute("isNew", true);
        model.addAttribute("student", new Student());
        return "student";
    }

    @PostMapping(value = "/student")
    public String addStudent(Student student) {
        studentDao.addStudent(student);
        return "redirect:/students";
    }

    @GetMapping(value = "/student/{id}")
    public String goToUpdateStudent(@PathVariable Integer id, Model model) {
        model.addAttribute("isNew", false);
        model.addAttribute("student", studentDao.getStudentById(id));
        return "student";
    }

    @PostMapping(value = "/student/{id}")
    public String updateStudent(Student student) {
        studentDao.updateStudent(student);
        return "redirect:/students";
    }

    @GetMapping(value = "/students/{id}/delete")
    public String deleteStudents(@PathVariable Integer id, Model model) {
        studentDao.deleteStudent(id);
        return "redirect:/students";
    }
}
