package com.exemplo.iara_apimongo.dto.dashboardsDTOs;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardFalhasDTO {

    @Schema(description = "Dashboard title", example = "Technical Failures")
    private String title;

    @Schema(description = "Total number of failures", example = "25")
    private int total;

    @Schema(description = "Average failure rate", example = "12.5")
    private double averageRate;

    @Schema(description = "Comparison with previous period", example = "20")
    private int previousComparison;

    @Schema(description = "Ranking of failure reasons")
    private List<ReasonRankingDTO> reasonRanking;

    @Schema(description = "Monthly evolution of failures")
    private MonthlyEvolutionDTO monthlyEvolution;

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    @Schema(description = "Ranking of reasons for failures")
    public static class ReasonRankingDTO {
        @Schema(description = "Reason for the failure", example = "Mechanical")
        private String reason;

        @Schema(description = "Number of occurrences", example = "10")
        private int quantity;
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    @Schema(description = "Monthly evolution data")
    public static class MonthlyEvolutionDTO {
        @Schema(description = "List of periods", example = "[\"Jan\", \"Feb\"]")
        private List<String> periods;

        @Schema(description = "Number of failures per period", example = "[10, 15]")
        private List<Integer> values;
    }
}
