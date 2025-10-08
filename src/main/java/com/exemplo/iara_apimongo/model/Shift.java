package com.exemplo.iara_apimongo.model;

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
    private String name;
    private LocalTime startsAt;
    private LocalTime endsAt;
    private Instant createdAt;
}
