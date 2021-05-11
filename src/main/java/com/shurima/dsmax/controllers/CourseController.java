package com.shurima.dsmax.controllers;

import com.shurima.dsmax.dao.entity.Course;
import com.shurima.dsmax.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/course/{id}")
    public String getCourse(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("course", courseService.getCourseById(id));
        return "course";
    }

    @GetMapping("/course/{id}/edit")
    public String getCourseEditForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("course", courseService.getCourseById(id));
        return "createCourse";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/create_course")
    public String getCreateCourse(Model model) {
        model.addAttribute("course", new Course());
        return "createCourse";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/create_course")
    public String saveCourse(Model model, @Valid Course course, Errors errors) {
        if (!errors.hasErrors()) {
            courseService.save(course);
            return "redirect:/course/" + course.getId();
        }
        model.addAttribute("course", course);
        return "createCourse";
    }
}
