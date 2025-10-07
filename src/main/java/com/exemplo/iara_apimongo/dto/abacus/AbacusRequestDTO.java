package com.exemplo.iara_apimongo.dto.abacus;

import com.exemplo.iara_apimongo.model.Abacus;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Map;

@Data
public class AbacusRequestDTO {
    private int factoryId;

    @NotBlank(message = "Name is mandatory")
    private String name;

    private String description;

    @NotEmpty(message = "Lines cannot be empty")
    private List<String> lines;

    @NotEmpty(message = "Columns cannot be empty")
    private List<Abacus.AbacusColumn> columns;
}
