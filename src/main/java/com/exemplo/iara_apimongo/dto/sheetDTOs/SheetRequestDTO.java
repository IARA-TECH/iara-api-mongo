package com.exemplo.iara_apimongo.dto.sheetDTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class SheetRequestDTO {
    private int factoryId;

    @NotBlank(message = "Shift ID is mandatory")
    private String shiftId;

    @NotEmpty(message = "Abacus photo IDs cannot be empty")
    private List<String> abacusPhotoIds;

    private Instant date;

    // Shift info
    private String shiftName;
    private String shiftStartsAt;
    private String shiftEndsAt;
}
