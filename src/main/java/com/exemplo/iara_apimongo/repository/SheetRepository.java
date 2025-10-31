package com.exemplo.iara_apimongo.repository;

import com.exemplo.iara_apimongo.model.database.Sheet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SheetRepository extends MongoRepository<Sheet, String> {
}
