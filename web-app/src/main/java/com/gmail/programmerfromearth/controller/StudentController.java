package com.gmail.programmerfromearth.controller;

import com.gmail.programmerfromearth.controller.utils.Value;
import com.gmail.programmerfromearth.controller.validators.StudentValidator;
import com.gmail.programmerfromearth.model.Feedback;
import com.gmail.programmerfromearth.model.Student;
import com.gmail.programmerfromearth.service.FeedbackService;
import com.gmail.programmerfromearth.service.StudentCourseFeedbackService;
import com.gmail.programmerfromearth.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class StudentController {
    private final StudentService studentService;
    private final StudentCourseFeedbackService studentCourseFeedbackService;
    private final FeedbackService feedbackService;

    private final StudentValidator studentValidator;

    @Autowired
    public StudentController(StudentService studentService,
                             StudentCourseFeedbackService studentCourseFeedbackService,
                             FeedbackService feedbackService,
                             StudentValidator studentValidator) {
        this.studentService = studentService;
        this.studentCourseFeedbackService = studentCourseFeedbackService;
        this.feedbackService = feedbackService;
        this.studentValidator = studentValidator;
    }

    @GetMapping(value = "/students")
    public String getStudent(Model model) {
        model.addAttribute("students", studentService.getStudents());
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
        studentService.addStudent(student);
        return "redirect:/students";
    }

    @GetMapping(value = "/student/{id}")
    public String goToUpdateStudent(@PathVariable Integer id, Model model) {
        model.addAttribute("isNew", false);
        model.addAttribute("student", studentService.getStudentById(id));
        return "student";
    }

    @PostMapping(value = "/student/{id}")
    public String updateStudent(@Valid Student student, BindingResult result) {
        studentValidator.validate(student, result);
        if (result.hasErrors()) {
            return "student";
        } else {
            studentService.updateStudent(student);
            return "redirect:/students";
        }
    }

    @GetMapping(value = "/students/{id}/delete")
    public String deleteStudents(@PathVariable Integer id, Model model) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }

    @GetMapping(value = "/course/{id}/students")
    public String showStudentsOfCourse(@PathVariable Integer id, Model model) {
        model.addAttribute("students", studentService.getStudentsFormCourse(id));
        model.addAttribute("id", id);
        return "course_student";
    }

    @GetMapping(value = "/course/{idC}/students/{idS}/feedback")
    public String goToAddFeedback(
            @PathVariable Integer idC, @PathVariable Integer idS,
            Model model) {
        model.addAttribute("idC", idC);
        model.addAttribute("idS", idS);
        model.addAttribute("values", Value.values());
        Integer idF = studentCourseFeedbackService.selectIdFByIdCAndIds(idC, idS);
        Feedback feedback;
        if (idF != null) {
            feedback = feedbackService.getFeedbackById(idF);
            model.addAttribute("isNew", false);
        } else {
            feedback = new Feedback();
            model.addAttribute("feedback", feedback);
            model.addAttribute("isNew", true);
        }
        model.addAttribute("feedback", feedback);
        return "feedback";
    }

    @PostMapping(value = "/course/{idC}/students/{idS}/feedback")
    public String addFeedback(@PathVariable Integer idC, @PathVariable Integer idS, Feedback feedback, @ModelAttribute("isNew") boolean isNew) {
        if (isNew) {
            feedbackService.addFeedback(feedback, idC, idS);
        } else {
            feedbackService.updateFeedback(feedback);
        }
        return String.format("redirect:/course/%s/students", idC);
    }
}

