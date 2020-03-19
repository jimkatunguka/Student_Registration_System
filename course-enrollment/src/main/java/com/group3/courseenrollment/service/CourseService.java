package com.group3.courseenrollment.service;


import org.springframework.http.ResponseEntity;

import com.group3.courseenrollment.domain.Course;
import com.group3.courseenrollment.exception.NoSuchResourceException;

import java.util.List;

public interface CourseService {
	
	public List<Course> getAllCourses();
	
	public Course addCourse(Course course);
	
	public Course getCourse(String courseId) throws NoSuchResourceException;
	
	public Course updateCourse(String courseId, Course new_Course) throws NoSuchResourceException;
	
	public ResponseEntity<Void> deleteCourse(String courseId) throws NoSuchResourceException;
    
}

