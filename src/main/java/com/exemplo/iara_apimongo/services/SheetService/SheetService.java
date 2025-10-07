package com.exemplo.iara_apimongo.services.SheetService;

import com.exemplo.iara_apimongo.dto.sheet.SheetRequestDTO;
import com.exemplo.iara_apimongo.dto.sheet.SheetResponseDTO;

import java.util.List;

public interface SheetService {

    SheetResponseDTO create(SheetRequestDTO dto);
    SheetResponseDTO findById(String id);
    List<SheetResponseDTO> findAll();
    SheetResponseDTO update(String id, SheetRequestDTO dto);
    void delete(String id);
}
