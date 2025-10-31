package com.exemplo.iara_apimongo.model.database;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "abacuses")
public class Abacus {
    @Id
    private String id;

    @Field("factory_id")
    private Integer factoryId;
    private String name;
    private String description;
    private List<AbacusLine> lines;
    private List<AbacusColumn> columns;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AbacusLine {
        private String name;

        @Field("line_type")
        private LineType lineType;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AbacusColumn {
        private String color;
        private Integer value;
        private String name;
    }
}
