package com.group3.courseenrollment.repository;


import com.group3.courseenrollment.domain.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CredentialRepository extends JpaRepository<Credential,Long> {
    Optional<Credential> findByUsername(String email);
}
