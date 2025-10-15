package com.exemplo.iara_apimongo.services;

import com.exemplo.iara_apimongo.dto.shiftDTOs.ShiftRequestDTO;
import com.exemplo.iara_apimongo.dto.shiftDTOs.ShiftResponseDTO;
import com.exemplo.iara_apimongo.model.Shift;
import com.exemplo.iara_apimongo.repository.ShiftRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ShiftService extends BaseService<Shift, String, ShiftRequestDTO, ShiftResponseDTO> {

    public ShiftService(ShiftRepository repository) {
        super(repository, "Shift");
    }

    @Override
    protected Shift toEntity(ShiftRequestDTO dto) {
        return Shift.builder()
                .name(dto.getName())
                .startsAt(dto.getStartsAt())
                .endsAt(dto.getEndsAt())
                .build();
    }

    @Override
    protected ShiftResponseDTO toResponse(Shift entity) {
        return ShiftResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .startsAt(entity.getStartsAt())
                .endsAt(entity.getEndsAt())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    @Override
    protected void updateEntity(Shift entity, ShiftRequestDTO dto) {
        entity.setName(dto.getName());
        entity.setStartsAt(dto.getStartsAt());
        entity.setEndsAt(dto.getEndsAt());
    }
}
