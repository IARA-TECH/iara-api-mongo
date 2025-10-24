package com.exemplo.iara_apimongo.dto.dashboardsDTOs;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardTurnosDTO {

    @Schema(description = "Dashboard title", example = "Shift Overview")
    private String title;

    @Schema(description = "Quantity per shift")
    private List<QuantityPerShiftDTO> quantityPerShift;

    @Schema(description = "Monthly evolution per shift")
    private MonthlyEvolutionDTO monthlyEvolution;

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    @Schema(description = "Quantity data per shift")
    public static class QuantityPerShiftDTO {
        @Schema(description = "Shift name", example = "Morning")
        private String shift;

        @Schema(description = "Quantity for the shift", example = "10")
        private int quantity;
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    @Schema(description = "Monthly evolution data for shifts")
    public static class MonthlyEvolutionDTO {
        @Schema(description = "List of periods", example = "[\"Jan\", \"Feb\"]")
        private List<String> periods;

        @Schema(description = "Values for morning shift", example = "[5, 8]")
        private List<Integer> morning;

        @Schema(description = "Values for afternoon shift", example = "[3, 6]")
        private List<Integer> afternoon;

        @Schema(description = "Values for night shift", example = "[2, 4]")
        private List<Integer> night;
    }
}
