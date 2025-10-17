package com.exemplo.iara_apimongo.dto.shiftDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShiftResponseDTO {
    private String id;
    private String name;
    private LocalTime startsAt;
    private LocalTime endsAt;
    private Instant createdAt;
}
