package com.exemplo.iara_apimongo.repository;

import com.exemplo.iara_apimongo.model.database.Shift;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShiftRepository extends MongoRepository<Shift, String> {}
