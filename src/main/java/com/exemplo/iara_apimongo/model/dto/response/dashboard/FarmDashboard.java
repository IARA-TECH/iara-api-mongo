package com.exemplo.iara_apimongo.model.dto.response.dashboard;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FarmDashboard {

    @Schema(description = "Dashboard title", example = "Farm Condemnations")
    private String title;

    @Schema(description = "Total number of farm condemnations", example = "30")
    private int total;

    @Schema(description = "Average condemnation rate", example = "15.0")
    private double averageRate;

    @Schema(description = "Comparison with previous period", example = "25")
    private int previousComparison;

    @Schema(description = "Ranking of condemnation reasons")
    private List<ReasonRanking> reasonRanking;

    @Schema(description = "Monthly evolution of farm data")
    private FailuresDashboard.MonthlyEvolution monthlyEvolution;

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    @Schema(description = "Ranking of reasons for farm condemnations")
    public static class ReasonRanking {
        @Schema(description = "Reason", example = "Contamination")
        private String reason;

        @Schema(description = "Number of occurrences", example = "5")
        private int quantity;
    }
}
