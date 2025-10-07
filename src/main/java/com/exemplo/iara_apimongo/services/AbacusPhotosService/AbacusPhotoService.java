package com.exemplo.iara_apimongo.services.AbacusPhotosService;

import com.exemplo.iara_apimongo.dto.abacusPhoto.AbacusPhotoRequestDTO;
import com.exemplo.iara_apimongo.dto.abacusPhoto.AbacusPhotoResponseDTO;

import java.util.List;

public interface AbacusPhotoService {
    AbacusPhotoResponseDTO create(AbacusPhotoRequestDTO dto);
    AbacusPhotoResponseDTO findById(String id);
    List<AbacusPhotoResponseDTO> findAll();
    AbacusPhotoResponseDTO update(String id, AbacusPhotoRequestDTO dto);
    void delete(String id);
}
