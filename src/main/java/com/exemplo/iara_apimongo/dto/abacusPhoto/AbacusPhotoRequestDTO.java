package com.exemplo.iara_apimongo.dto.abacusPhoto;

import com.exemplo.iara_apimongo.model.Abacus;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
public class AbacusPhotoRequestDTO {
    private int factoryId;

    @NotBlank(message = "Shift ID is mandatory")
    private String shiftId;

    @NotBlank(message = "Abacus ID is mandatory")
    private String abacusId;

    @NotBlank(message = "Taken by is mandatory")
    private String takenBy;

    private LocalDateTime takenAt;

    @NotBlank(message = "URL blob is mandatory")
    private String urlBlob;

    private String validatedBy;

    @NotEmpty(message = "Lines cannot be empty")
    private List<String> lines;

    @NotEmpty(message = "Columns cannot be empty")
    private List<Abacus.AbacusColumn> columns;

    @NotEmpty(message = "Values cannot be empty")
    private List<List<Integer>> values;

    // Shift info
    private String shiftName;
    private String shiftStartsAt;
    private String shiftEndsAt;
}

