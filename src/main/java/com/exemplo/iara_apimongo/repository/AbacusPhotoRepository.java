package com.exemplo.iara_apimongo.repository;

import com.exemplo.iara_apimongo.model.AbacusPhoto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AbacusPhotoRepository extends MongoRepository<AbacusPhoto, String> {}
