package com.exemplo.iara_apimongo.services;

import com.exemplo.iara_apimongo.exception.ResourceNotFoundException;
import com.exemplo.iara_apimongo.model.database.Abacus;
import com.exemplo.iara_apimongo.model.database.AbacusPhoto;
import com.exemplo.iara_apimongo.model.database.Shift;
import com.exemplo.iara_apimongo.model.dto.request.AbacusPhotoRequest;
import com.exemplo.iara_apimongo.model.dto.request.ValidationRequest;
import com.exemplo.iara_apimongo.model.dto.response.AbacusPhotoResponse;
import com.exemplo.iara_apimongo.repository.AbacusPhotoRepository;
import com.exemplo.iara_apimongo.repository.AbacusRepository;
import com.exemplo.iara_apimongo.repository.ShiftRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;

@Slf4j
@Service
public class AbacusPhotoService extends BaseService<AbacusPhoto, String, AbacusPhotoRequest, AbacusPhotoResponse> {

    private final AbacusRepository abacusRepository;
    private final ShiftRepository shiftRepository;

    public AbacusPhotoService(AbacusPhotoRepository repository,
                              AbacusRepository abacusRepository,
                              ShiftRepository shiftRepository) {
        super(repository, "AbacusPhoto");
        this.abacusRepository = abacusRepository;
        this.shiftRepository = shiftRepository;
    }

    @Override
    protected AbacusPhoto toEntity(AbacusPhotoRequest request) {
        return AbacusPhoto.builder()
                .factoryId(request.getFactoryId())
                .abacus(getAbacus(request.getAbacus().getId()))
                .shift(getShift(request.getShiftId()))
                .takenBy(request.getTakenBy())
                .takenAt(request.getTakenAt() != null ? request.getTakenAt() : Instant.now())
                .photoUrlBlob(request.getPhotoUrlBlob())
                .sheetUrlBlob(request.getSheetUrlBlob())
                .validatedBy(request.getValidatedBy())
                .values(request.getValues())
                .build();
    }

    @Override
    protected AbacusPhotoResponse toResponse(AbacusPhoto entity) {
        return AbacusPhotoResponse.builder()
                .id(entity.getId())
                .factoryId(entity.getFactoryId())
                .takenBy(entity.getTakenBy())
                .takenAt(entity.getTakenAt())
                .validatedBy(entity.getValidatedBy())
                .photoUrlBlob(entity.getPhotoUrlBlob())
                .sheetUrlBlob(entity.getSheetUrlBlob())
                .abacus(entity.getAbacus())
                .values(entity.getValues())
                .shiftId(entity.getShift() != null ? entity.getShift().getId() : null)
                .shiftName(entity.getShift() != null ? entity.getShift().getName() : null)
                .shiftStartsAt(entity.getShift() != null ? entity.getShift().getStartsAt() : null)
                .shiftEndsAt(entity.getShift() != null ? entity.getShift().getEndsAt() : null)
                .shiftCreatedAt(entity.getShift() != null ? entity.getShift().getCreatedAt() : null)
                .build();
    }

    @Override
    protected void updateEntity(AbacusPhoto entity, AbacusPhotoRequest request) {

    }

    private Abacus getAbacus(String abacusId) {
        return abacusRepository.findById(abacusId)
                .orElseThrow(() -> new ResourceNotFoundException("Abacus not found with id: " + abacusId));
    }

    private Shift getShift(String shiftId) {
        return shiftRepository.findById(shiftId)
                .orElseThrow(() -> new ResourceNotFoundException("Shift not found with id: " + shiftId));
    }

    public AbacusPhotoResponse validate(String id, ValidationRequest request) {
        AbacusPhoto photo = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sheet not found with ID: " + id));
        photo.setValidatedBy(request.getValidatedBy());
        return toResponse(repository.save(photo));
    }
}
