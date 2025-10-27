package com.exemplo.iara_apimongo.repository;

import com.exemplo.iara_apimongo.model.Abacus;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AbacusRepository extends MongoRepository<Abacus, String> {}
