package com.exemplo.iara_apimongo.model.database;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

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

    @Field("factory_id")
    private Integer factoryId;

    private Shift shift;

    private Instant date;

    @Field("sheet_url_blob")
    private String sheetUrlBlob;

    @Field("abacus_photos")
    private List<String> abacusPhotos;
}
