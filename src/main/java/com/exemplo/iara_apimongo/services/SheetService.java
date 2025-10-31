package com.exemplo.iara_apimongo.services;

import com.exemplo.iara_apimongo.exception.ResourceNotFoundException;
import com.exemplo.iara_apimongo.model.database.Sheet;
import com.exemplo.iara_apimongo.model.database.Shift;
import com.exemplo.iara_apimongo.model.dto.request.SheetRequest;
import com.exemplo.iara_apimongo.model.dto.response.SheetResponse;
import com.exemplo.iara_apimongo.repository.SheetRepository;
import com.exemplo.iara_apimongo.repository.ShiftRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class SheetService extends BaseService<Sheet, String, SheetRequest, SheetResponse> {

    private final ShiftRepository shiftRepository;

    public SheetService(SheetRepository repository,
                        ShiftRepository shiftRepository) {
        super(repository, "Sheet");
        this.shiftRepository = shiftRepository;
    }

    @Override
    protected Sheet toEntity(SheetRequest request) {
        return Sheet.builder()
                .factoryId(request.getFactoryId())
                .shift(getShift(request.getShiftId()))
                .date(LocalDateTime.now())
                .abacusPhotos(request.getAbacusPhotoIds())
                .build();
    }

    @Override
    protected SheetResponse toResponse(Sheet entity) {
        return SheetResponse.builder()
                .id(entity.getId())
                .factoryId(entity.getFactoryId())
                .shiftId(entity.getShift().getId())
                .shiftName(entity.getShift().getName())
                .shiftStartsAt(entity.getShift().getStartsAt())
                .shiftEndsAt(entity.getShift().getEndsAt())
                .abacusPhotoIds(entity.getAbacusPhotos())
                .date(LocalDateTime.now())
                .build();
    }


    private Shift getShift(String shiftId) {
        return shiftRepository.findById(shiftId)
                .orElseThrow(() -> new ResourceNotFoundException("Shift not found with ID: " + shiftId));
    }
}
