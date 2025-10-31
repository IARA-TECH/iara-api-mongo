package com.exemplo.iara_apimongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SheetRepository extends MongoRepository<Sheet, String> {
    List<Sheet> findByFactoryId(Integer factoryId);

}
