package com.gmail.programmerfromearth.controller;

import com.gmail.programmerfromearth.controller.utils.Checkbox;
import com.gmail.programmerfromearth.service.StudentCourseFeedbackService;
import com.gmail.programmerfromearth.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

@Controller
public class StudentCourseController {
    private final StudentService studentService;
    private final StudentCourseFeedbackService studentCourseFeedbackService;

    @Autowired
    public StudentCourseController(StudentService studentService,
                                   StudentCourseFeedbackService studentCourseFeedbackService) {
        this.studentService = studentService;
        this.studentCourseFeedbackService = studentCourseFeedbackService;
    }

    @GetMapping("/course/{id}/students/add_to_course")
    public String goToAddStudentToCourse(@PathVariable Integer id, Model model) {
        model.addAttribute("students", studentService.getStudentsNotFormCourse(id));
        model.addAttribute("checkbox", new Checkbox());
        model.addAttribute("modeFlag", true);
        return "add_delete_student_to_course";
    }

    @PostMapping("/course/{id}/students/add_to_course")
    public String addStudentToCourse(@PathVariable Integer id,
                                     @ModelAttribute(value = "checkbox") Checkbox checkbox) {
        if (Objects.nonNull(checkbox.getCheckedItem())) {
            for (Integer item : checkbox.getCheckedItem()) {
                studentCourseFeedbackService.addCourseStudent(id, item);
            }
        }
        return String.format("redirect:/course/%s/students", id);
    }

    @GetMapping("/course/{id}/students/delete_from_course")
    public String goToDeleteStudentToCourse(@PathVariable Integer id, Model model) {
        model.addAttribute("students", studentService.getStudentsFormCourse(id));
        model.addAttribute("checkbox", new Checkbox());
        model.addAttribute("modeFlag", false);
        return "add_delete_student_to_course";
    }

    @PostMapping("/course/{id}/students/delete_from_course")
    public String deleteStudentToCourse(@PathVariable Integer id,
                                        @ModelAttribute(value = "checkbox") Checkbox checkbox) {
        if (Objects.nonNull(checkbox.getCheckedItem())) {
            for (Integer item : checkbox.getCheckedItem()) {
                studentCourseFeedbackService.deleteCourseStudent(id, item);
            }
        }
        return String.format("redirect:/course/%s/students", id);
    }
}
