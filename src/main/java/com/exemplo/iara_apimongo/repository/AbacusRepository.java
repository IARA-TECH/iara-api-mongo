package com.exemplo.iara_apimongo.repository;

import com.exemplo.iara_apimongo.model.database.Abacus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbacusRepository extends MongoRepository<Abacus, String> {
}
