package com.gmail.programmerfromearth.controller.validators;

import com.gmail.programmerfromearth.model.Student;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class StudentValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Student.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "number", "student.number.empty");
        ValidationUtils.rejectIfEmpty(errors, "name", "student.name.empty");
    }
}
