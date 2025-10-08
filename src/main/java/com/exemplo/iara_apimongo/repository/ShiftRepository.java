package com.exemplo.iara_apimongo.repository;

import com.exemplo.iara_apimongo.model.Shift;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShiftRepository extends MongoRepository<Shift, String> {
}
