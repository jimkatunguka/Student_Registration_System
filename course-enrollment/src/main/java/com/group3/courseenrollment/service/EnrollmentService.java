package com.group3.courseenrollment.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.group3.courseenrollment.domain.Enrollment;
import com.group3.courseenrollment.exception.NoSuchResourceException;


public interface EnrollmentService {

	 public Enrollment updateEnrollment(long enrollmentId,Long studentId, Enrollment enrollment)
			 throws NoSuchResourceException;

}
