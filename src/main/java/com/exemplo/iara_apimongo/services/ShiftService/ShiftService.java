package com.exemplo.iara_apimongo.services.ShiftService;

import com.exemplo.iara_apimongo.dto.shift.ShiftRequestDTO;
import com.exemplo.iara_apimongo.dto.shift.ShiftResponseDTO;

import java.util.List;

public interface ShiftService {
    ShiftResponseDTO create(ShiftRequestDTO dto);
    ShiftResponseDTO findById(String id);
    List<ShiftResponseDTO> findAll();
    ShiftResponseDTO update(String id, ShiftRequestDTO dto);
    void delete(String id);
}
