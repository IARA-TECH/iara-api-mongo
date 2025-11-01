package com.exemplo.iara_apimongo.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class SheetRequest {

    @Schema(description = "Factory ID", example = "1")
    private int factoryId;

    @Schema(description = "Shift ID related to this sheet", example = "shift123")
    @NotBlank(message = "Shift ID is required.")
    private String shiftId;

    @Schema(description = "IDs of abacus photos in this sheet")
    @NotEmpty(message = "Abacus photo IDs can not be empty.")
    private List<String> abacusPhotoIds;

    @Schema(description = "Sheet blob URL", example = "https://storage.example.com/sheet123.jpg")
    private String sheetUrlBlob;
}
