package com.exemplo.iara_apimongo.model.dto.response.dashboard;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenericDashboardDetail {

    @Schema(description = "Dashboard title", example = "Generic Dashboard")
    private String title;

    @Schema(description = "Total value", example = "50")
    private int total;

    @Schema(description = "Average rate", example = "25.0")
    private double averageRate;

    @Schema(description = "Comparison with previous period", example = "45")
    private int previousComparison;

    @Schema(description = "Ranking of failure reasons")
    private List<FailuresDashboard.ReasonRankingDTO> failureReasonRanking;

    @Schema(description = "Ranking of farm reasons")
    private List<FarmDashboard.ReasonRankingDTO> farmReasonRanking;

    @Schema(description = "Data by factory")
    private List<FarmDashboard.ByFactoryDTO> byFactory;

    @Schema(description = "Monthly evolution")
    private FailuresDashboard.MonthlyEvolutionDTO monthlyEvolution;

    public FailuresDashboard toDashboardFalhasDTO() {
        return FailuresDashboard.builder()
                .title(title)
                .total(total)
                .averageRate(averageRate)
                .previousComparison(previousComparison)
                .reasonRanking(failureReasonRanking)
                .monthlyEvolution(monthlyEvolution)
                .build();
    }

    public FarmDashboard toDashboardGranjaDTO() {
        return FarmDashboard.builder()
                .title(title)
                .total(total)
                .averageRate(averageRate)
                .previousComparison(previousComparison)
                .reasonRanking(farmReasonRanking)
                .byFactory(byFactory)
                .monthlyEvolution(monthlyEvolution)
                .build();
    }
}
