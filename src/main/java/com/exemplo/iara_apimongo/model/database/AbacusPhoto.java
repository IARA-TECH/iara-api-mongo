package com.exemplo.iara_apimongo.model.database;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "abacus_photos")
public class AbacusPhoto {
    @Id
    private String id;
    private Integer factoryId;
    private ShiftSummary shift;
    private String abacusId;
    private String takenBy;
    private LocalDateTime date;
    private LocalDateTime takenAt;
    private String urlBlob;
    private String validatedBy;
    private List<String> lines;
    private List<Abacus.AbacusColumn> columns;
    private List<List<Integer>> values;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ShiftSummary {
        private String id;
        private String name;
        private String startsAt;
        private String endsAt;
    }
}
