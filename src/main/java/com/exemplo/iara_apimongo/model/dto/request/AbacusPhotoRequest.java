package com.exemplo.iara_apimongo.model.dto.request;

import com.exemplo.iara_apimongo.model.database.Abacus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

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

    @Schema(description = "User who took the photo", example = "550e8400-e29b-41d4-a716-446655440000")
    @NotBlank(message = "Taken by is mandatory.")
    private String takenBy;

    @Schema(description = "Photo blob URL", example = "https://picsum.photos/437/714")
    @NotBlank(message = "Photo URL blob is mandatory.")
    private String photoUrlBlob;

    @Schema(description = "Sheet blob URL", example = "https://picsum.photos/630/820")
    private String sheetUrlBlob;

    @Schema(description = "Values in the abacus (rows and columns)")
    @NotEmpty(message = "Values cannot be empty.")
    private List<List<Integer>> values;
}
