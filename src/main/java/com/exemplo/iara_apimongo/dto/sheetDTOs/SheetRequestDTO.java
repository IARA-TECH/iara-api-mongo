package com.exemplo.iara_apimongo.dto.sheetDTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class SheetRequestDTO {

    @Schema(description = "ID of the factory where the sheet belongs", example = "1")
    private int factoryId;

    @Schema(description = "ID of the shift", example = "shift123")
    @NotBlank(message = "Shift ID is mandatory.")
    private String shiftId;

    @Schema(description = "List of abacus photo IDs included in this sheet")
    @NotEmpty(message = "Abacus photo IDs cannot be empty.")
    private List<String> abacusPhotoIds;

    @Schema(description = "Date of the sheet", example = "2025-10-22T08:00:00Z")
    private Instant date;

    @Schema(description = "Optional shift name", example = "Morning")
    private String shiftName;

    @Schema(description = "Optional shift start time", example = "08:00")
    private String shiftStartsAt;

    @Schema(description = "Optional shift end time", example = "12:00")
    private String shiftEndsAt;
}
