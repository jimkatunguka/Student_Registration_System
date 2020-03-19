package com.group3.courseenrollment.controller;


import com.group3.courseenrollment.domain.Enrollment;
import com.group3.courseenrollment.exception.EnrollmentLimitExceededException;
import com.group3.courseenrollment.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/{studentId}/enrollment")
    public ResponseEntity<?> addEnrollment(@PathVariable Long studentId,
                                           @RequestBody List<Enrollment> enrollments) {
        studentService.addEnrollment(studentId,enrollments);

        return ResponseEntity.created(URI.create("/enrollments/" + studentId)).build();
    }


    @GetMapping("/{studentId}/enrollment")
    public ResponseEntity<List<Enrollment>> getStudentEnrollment(@PathVariable Long studentId) {
        List<Enrollment> enrollments = studentService.loadEnrollmentByStudent(studentId);
        if(enrollments.size() == 0) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(enrollments);
    }

    @GetMapping("/{studentId}/enrollment/{enrollmentId}")
    public ResponseEntity<Enrollment> getStudentEnrollmentById(@PathVariable Long studentId,
                                                                     @PathVariable Long enrollmentId) {
        try {
            Optional<Enrollment> enrollment =
                    studentService.loadStudentEnrollmentByEnrollmentId(enrollmentId, studentId);
            return ResponseEntity.ok(enrollment.get());
        }catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }


    }

}
