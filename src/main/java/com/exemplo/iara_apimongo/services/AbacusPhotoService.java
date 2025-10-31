package com.exemplo.iara_apimongo.services;


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
        AbacusPhoto.ShiftSummary shift = new AbacusPhoto.ShiftSummary(
                dto.getShiftId(),
                dto.getShiftName(),
                dto.getShiftStartsAt(),
                dto.getShiftEndsAt(),
                LocalDateTime.now()
        );

        Abacus abacus = Abacus.builder()
                .id(dto.getAbacusId())
                .columns(dto.getColumns())
                .build();

        return AbacusPhoto.builder()
                .factoryId(dto.getFactoryId())
                .shift(shift)
                .abacus(abacus)
                .takenBy(dto.getTakenBy())
                .takenAt(dto.getTakenAt())
                .photoUrlBlob(dto.getUrlBlob())
                .validatedBy(dto.getValidatedBy())
                .values(dto.getValues())
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
                .abacusId(entity.getAbacus() != null ? entity.getAbacus().getId() : null)
                .takenBy(entity.getTakenBy())
                .takenAt(entity.getTakenAt())
                .urlBlob(entity.getPhotoUrlBlob())
                .validatedBy(entity.getValidatedBy())
                .values(entity.getValues())
                .build();
    }

    @Override
    protected void updateEntity(AbacusPhoto entity, AbacusPhotoRequest dto) {
        entity.setTakenBy(dto.getTakenBy());
        entity.setTakenAt(dto.getTakenAt());
        entity.setPhotoUrlBlob(dto.getUrlBlob());
        entity.setValidatedBy(dto.getValidatedBy());
        entity.setValues(dto.getValues());

        Abacus abacus = entity.getAbacus();
        if (abacus == null) {
            abacus = new Abacus();
        }
        abacus.setId(dto.getAbacusId());
        abacus.setColumns(dto.getColumns());
        entity.setAbacus(abacus);

        entity.setShift(new AbacusPhoto.ShiftSummary(
                dto.getShiftId(),
                dto.getShiftName(),
                dto.getShiftStartsAt(),
                dto.getShiftEndsAt(),
                LocalDateTime.now()
        ));
    }
}
