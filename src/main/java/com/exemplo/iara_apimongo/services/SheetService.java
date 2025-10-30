package com.exemplo.iara_apimongo.services;

import com.exemplo.iara_apimongo.model.database.AbacusPhoto;
import com.exemplo.iara_apimongo.model.database.Sheet;
import com.exemplo.iara_apimongo.model.dto.request.SheetRequest;
import com.exemplo.iara_apimongo.model.dto.response.SheetResponse;
import com.exemplo.iara_apimongo.repository.SheetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SheetService extends BaseService<Sheet, String, SheetRequest, SheetResponse> {

    private final SheetRepository sheetRepository;

    public SheetService(SheetRepository repository) {
        super(repository, "Sheet");
        this.sheetRepository = repository;
    }

    @Override
    protected Sheet toEntity(SheetRequest dto) {
        AbacusPhoto.ShiftSummary shift = null;
        if (dto.getShiftId() != null) {
            shift = new AbacusPhoto.ShiftSummary(dto.getShiftId(), dto.getShiftName(), dto.getShiftStartsAt(), dto.getShiftEndsAt());
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
        if (entity.getShift() == null) {
            return SheetResponse.builder()
                    .id(entity.getId())
                    .factoryId(entity.getFactoryId())
                    .abacusPhotoIds(entity.getAbacusPhotos())
                    .date(entity.getDate())
                    .build();
        }
        return SheetResponse.builder()
                .id(entity.getId())
                .factoryId(entity.getFactoryId())
                .shiftId(entity.getShift().getId())
                .shiftName(entity.getShift().getName())
                .shiftStartsAt(entity.getShift().getStartsAt())
                .shiftEndsAt(entity.getShift().getEndsAt())
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
            shift = new AbacusPhoto.ShiftSummary(dto.getShiftId(), dto.getShiftName(), dto.getShiftStartsAt(), dto.getShiftEndsAt());
        }
        entity.setShift(shift);
    }

}
