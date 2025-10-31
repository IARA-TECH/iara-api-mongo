package com.exemplo.iara_apimongo.model.dto.request;

import com.exemplo.iara_apimongo.model.database.Abacus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class AbacusPhotoRequest {

    @Schema(description = "Factory ID", example = "1")
    private int factoryId;

    @Schema(description = "Shift ID", example = "shift123")
    @NotBlank(message = "Shift ID is mandatory.")
    private String shiftId;

    @Schema(description = "Abacus object reference")
    private Abacus abacus;

    @Schema(description = "User who took the photo", example = "John Doe")
    @NotBlank(message = "Taken by is mandatory.")
    private String takenBy;

    @Schema(description = "Date and time when the photo was taken", example = "2025-10-30T08:47:00")
    private Instant takenAt;

    @Schema(description = "Photo blob URL", example = "https://picsum.photos/437/714")
    @NotBlank(message = "Photo URL blob is mandatory.")
    private String photoUrlBlob;

    @Schema(description = "Sheet blob URL", example = "https://picsum.photos/630/820")
    private String sheetUrlBlob;

    @Schema(description = "Validator name", example = "Jane Doe")
    private String validatedBy;

    @Schema(description = "Values in the abacus (rows and columns)")
    @NotEmpty(message = "Values cannot be empty.")
    private List<List<Integer>> values;
}
