package com.exemplo.iara_apimongo.model.database;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "abacuses")
public class Abacus {
    @Id
    private String id;
    private Integer factoryId;
    private String name;
    private String description;
    private List<String> lines;
    private List<AbacusColumn> columns;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AbacusColumn {
        private String color;
        private Integer value;
    }
}
