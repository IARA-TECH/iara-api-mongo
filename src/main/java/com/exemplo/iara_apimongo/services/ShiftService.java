package com.exemplo.iara_apimongo.services;

import com.exemplo.iara_apimongo.model.dto.request.ShiftRequest;
import com.exemplo.iara_apimongo.model.dto.response.ShiftResponse;
import com.exemplo.iara_apimongo.model.database.Shift;
import com.exemplo.iara_apimongo.repository.ShiftRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ShiftService extends BaseService<Shift, String, ShiftRequest, ShiftResponse> {

    public ShiftService(ShiftRepository repository) {
        super(repository, "Shift");
    }

    @Override
    protected Shift toEntity(ShiftRequest dto) {
        return Shift.builder()
                .name(dto.getName())
                .startsAt(dto.getStartsAt())
                .endsAt(dto.getEndsAt())
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
    }
}
