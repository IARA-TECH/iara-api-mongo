package com.exemplo.iara_apimongo.services;

import com.exemplo.iara_apimongo.exception.ResourceNotFoundException;
import com.exemplo.iara_apimongo.model.database.Shift;
import com.exemplo.iara_apimongo.model.dto.request.ShiftRequest;
import com.exemplo.iara_apimongo.model.dto.response.ShiftResponse;
import com.exemplo.iara_apimongo.repository.ShiftRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Slf4j
@Service
public class ShiftService extends BaseService<Shift, String, ShiftRequest, ShiftResponse> {

    private final ShiftRepository shiftRepository;

    public ShiftService(ShiftRepository shiftRepository) {
        super(shiftRepository, "Shift");
        this.shiftRepository = shiftRepository;
    }

    @Override
    protected Shift toEntity(ShiftRequest request) {
        return Shift.builder()
                .name(request.getName())
                .startsAt(request.getStartsAt())
                .endsAt(request.getEndsAt())
                .createdAt(Instant.now())
                .build();
    }

    @Override
    protected ShiftResponse toResponse(Shift entity) {
        return ShiftResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .startsAt(entity.getStartsAt())
                .endsAt(entity.getEndsAt())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    @Override
    protected void updateEntity(Shift entity, ShiftRequest dto) {
        entity.setName(dto.getName());
        entity.setStartsAt(dto.getStartsAt());
        entity.setEndsAt(dto.getEndsAt());
        log.info("Shift {} updated successfully", entity.getId());
    }

    public ShiftResponse findByName(String name){
        log.info("Finding shift by name {}", name);
        return shiftRepository.findByNameContainsIgnoreCase(name)
                .map(this::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Shift not found with name: " + name));
    }
}
