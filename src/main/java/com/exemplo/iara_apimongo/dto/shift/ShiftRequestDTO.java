package com.exemplo.iara_apimongo.dto.shift;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class ShiftRequestDTO {
    @NotBlank(message = "Name is mandatory")
    private String name;
    private String startsAt;
    private String endsAt;
}
