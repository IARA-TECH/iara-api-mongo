package com.exemplo.iara_apimongo.model.dto.request;

import com.exemplo.iara_apimongo.model.database.Abacus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class AbacusRequest {

    @Schema(description = "ID of the factory where the abacus belongs", example = "1")
    private int factoryId;

    @Schema(description = "Abacus name", example = "Production Abacus A")
    @NotBlank(message = "Abacus name is mandatory.")
    private String name;

    @Schema(description = "Optional description of the abacus", example = "Used in morning shifts")
    private String description;

    @Schema(description = "Lines of the abacus")
    @NotEmpty(message = "Lines cannot be empty.")
    private List<Abacus.AbacusLine> lines;

    @Schema(description = "Columns of the abacus")
    @NotEmpty(message = "Columns cannot be empty.")
    private List<Abacus.AbacusColumn> columns;
}
