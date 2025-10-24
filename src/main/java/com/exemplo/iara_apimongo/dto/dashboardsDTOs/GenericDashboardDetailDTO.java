package com.exemplo.iara_apimongo.dto.dashboardsDTOs;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenericDashboardDetailDTO {

    @Schema(description = "Dashboard title", example = "Generic Dashboard")
    private String title;

    @Schema(description = "Total value", example = "50")
    private int total;

    @Schema(description = "Average rate", example = "25.0")
    private double averageRate;

    @Schema(description = "Comparison with previous period", example = "45")
    private int previousComparison;

    @Schema(description = "Ranking of failure reasons")
    private List<DashboardFalhasDTO.ReasonRankingDTO> failureReasonRanking;

    @Schema(description = "Ranking of farm reasons")
    private List<DashboardGranjaDTO.ReasonRankingDTO> farmReasonRanking;

    @Schema(description = "Data by factory")
    private List<DashboardGranjaDTO.ByFactoryDTO> byFactory;

    @Schema(description = "Monthly evolution")
    private DashboardFalhasDTO.MonthlyEvolutionDTO monthlyEvolution;

    public DashboardFalhasDTO toDashboardFalhasDTO() {
        return DashboardFalhasDTO.builder()
                .title(title)
                .total(total)
                .averageRate(averageRate)
                .previousComparison(previousComparison)
                .reasonRanking(failureReasonRanking)
                .monthlyEvolution(monthlyEvolution)
                .build();
    }

    public DashboardGranjaDTO toDashboardGranjaDTO() {
        return DashboardGranjaDTO.builder()
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
