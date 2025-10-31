package com.exemplo.iara_apimongo.model.dto.request;

import com.exemplo.iara_apimongo.model.database.Abacus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class AbacusPhotoRequest {

    @Schema(description = "ID of the factory where the photo was taken", example = "1")
    private int factoryId;

    @Schema(description = "ID of the shift", example = "shift123")
    @NotBlank(message = "Shift ID is mandatory.")
    private String shiftId;

    @Schema(description = "ID of the abacus", example = "abacus123")
    @NotBlank(message = "Abacus ID is mandatory.")
    private String abacusId;

    @Schema(description = "Name of the person who took the photo", example = "John Doe")
    @NotBlank(message = "Taken by is mandatory.")
    private String takenBy;

    @Schema(description = "URL of the stored photo blob")
    @NotBlank(message = "URL blob is mandatory.")
    private String photoUrlBlob;

    @Schema(description = "Lines of the abacus")
    @NotEmpty(message = "Lines cannot be empty.")
    private List<String> lines;

    @Schema(description = "Columns of the abacus")
    @NotEmpty(message = "Columns cannot be empty.")
    private List<Abacus.AbacusColumn> columns;
}
