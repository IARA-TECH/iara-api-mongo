package com.exemplo.iara_apimongo.dto.dashboardsDTOs;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardGranjaDTO {

    @Schema(description = "Dashboard title", example = "Farm Condemnations")
    private String title;

    @Schema(description = "Total number of farm condemnations", example = "30")
    private int total;

    @Schema(description = "Average condemnation rate", example = "15.0")
    private double averageRate;

    @Schema(description = "Comparison with previous period", example = "25")
    private int previousComparison;

    @Schema(description = "Ranking of condemnation reasons")
    private List<ReasonRankingDTO> reasonRanking;

    @Schema(description = "Data grouped by factory")
    private List<ByFactoryDTO> byFactory;

    @Schema(description = "Monthly evolution of farm data")
    private DashboardFalhasDTO.MonthlyEvolutionDTO monthlyEvolution;

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    @Schema(description = "Ranking of reasons for farm condemnations")
    public static class ReasonRankingDTO {
        @Schema(description = "Reason", example = "Contamination")
        private String reason;

        @Schema(description = "Number of occurrences", example = "5")
        private int quantity;
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    @Schema(description = "Data by factory")
    public static class ByFactoryDTO {
        @Schema(description = "Factory name", example = "Factory A")
        private String factory;

        @Schema(description = "Number of occurrences", example = "15")
        private int quantity;
    }
}
