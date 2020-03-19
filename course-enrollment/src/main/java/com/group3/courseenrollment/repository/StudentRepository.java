package com.group3.courseenrollment.repository;

import com.group3.courseenrollment.domain.Enrollment;
import com.group3.courseenrollment.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    Optional<Student> findByStudentId(Long studentId);
    Optional<Student> findByEnrollmentListIdAndStudentId(Long enrollmentId,Long studentId);
    Optional<Student> findByEmail(String email);

}
