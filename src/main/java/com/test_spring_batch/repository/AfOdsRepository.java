package com.test_spring_batch.repository;

import com.test_spring_batch.domain.AfOds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AfOdsRepository extends JpaRepository<AfOds, UUID> {
}
