package com.exemplo.iara_apimongo.dto.shift;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.time.LocalTime;

@Data
@Builder
public class ShiftResponseDTO {

    @Schema(description = "ID do turno", example = "67056f1e2fa1a31a59f1a2b3")
    private String id;

    @Schema(description = "Nome do turno", example = "Turno da Tarde")
    private String name;

    @Schema(description = "Horário de início (HH:mm)", example = "13:00")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime startsAt;

    @Schema(description = "Horário de término (HH:mm)", example = "18:00")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime endsAt;

    @Schema(description = "Data e hora de criação em UTC", example = "2025-10-08T11:40:00Z")
    private Instant createdAt;
}
