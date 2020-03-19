package com.group3.courseenrollment.repository;


import com.group3.courseenrollment.domain.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntryRepository extends JpaRepository<Entry,Long> {
    Optional<Entry> findByStudentListStudentId(Long studentId);
    Optional<Entry> findByStudentListEmail(String email);
}
