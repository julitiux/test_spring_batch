package com.test_spring_batch.repository;

import com.test_spring_batch.domain.AfOdsMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AfOdsMongoRepository extends MongoRepository<AfOdsMongo, String> {
}
