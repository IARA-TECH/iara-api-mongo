package com.exemplo.iara_apimongo.services;

import com.exemplo.iara_apimongo.dto.shiftDTOs.ShiftRequestDTO;
import com.exemplo.iara_apimongo.dto.shiftDTOs.ShiftResponseDTO;
import com.exemplo.iara_apimongo.model.Shift;
import com.exemplo.iara_apimongo.repository.ShiftRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

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
                .startsAt(dto.getStartsAt().toString())
                .endsAt(dto.getEndsAt().toString())
                .build();
    }

    @Override
    protected ShiftResponseDTO toResponse(Shift entity) {
        LocalTime start = LocalTime.parse(entity.getStartsAt());
        LocalTime end = LocalTime.parse(entity.getEndsAt());

        return ShiftResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .startsAt(start)
                .endsAt(end)
                .createdAt(entity.getCreatedAt())
                .build();
    }

    @Override
    protected void updateEntity(Shift entity, ShiftRequestDTO dto) {
        entity.setName(dto.getName());
        entity.setStartsAt(dto.getStartsAt().toString());
        entity.setEndsAt(dto.getEndsAt().toString());
    }
}
