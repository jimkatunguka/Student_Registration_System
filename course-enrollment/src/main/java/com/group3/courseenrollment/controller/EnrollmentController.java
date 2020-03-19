package com.group3.courseenrollment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.group3.courseenrollment.domain.Enrollment;
import com.group3.courseenrollment.service.EnrollmentService;

import javax.validation.Valid;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {
	@Autowired
	private EnrollmentService enrollmentService;


	@PutMapping("/{enrollmentId}/{studentId}")
	public @ResponseBody Enrollment updateEnrollment(@PathVariable Long enrollmentId,
					 @PathVariable Long studentId,
					 @RequestBody @Valid Enrollment enrollment){

		return enrollmentService.updateEnrollment(enrollmentId,studentId,enrollment);
	}


	 

}
