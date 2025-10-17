package com.exemplo.iara_apimongo.repository;

import com.exemplo.iara_apimongo.model.Sheet;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SheetRepository extends MongoRepository<Sheet, String> {}
