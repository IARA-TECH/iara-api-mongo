package com.exemplo.iara_apimongo.model.database;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

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

    @Field("factory_id")
    private Integer factoryId;

    private ShiftSummary shift;

    private Abacus abacus;

    @Field("taken_by")
    private String takenBy;

    @Field("taken_at")
    private LocalDateTime takenAt;

    @Field("photo_url_blob")
    private String photoUrlBlob;

    @Field("sheet_url_blob")
    private String sheetUrlBlob;

    @Field("validated_by")
    private String validatedBy;

    private List<List<Integer>> values;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ShiftSummary {
        @Field("_id")
        private String id;

        private String name;

        @Field("starts_at")
        private String startsAt;

        @Field("ends_at")
        private String endsAt;

        @Field("created_at")
        private LocalDateTime createdAt;
    }
}
