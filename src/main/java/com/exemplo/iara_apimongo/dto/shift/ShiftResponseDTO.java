package com.exemplo.iara_apimongo.dto.shift;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShiftResponseDTO {
    private String id;
    private String name;
    private String startsAt;
    private String endsAt;
    private Instant createdAt;
}
