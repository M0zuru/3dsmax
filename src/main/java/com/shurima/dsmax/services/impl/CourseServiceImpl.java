package com.shurima.dsmax.services.impl;

import com.shurima.dsmax.dao.entity.Course;
import com.shurima.dsmax.dao.repository.CourseRepository;
import com.shurima.dsmax.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Integer id) {
        return courseRepository.getOne(id);
    }
}
