package com.exemplo.iara_apimongo.services.AbacusService;

import com.exemplo.iara_apimongo.dto.abacus.AbacusRequestDTO;
import com.exemplo.iara_apimongo.dto.abacus.AbacusResponseDTO;

import java.util.List;

public interface AbacusService {
    AbacusResponseDTO create(AbacusRequestDTO dto);
    AbacusResponseDTO findById(String id);
    List<AbacusResponseDTO> findAll();
    AbacusResponseDTO update(String id, AbacusRequestDTO dto);
    void delete(String id);
}
