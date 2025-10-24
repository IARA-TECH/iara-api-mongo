package com.exemplo.iara_apimongo.model.database;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private LocalTime startsAt;

    @NotBlank
    private LocalTime endsAt;

    private Instant createdAt;
}
