package com.exemplo.iara_apimongo.dto.dashboardsDTOs;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardComparativoDTO {

    @Schema(description = "Dashboard title", example = "Farm Comparison")
    private String title;

    @Schema(description = "List of periods included in the comparison", example = "[\"Jan\",\"Feb\",\"Mar\"]")
    private List<String> periods;

    @Schema(description = "Number of technical failures per period", example = "[10, 15, 8]")
    private List<Integer> technicalFailures;

    @Schema(description = "Number of farm condemnations per period", example = "[5, 7, 3]")
    private List<Integer> farmCondemnations;

    @Schema(description = "Totals for technical failures and farm condemnations")
    private TotalsDTO totals;

    @Schema(description = "Monthly ranking data")
    private List<MonthlyRankingDTO> monthlyRanking;

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    @Schema(description = "Total counts for dashboard")
    public static class TotalsDTO {
        @Schema(description = "Total farm condemnations", example = "15")
        private int totalFarmCondemnations;

        @Schema(description = "Total technical failures", example = "33")
        private int totalTechnicalFailures;
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    @Schema(description = "Monthly ranking information")
    public static class MonthlyRankingDTO {
        @Schema(description = "Month", example = "Jan")
        private String month;

        @Schema(description = "Total occurrences for the month", example = "10")
        private int total;
    }
}
