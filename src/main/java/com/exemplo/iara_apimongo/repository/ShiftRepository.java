package com.exemplo.iara_apimongo.repository;

import com.exemplo.iara_apimongo.model.database.Shift;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShiftRepository extends MongoRepository<Shift, String> {
    Optional<Shift> findByNameContainsIgnoreCase(String name);
}
