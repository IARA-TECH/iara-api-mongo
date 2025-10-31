package com.exemplo.iara_apimongo.model.dto.response;

import com.exemplo.iara_apimongo.model.database.Abacus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AbacusResponse {
    private String id;
    private int factoryId;
    private String name;
    private String description;
    private List<Abacus.AbacusLine> lines;
    private List<Abacus.AbacusColumn> columns;
}
