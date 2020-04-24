package com.gmail.programmerfromearth.controller;

import com.gmail.programmerfromearth.model.Course;
import com.gmail.programmerfromearth.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping(value = "/courses")
    public String getCourse(Model model) {
        model.addAttribute("courses", courseService.getCourses());
        return "courses";
    }

    @GetMapping(value = "/course")
    public String goToAddCourse(Model model) {
        model.addAttribute("isNew", true);
        model.addAttribute("course", new Course());
        return "course";
    }

    @PostMapping(value = "/course")
    public String addStudent(Course course) {
        courseService.addCourse(course);
        return "redirect:/courses";
    }

    @GetMapping(value = "/course/{id}")
    public String goToUpdateStudent(@PathVariable Integer id, Model model) {
        model.addAttribute("isNew", false);
        model.addAttribute("course", courseService.getCourseById(id));
        return "course";
    }

    @PostMapping(value = "/course/{id}")
    public String updateStudent(Course course) {
        courseService.updateCourse(course);
        return "redirect:/courses";
    }

    @GetMapping(value = "/courses/{id}/delete")
    public String deleteStudents(@PathVariable Integer id, Model model) {
        courseService.deleteCourse(id);
        return "redirect:/courses";
    }
}
