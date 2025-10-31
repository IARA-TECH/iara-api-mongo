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

import java.time.LocalDateTime;

@Slf4j
@Service
public class AbacusPhotoService extends BaseService<AbacusPhoto, String, AbacusPhotoRequest, AbacusPhotoResponse> {

    private final AbacusRepository abacusRepository;
    private final ShiftRepository shiftRepository;

    public AbacusPhotoService(AbacusPhotoRepository repository,
                              AbacusRepository abacusRepository, ShiftRepository shiftRepository) {
        super(repository, "AbacusPhoto");
        this.abacusRepository = abacusRepository;
        this.shiftRepository = shiftRepository;
    }

    @Override
    protected AbacusPhoto toEntity(AbacusPhotoRequest request) {
        return AbacusPhoto.builder()
                .factoryId(request.getFactoryId())
                .abacus(getAbacus(request.getAbacusId()))
                .takenBy(request.getTakenBy())
                .takenAt(LocalDateTime.now())
                .photoUrlBlob(request.getPhotoUrlBlob())
                .shift(getShift(request.getShiftId()))
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
                .abacusId(entity.getAbacus().getId())
                .takenBy(entity.getTakenBy())
                .takenAt(entity.getTakenAt())
                .photoUrlBlob(entity.getPhotoUrlBlob())
                .sheetUrlBlob(entity.getSheetUrlBlob())
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
        AbacusPhoto photo = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sheet not found with ID: " + id));
        photo.setValidatedBy(request.getValidatedBy());
        return toResponse(repository.save(photo));
    }
}
