package com.exemplo.iara_apimongo.services;

import com.exemplo.iara_apimongo.model.database.AbacusPhoto;
import com.exemplo.iara_apimongo.model.database.Sheet;
import com.exemplo.iara_apimongo.model.dto.request.SheetRequest;
import com.exemplo.iara_apimongo.model.dto.response.SheetResponse;
import com.exemplo.iara_apimongo.repository.SheetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class SheetService extends BaseService<Sheet, String, SheetRequest, SheetResponse> {

    public SheetService(SheetRepository repository) {
        super(repository, "Sheet");
    }

    @Override
    protected Sheet toEntity(SheetRequest dto) {
        AbacusPhoto.ShiftSummary shift = null;
        if (dto.getShiftId() != null) {
            shift = new AbacusPhoto.ShiftSummary(
                    dto.getShiftId(),
                    dto.getShiftName(),
                    dto.getShiftStartsAt(),
                    dto.getShiftEndsAt(),
                    LocalDateTime.now()
            );
        }

        return Sheet.builder()
                .factoryId(dto.getFactoryId())
                .shift(shift)
                .date(dto.getDate())
                .abacusPhotos(dto.getAbacusPhotoIds())
                .build();
    }

    @Override
    protected SheetResponse toResponse(Sheet entity) {
        return SheetResponse.builder()
                .id(entity.getId())
                .factoryId(entity.getFactoryId())
                .shiftId(entity.getShift() != null ? entity.getShift().getId() : null)
                .shiftName(entity.getShift() != null ? entity.getShift().getName() : null)
                .shiftStartsAt(entity.getShift() != null ? entity.getShift().getStartsAt() : null)
                .shiftEndsAt(entity.getShift() != null ? entity.getShift().getEndsAt() : null)
                .abacusPhotoIds(entity.getAbacusPhotos())
                .date(entity.getDate())
                .build();
    }

    @Override
    protected void updateEntity(Sheet entity, SheetRequest dto) {
        entity.setFactoryId(dto.getFactoryId());
        entity.setAbacusPhotos(dto.getAbacusPhotoIds());
        entity.setDate(dto.getDate());

        AbacusPhoto.ShiftSummary shift = null;
        if (dto.getShiftId() != null) {
            shift = new AbacusPhoto.ShiftSummary(
                    dto.getShiftId(),
                    dto.getShiftName(),
                    dto.getShiftStartsAt(),
                    dto.getShiftEndsAt(),
                    LocalDateTime.now()
            );
        }
        entity.setShift(shift);
    }
}
