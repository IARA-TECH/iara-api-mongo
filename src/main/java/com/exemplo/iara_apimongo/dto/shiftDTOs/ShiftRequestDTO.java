package com.exemplo.iara_apimongo.dto.shiftDTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalTime;

@Data
public class ShiftRequestDTO {

    @Schema(description = "Nome do turno", example = "Turno da Manhã")
    @NotBlank(message = "O nome do turno é obrigatório.")
    private String name;

    @Schema(description = "Horário de início (HH:mm)", example = "08:00")
    @NotNull(message = "O horário de início é obrigatório.")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime startsAt;

    @Schema(description = "Horário de término (HH:mm)", example = "12:00")
    @NotNull(message = "O horário de término é obrigatório.")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime endsAt;
}
