package com.exemplo.iara_apimongo.model.database;

import lombok.*;
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

    private Shift shift;

    @Field("abacus_id")
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
}
