package com.exemplo.iara_apimongo.model.dto.response;

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
public class ShiftResponse {
    private String id;
    private String name;
    private Instant startsAt;
    private Instant endsAt;
    private Instant createdAt;
}
