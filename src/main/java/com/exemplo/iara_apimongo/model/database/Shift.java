package com.exemplo.iara_apimongo.model.database;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "shifts")
public class Shift {
    @Id
    private String id;

    private String name;

    @Field("starts_at")
    private String startsAt;

    @Field("ends_at")
    private String endsAt;

    @Field("created_at")
    private Instant createdAt;
}
