package com.exemplo.iara_apimongo.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationRequest {
    @Schema(description = "Id of the person who validated the photo", example = "9f3d1a87-6a3b-4e1f-b0a7-91c2e52ac0ce")
    private String validatedBy;
}
