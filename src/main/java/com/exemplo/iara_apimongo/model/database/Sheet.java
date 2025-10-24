package com.exemplo.iara_apimongo.model.database;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "sheets")
public class Sheet {
    @Id
    private String id;

    private Integer factoryId;

    private AbacusPhoto.ShiftSummary shift;

    private Instant date;

    private List<String> abacusPhotos;
}
