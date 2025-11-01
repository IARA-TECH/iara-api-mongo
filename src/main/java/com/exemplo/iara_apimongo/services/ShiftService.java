package com.exemplo.iara_apimongo.services;

import com.exemplo.iara_apimongo.model.database.Shift;
import com.exemplo.iara_apimongo.model.dto.request.ShiftRequest;
import com.exemplo.iara_apimongo.model.dto.response.ShiftResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Slf4j
@Service
public class ShiftService extends BaseService<Shift, String, ShiftRequest, ShiftResponse> {

    public ShiftService(MongoRepository<Shift, String> repository) {
        super(repository, "Shift");
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
    }
}
