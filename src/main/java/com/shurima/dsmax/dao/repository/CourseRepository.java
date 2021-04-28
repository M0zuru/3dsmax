package com.shurima.dsmax.dao.repository;

import com.shurima.dsmax.dao.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
