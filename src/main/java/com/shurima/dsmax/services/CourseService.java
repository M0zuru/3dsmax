package com.shurima.dsmax.services;

import com.shurima.dsmax.dao.entity.Course;

import java.util.List;

public interface CourseService {
    List<Course> getCourses();
    Course getCourseById(Integer id);
}
