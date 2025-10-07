package com.exemplo.iara_apimongo.dto.sheet;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

@Data
public class SheetRequestDTO {
    private int factoryId;

    @NotBlank(message = "Shift ID is mandatory")
    private String shiftId;

    @NotEmpty(message = "Abacus photo IDs cannot be empty")
    private List<String> abacusPhotoIds;

    private LocalDate date;

    // Shift info
    private String shiftName;
    private String shiftStartsAt;
    private String shiftEndsAt;
}
