package com.exemplo.iara_apimongo.services;

import com.exemplo.iara_apimongo.model.dto.request.AbacusPhotoRequest;
import com.exemplo.iara_apimongo.model.dto.response.AbacusPhotoResponse;
import com.exemplo.iara_apimongo.model.database.AbacusPhoto;
import com.exemplo.iara_apimongo.model.database.AbacusPhoto.ShiftSummary;
import com.exemplo.iara_apimongo.repository.AbacusPhotoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class AbacusPhotoService extends BaseService<AbacusPhoto, String, AbacusPhotoRequest, AbacusPhotoResponse> {

    public AbacusPhotoService(AbacusPhotoRepository repository) {
        super(repository, "AbacusPhoto");
    }

    @Override
    protected AbacusPhoto toEntity(AbacusPhotoRequest dto) {
        ShiftSummary shift = new ShiftSummary(
                dto.getShiftId(),
                dto.getShiftName(),
                dto.getShiftStartsAt(),
                dto.getShiftEndsAt()
        );

        return AbacusPhoto.builder()
                .factoryId(dto.getFactoryId())
                .abacusId(dto.getAbacusId())
                .takenBy(dto.getTakenBy())
                .date(dto.getDate() != null ? dto.getDate() : LocalDateTime.now())
                .takenAt(dto.getTakenAt())
                .urlBlob(dto.getUrlBlob())
                .validatedBy(dto.getValidatedBy())
                .lines(dto.getLines())
                .columns(dto.getColumns())
                .values(dto.getValues())
                .shift(shift)
                .build();
    }


    @Override
    protected AbacusPhotoResponse toResponse(AbacusPhoto entity) {
        return AbacusPhotoResponse.builder()
                .id(entity.getId())
                .factoryId(entity.getFactoryId())
                .shiftId(entity.getShift().getId())
                .shiftName(entity.getShift().getName())
                .shiftStartsAt(entity.getShift().getStartsAt())
                .shiftEndsAt(entity.getShift().getEndsAt())
                .abacusId(entity.getAbacusId())
                .takenBy(entity.getTakenBy())
                .date(entity.getDate())
                .takenAt(entity.getTakenAt())
                .urlBlob(entity.getUrlBlob())
                .validatedBy(entity.getValidatedBy())
                .lines(entity.getLines())
                .columns(entity.getColumns())
                .values(entity.getValues())
                .build();
    }

    @Override
    protected void updateEntity(AbacusPhoto entity, AbacusPhotoRequest dto) {
        entity.setAbacusId(dto.getAbacusId());
        entity.setTakenBy(dto.getTakenBy());
        entity.setDate(dto.getDate() != null ? dto.getDate() : entity.getDate());
        entity.setTakenAt(dto.getTakenAt());
        entity.setUrlBlob(dto.getUrlBlob());
        entity.setValidatedBy(dto.getValidatedBy());
        entity.setLines(dto.getLines());
        entity.setColumns(dto.getColumns());
        entity.setValues(dto.getValues());
        entity.setShift(new ShiftSummary(dto.getShiftId(), dto.getShiftName(), dto.getShiftStartsAt(), dto.getShiftEndsAt()));
    }
}
