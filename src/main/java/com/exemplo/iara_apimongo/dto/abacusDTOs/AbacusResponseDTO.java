package com.exemplo.iara_apimongo.dto.abacusDTOs;

import com.exemplo.iara_apimongo.model.Abacus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AbacusResponseDTO {
    private String id;
    private int factoryId;
    private String name;
    private String description;
    private List<String> lines;
    private List<Abacus.AbacusColumn> columns;
}
