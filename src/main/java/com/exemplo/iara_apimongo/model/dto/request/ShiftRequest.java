package com.exemplo.iara_apimongo.model.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalTime;

@Data
public class ShiftRequest {

    @Schema(description = "Shift name", example = "Morning Shift")
    @NotBlank(message = "Shift name is required.")
    private String name;

    @Schema(description = "Start time (HH:mm)", example = "08:00")
    @NotNull(message = "Start time is required.")
    @JsonFormat(pattern = "HH:mm")
    private String startsAt;

    @Schema(description = "End time (HH:mm)", example = "12:00")
    @NotNull(message = "End time is required.")
    @JsonFormat(pattern = "HH:mm")
    private String endsAt;
}
