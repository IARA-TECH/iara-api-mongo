package com.exemplo.iara_apimongo.services;

import com.exemplo.iara_apimongo.dto.sheetDTOs.SheetRequestDTO;
import com.exemplo.iara_apimongo.dto.sheetDTOs.SheetResponseDTO;
import com.exemplo.iara_apimongo.model.Sheet;
import com.exemplo.iara_apimongo.model.AbacusPhoto.ShiftSummary;
import com.exemplo.iara_apimongo.repository.SheetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SheetService extends BaseService<Sheet, String, SheetRequestDTO, SheetResponseDTO> {

    public SheetService(SheetRepository repository) {
        super(repository, "Sheet");
    }

    @Override
    protected Sheet toEntity(SheetRequestDTO dto) {
        ShiftSummary shift = new ShiftSummary(dto.getShiftId(), dto.getShiftName(), dto.getShiftStartsAt(), dto.getShiftEndsAt());
        return Sheet.builder()
                .factoryId(dto.getFactoryId())
                .shift(shift)
                .date(dto.getDate())
                .abacusPhotos(dto.getAbacusPhotoIds())
                .build();
    }

    @Override
    protected SheetResponseDTO toResponse(Sheet entity) {
        return SheetResponseDTO.builder()
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
    protected void updateEntity(Sheet entity, SheetRequestDTO dto) {
        entity.setFactoryId(dto.getFactoryId());
        entity.setAbacusPhotos(dto.getAbacusPhotoIds());
        entity.setDate(dto.getDate());
        entity.setShift(new ShiftSummary(dto.getShiftId(), dto.getShiftName(), dto.getShiftStartsAt(), dto.getShiftEndsAt()));
    }
}
