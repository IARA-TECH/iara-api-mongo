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

    @Schema(description = "Date and time when the photo was taken", example = "2025-10-22T08:00:00")
    private LocalDateTime takenAt;

    @Schema(description = "Optional date of the abacus entry", example = "2025-10-22T08:00:00")
    private LocalDateTime date;

    @Schema(description = "URL of the stored photo blob")
    @NotBlank(message = "URL blob is mandatory.")
    private String urlBlob;

    @Schema(description = "Name of the person who validated the photo", example = "Jane Doe")
    private String validatedBy;

    @Schema(description = "Lines of the abacus")
    @NotEmpty(message = "Lines cannot be empty.")
    private List<String> lines;

    @Schema(description = "Columns of the abacus")
    @NotEmpty(message = "Columns cannot be empty.")
    private List<Abacus.AbacusColumn> columns;

    @Schema(description = "Values in the abacus, organized by rows and columns")
    @NotEmpty(message = "Values cannot be empty.")
    private List<List<Integer>> values;

    @Schema(description = "Optional shift name", example = "Morning")
    private String shiftName;

    @Schema(description = "Optional shift start time", example = "08:00")
    private String shiftStartsAt;

    @Schema(description = "Optional shift end time", example = "12:00")
    private String shiftEndsAt;
}
