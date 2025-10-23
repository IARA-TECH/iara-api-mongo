package com.exemplo.iara_apimongo.services;

import com.exemplo.iara_apimongo.dto.abacusPhotoDTOs.AbacusPhotoRequestDTO;
import com.exemplo.iara_apimongo.dto.abacusPhotoDTOs.AbacusPhotoResponseDTO;
import com.exemplo.iara_apimongo.model.AbacusPhoto;
import com.exemplo.iara_apimongo.model.AbacusPhoto.ShiftSummary;
import com.exemplo.iara_apimongo.model.Abacus;
import com.exemplo.iara_apimongo.repository.AbacusPhotoRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AbacusPhotoService extends BaseService<AbacusPhoto, String, AbacusPhotoRequestDTO, AbacusPhotoResponseDTO> {

    public AbacusPhotoService(AbacusPhotoRepository repository) {
        super(repository, "AbacusPhoto");
    }

    @Override
    protected AbacusPhoto toEntity(AbacusPhotoRequestDTO dto) {
        ShiftSummary shift = new ShiftSummary(
                dto.getShiftId(),
                dto.getShiftName(),
                dto.getShiftStartsAt(),
                dto.getShiftEndsAt()
        );

        List<ObjectId> linesAsObjectId = null;
        if (dto.getLines() != null) {
            linesAsObjectId = dto.getLines().stream()
                    .map(ObjectId::new)
                    .collect(Collectors.toList());
        }

        return AbacusPhoto.builder()
                .factoryId(dto.getFactoryId())
                .abacusId(dto.getAbacusId())
                .takenBy(dto.getTakenBy())
                .date(dto.getDate() != null ? dto.getDate() : LocalDateTime.now())
                .takenAt(dto.getTakenAt())
                .urlBlob(dto.getUrlBlob())
                .validatedBy(dto.getValidatedBy())
                .lines(linesAsObjectId)
                .columns(dto.getColumns())
                .values(dto.getValues())
                .shift(shift)
                .build();
    }

    @Override
    protected AbacusPhotoResponseDTO toResponse(AbacusPhoto entity) {
        List<String> linesAsString = null;
        if (entity.getLines() != null) {
            linesAsString = entity.getLines().stream()
                    .map(ObjectId::toHexString)
                    .collect(Collectors.toList());
        }

        List<Abacus.AbacusColumn> columnsAsIs = entity.getColumns();

        return AbacusPhotoResponseDTO.builder()
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
                .lines(linesAsString)
                .columns(columnsAsIs)
                .values(entity.getValues())
                .build();
    }

    @Override
    protected void updateEntity(AbacusPhoto entity, AbacusPhotoRequestDTO dto) {
        entity.setAbacusId(dto.getAbacusId());
        entity.setTakenBy(dto.getTakenBy());
        entity.setDate(dto.getDate() != null ? dto.getDate() : entity.getDate());
        entity.setTakenAt(dto.getTakenAt());
        entity.setUrlBlob(dto.getUrlBlob());
        entity.setValidatedBy(dto.getValidatedBy());

        if (dto.getLines() != null) {
            List<ObjectId> linesAsObjectId = dto.getLines().stream()
                    .map(ObjectId::new)
                    .collect(Collectors.toList());
            entity.setLines(linesAsObjectId);
        }

        entity.setColumns(dto.getColumns());
        entity.setValues(dto.getValues());
        entity.setShift(new ShiftSummary(dto.getShiftId(), dto.getShiftName(), dto.getShiftStartsAt(), dto.getShiftEndsAt()));
    }
}
