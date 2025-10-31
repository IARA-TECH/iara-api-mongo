package com.exemplo.iara_apimongo.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class SheetRequest {

    @Schema(description = "Factory ID", example = "1")
    private int factoryId;

    @Schema(description = "Shift ID", example = "shift123")
    @NotBlank(message = "Shift ID is mandatory.")
    private String shiftId;

    @Schema(description = "IDs of abacus photos in this sheet")
    @NotEmpty(message = "Abacus photo IDs cannot be empty.")
    private List<String> abacusPhotoIds;

    @Schema(description = "Date of the sheet", example = "2025-10-30T11:44:18.873Z")
    private Instant date;

    @Schema(description = "Sheet blob URL", example = "https://placekitten.com/196/314")
    private String sheetUrlBlob;

    @Schema(description = "Shift name", example = "Noturno")
    private String shiftName;

    @Schema(description = "Shift start time", example = "18:00")
    private String shiftStartsAt;

    @Schema(description = "Shift end time", example = "23:59")
    private String shiftEndsAt;
}
