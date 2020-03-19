package com.group3.courseenrollment.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.group3.courseenrollment.exception.NoSuchResourceException;

import com.group3.courseenrollment.domain.Course;
import com.group3.courseenrollment.service.CourseService;

import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

/**
 *
 */
@Slf4j
@RestController
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	private CourseService courseService;

	@GetMapping
	public ResponseEntity<List<Course>> getAllCourses() {
		HttpHeaders headers = new HttpHeaders();
		List<Course> courses = courseService.getAllCourses();

		if (courses == null) {
			return new ResponseEntity<List<Course>>(HttpStatus.NOT_FOUND);
		}

		headers.add("Number Of Records Found", String.valueOf(courses.size()));
		return new ResponseEntity<List<Course>>(courses, headers, HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<Course> addCourse(@RequestBody @Valid Course course) {
	
		if (course == null) {
			return new ResponseEntity<Course>(HttpStatus.BAD_REQUEST);
		}
		
		courseService.addCourse(course);
		
		return ResponseEntity.created(URI.create("/courses/" + course.getCode())).build();
	}

	@GetMapping("/{courseId}")
	public ResponseEntity<Course> getCourse(@PathVariable String courseId) {
	    try {
            Course course = courseService.getCourse(courseId);
            return new ResponseEntity<Course>(course, HttpStatus.OK);
        } catch (NoSuchResourceException e){
            return new ResponseEntity<Course>(HttpStatus.NOT_FOUND);
        }

	}

	@PutMapping("/{courseId}")
	public ResponseEntity<Course> update(@PathVariable String courseId, @RequestBody @Valid Course course)   {
		HttpHeaders headers = new HttpHeaders();
		try {
		courseService.updateCourse(courseId, course);
			headers.add("Course Updated  - ", String.valueOf(courseId));

		}catch (NoSuchResourceException e){
			log.info("Course not found");
			return new ResponseEntity<Course>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Course>(course, headers, HttpStatus.OK);
	}


}
