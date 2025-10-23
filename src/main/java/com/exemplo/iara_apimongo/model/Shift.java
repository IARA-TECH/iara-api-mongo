package com.exemplo.iara_apimongo.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "shifts")
public class Shift {
    @Id
    private String id;

    @NotBlank
    private String name;

    @NotBlank
    @Field("starts_at")
    private String startsAt;

    @NotBlank
    @Field("ends_at")
    private String endsAt;

    @Field("created_at")
    private Instant createdAt;
}
