package com.test_spring_batch.repository;

import com.test_spring_batch.domain.AfOds;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AfOdsRepository extends JpaRepository<AfOds, UUID> {
}
