package com.exemplo.iara_apimongo.services;

import com.exemplo.iara_apimongo.dto.sheetDTOs.SheetRequestDTO;
import com.exemplo.iara_apimongo.dto.sheetDTOs.SheetResponseDTO;
import com.exemplo.iara_apimongo.model.Sheet;
import com.exemplo.iara_apimongo.model.AbacusPhoto.ShiftSummary;
import com.exemplo.iara_apimongo.repository.SheetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SheetService extends BaseService<Sheet, String, SheetRequestDTO, SheetResponseDTO> {

    private final SheetRepository sheetRepository;

    public SheetService(SheetRepository repository) {
        super(repository, "Sheet");
        this.sheetRepository = repository;
    }

    @Override
    protected Sheet toEntity(SheetRequestDTO dto) {
        ShiftSummary shift = null;
        if (dto.getShiftId() != null) {
            shift = new ShiftSummary(dto.getShiftId(), dto.getShiftName(), dto.getShiftStartsAt(), dto.getShiftEndsAt());
        }

        return Sheet.builder()
                .factoryId(dto.getFactoryId())
                .shift(shift)
                .date(dto.getDate())
                .abacusPhotos(dto.getAbacusPhotoIds())
                .build();
    }

    @Override
    protected SheetResponseDTO toResponse(Sheet entity) {
        if (entity.getShift() == null) {
            // evitar NPE
            return SheetResponseDTO.builder()
                    .id(entity.getId())
                    .factoryId(entity.getFactoryId())
                    .abacusPhotoIds(entity.getAbacusPhotos())
                    .date(entity.getDate())
                    .build();
        }
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

        ShiftSummary shift = null;
        if (dto.getShiftId() != null) {
            shift = new ShiftSummary(dto.getShiftId(), dto.getShiftName(), dto.getShiftStartsAt(), dto.getShiftEndsAt());
        }
        entity.setShift(shift);
    }

    public List<SheetResponseDTO> findByFactoryId(Integer factoryId) {
        List<Sheet> sheets = sheetRepository.findByFactoryId(factoryId);
        return sheets.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
