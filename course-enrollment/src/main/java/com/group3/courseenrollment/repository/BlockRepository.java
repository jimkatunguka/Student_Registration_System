package com.group3.courseenrollment.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group3.courseenrollment.domain.Block;

@Repository
public interface BlockRepository extends JpaRepository<Block, Long>{
	Optional<Block> findByCode(String code);

}
