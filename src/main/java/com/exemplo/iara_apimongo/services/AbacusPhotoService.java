package com.exemplo.iara_apimongo.services;

import com.exemplo.iara_apimongo.exception.BadRequestException;
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

@Slf4j
@Service
public class AbacusPhotoService extends BaseService<AbacusPhoto, String, AbacusPhotoRequest, AbacusPhotoResponse> {

    private final AbacusRepository abacusRepository;
    private final ShiftRepository shiftRepository;
    private final AbacusPhotoRepository abacusPhotoRepository;

    public AbacusPhotoService(
            AbacusPhotoRepository repository,
            AbacusRepository abacusRepository,
            ShiftRepository shiftRepository
    ) {
        super(repository, "Abacus photo");
        this.abacusRepository = abacusRepository;
        this.shiftRepository = shiftRepository;
        this.abacusPhotoRepository = repository;
    }

    @Override
    protected AbacusPhoto toEntity(AbacusPhotoRequest request) {
        if (request.getShiftId() == null || request.getShiftId().isBlank()) {
            throw new BadRequestException("Shift ID is mandatory for abacus photo.");
        }

        Shift shift = shiftRepository.findById(request.getShiftId())
                .orElseThrow(() -> new ResourceNotFoundException("Shift not found with ID: " + request.getShiftId()));

        Abacus abacus = null;
        if (request.getAbacus() != null && request.getAbacus().getId() != null) {
            abacus = abacusRepository.findById(request.getAbacus().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Abacus not found with ID: " + request.getAbacus().getId()));
        }

        return AbacusPhoto.builder()
                .factoryId(request.getFactoryId())
                .shift(shift)
                .abacus(abacus)
                .takenAt(Instant.now())
                .photoUrlBlob(request.getPhotoUrlBlob())
                .sheetUrlBlob(request.getSheetUrlBlob())
                .values(request.getValues())
                .validatedBy(null)
                .build();
    }

    @Override
    protected AbacusPhotoResponse toResponse(AbacusPhoto entity) {
        Shift shift = entity.getShift();

        return AbacusPhotoResponse.builder()
                .id(entity.getId())
                .factoryId(entity.getFactoryId())
                .takenAt(entity.getTakenAt())
                .photoUrlBlob(entity.getPhotoUrlBlob())
                .sheetUrlBlob(entity.getSheetUrlBlob())
                .abacus(entity.getAbacus())
                .values(entity.getValues())
                .shiftId(shift != null ? shift.getId() : null)
                .shiftName(shift != null ? shift.getName() : null)
                .shiftStartsAt(shift != null ? shift.getStartsAt().toString() : null)
                .shiftEndsAt(shift != null ? shift.getEndsAt().toString() : null)
                .shiftCreatedAt(shift != null ? shift.getCreatedAt() : null)
                .validatedBy(entity.getValidatedBy())
                .build();
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
        AbacusPhoto photo = abacusPhotoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Abacus photo not found with ID: " + id));

        photo.setValidatedBy(request.getValidatedBy());
        AbacusPhoto saved = abacusPhotoRepository.save(photo);
        return toResponse(saved);
    }
}
