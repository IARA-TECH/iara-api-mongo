package com.exemplo.iara_apimongo.dto.abacusDTOs;

import com.exemplo.iara_apimongo.model.Abacus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

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
