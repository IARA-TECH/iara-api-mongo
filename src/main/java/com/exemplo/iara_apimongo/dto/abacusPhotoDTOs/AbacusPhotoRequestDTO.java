package com.exemplo.iara_apimongo.dto.abacusPhotoDTOs;

import com.exemplo.iara_apimongo.model.Abacus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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

    private LocalDateTime date;

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
