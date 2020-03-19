package com.group3.courseenrollment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.group3.courseenrollment.domain.Enrollment;
import com.group3.courseenrollment.exception.NoSuchResourceException;
import com.group3.courseenrollment.repository.EnrollmentRepository;
import com.group3.courseenrollment.service.EnrollmentService;


@Service
public class EnrollmentServiceImpl implements EnrollmentService{
	
	@Autowired
    private EnrollmentRepository enrollmentRepository;

    @Transactional(propagation =Propagation.REQUIRES_NEW)
    public List<Enrollment> getAllEnrollments(){
        return enrollmentRepository.findAll();
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Enrollment getEnrollment(long enrollmentId) throws NoSuchResourceException{
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new NoSuchResourceException("Can't find Enrollment", enrollmentId));
        return enrollment;
    }

    @Secured("ROLE_ADMIN")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Enrollment updateEnrollment(long enrollmentId,Long studentId, Enrollment enrollment)
            throws NoSuchResourceException{
        Enrollment enrollment1 = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new NoSuchResourceException("Can't find enrollment", enrollmentId));

        enrollment1.setEnrolStartDate(enrollment.getEnrolStartDate());
        enrollment1.setEnrolEndDate(enrollment.getEnrolEndDate());

        return enrollmentRepository.save(enrollment1);
    }

    @Secured("ROLE_ADMIN")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseEntity<Void> deleteEnrollment(long enrollmentId) throws NoSuchResourceException{
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new NoSuchResourceException("Cant find enrollment", enrollmentId));
        enrollmentRepository.delete(enrollment);
        return  ResponseEntity.noContent().build();
}

}
