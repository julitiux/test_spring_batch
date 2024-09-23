package com.test_spring_batch.repository;

import com.test_spring_batch.domain.AfOds;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AfOdsRepository extends MongoRepository<AfOds, String> {
}
