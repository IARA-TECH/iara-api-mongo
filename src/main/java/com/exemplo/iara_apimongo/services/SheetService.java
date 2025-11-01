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

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class SheetService extends BaseService<Sheet, String, SheetRequest, SheetResponse> {

    private final ShiftRepository shiftRepository;

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public SheetService(SheetRepository repository, ShiftRepository shiftRepository) {
        super(repository, "Sheet");
        this.shiftRepository = shiftRepository;
    }

    @Override
    protected Sheet toEntity(SheetRequest request) {
        Instant date = Instant.now();

        List<String> abacusPhotos = request.getAbacusPhotoIds() != null
                ? new ArrayList<>(request.getAbacusPhotoIds())
                : new ArrayList<>();

        Shift shift = null;
        if (request.getShiftId() != null && !request.getShiftId().isBlank()) {
            shift = getShift(request.getShiftId());
        }

        return Sheet.builder()
                .factoryId(request.getFactoryId())
                .shift(shift)
                .date(date)
                .abacusPhotos(abacusPhotos)
                .sheetUrlBlob(request.getSheetUrlBlob())
                .build();
    }

    @Override
    protected SheetResponse toResponse(Sheet entity) {
        String startsAt = null;
        String endsAt = null;
        Shift shift = entity.getShift();
        if (shift != null) {
            if (shift.getStartsAt() != null) {
                startsAt = shift.getStartsAt();
            }
            if (shift.getEndsAt() != null) {
                endsAt = shift.getEndsAt();
            }
        }

        return SheetResponse.builder()
                .id(entity.getId())
                .factoryId(entity.getFactoryId())
                .abacusPhotoIds(entity.getAbacusPhotos() != null ? new ArrayList<>(entity.getAbacusPhotos()) : List.of())
                .date(entity.getDate())
                .sheetUrlBlob(entity.getSheetUrlBlob())
                .shiftId(shift != null ? shift.getId() : null)
                .shiftName(shift != null ? shift.getName() : null)
                .shiftStartsAt(startsAt)
                .shiftEndsAt(endsAt)
                .build();
    }

    private Shift getShift(String shiftId) {
        return shiftRepository.findById(shiftId)
                .orElseThrow(() -> new ResourceNotFoundException("Shift not found with id: " + shiftId));
    }
}
