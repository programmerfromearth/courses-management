package com.gmail.programmerfromearth.controller;

import com.gmail.programmerfromearth.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentController {
    private final StudentDao studentDao;

    @Autowired
    public StudentController(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @RequestMapping(value = "/students")
    public String getStudent(Model model) {
        model.addAttribute("students", studentDao.getStudents());
        return "students";
    }

    @RequestMapping(value = "/students/{id}/delete")
    public String deleteStudents(@PathVariable Integer id, Model model) {
        studentDao.deleteStudent(id);
        return "redirect:/students";
    }
}
