package com.exemplo.iara_apimongo.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

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
    private String startsAt;

    @NotBlank
    private String endsAt;

    private Instant createdAt;
}
