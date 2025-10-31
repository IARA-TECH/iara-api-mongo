package com.exemplo.iara_apimongo.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class SheetRequest {

    @Schema(description = "ID of the factory where the sheet belongs", example = "1")
    private int factoryId;

    @Schema(description = "ID of the shift", example = "shift123")
    @NotBlank(message = "Shift ID is required.")
    private String shiftId;

    @Schema(description = "List of abacus photo IDs included in this sheet")
    @NotEmpty(message = "Abacus photo IDs can not be empty.")
    private List<String> abacusPhotoIds;
}
