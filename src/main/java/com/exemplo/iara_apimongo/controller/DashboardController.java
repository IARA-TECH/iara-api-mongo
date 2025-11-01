package com.exemplo.iara_apimongo.controller;

import com.exemplo.iara_apimongo.model.dto.response.dashboard.ComparativeDashboard;
import com.exemplo.iara_apimongo.model.dto.response.dashboard.FailuresDashboard;
import com.exemplo.iara_apimongo.model.dto.response.dashboard.FarmDashboard;
import com.exemplo.iara_apimongo.model.dto.response.dashboard.ShiftDashboard;
import com.exemplo.iara_apimongo.services.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
@Tag(name = "Dashboard", description = "Dashboard queries")
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/comparatives")
    @Operation(summary = "Returns the comparison of technical failures and farm condemnations",
            description = "Returns periods, technical failures, farm condemnations, totals, and monthly ranking")
    @ApiResponse(responseCode = "200", description = "Comparative dashboard returned successfully")
    public ComparativeDashboard getComparative(
            @RequestParam int factoryId
    ) {
        return dashboardService.getComparative(factoryId);
    }

    @GetMapping("/failures")
    @Operation(summary = "Returns technical failures dashboard",
            description = "Includes total failures, average rate, previous comparison, reasons ranking, and monthly evolution")
    @ApiResponse(responseCode = "200", description = "Technical failures dashboard returned successfully")
    public FailuresDashboard getFailures(
            @RequestParam int factoryId
    ) {
        return dashboardService.getFailures(factoryId);
    }

    @GetMapping("/farms")
    @Operation(summary = "Returns the farm condemnations dashboard",
            description = "Includes total, average rate, previous comparison, reasons ranking, and monthly evolution")
    @ApiResponse(responseCode = "200", description = "Farm dashboard returned successfully")
    public FarmDashboard getFarm(
            @RequestParam int factoryId
    ) {
        return dashboardService.getFarm(factoryId);
    }

    @GetMapping("/shifts")
    @Operation(summary = "Returns shift dashboard",
            description = "Includes quantity per shift and monthly evolution of each shift")
    @ApiResponse(responseCode = "200", description = "Shift dashboard returned successfully")
    public ShiftDashboard getShifts(
            @RequestParam int factoryId
    ) {
        return dashboardService.getShifts(factoryId);
    }

    @GetMapping("/top-shifts")
    @Operation(summary = "Returns top 5 shifts with the highest quantities",
            description = "Shows the shifts ranked by total quantities")
    @ApiResponse(responseCode = "200", description = "Top shifts returned successfully")
    public ShiftDashboard getTopShifts(
            @RequestParam int factoryId
    ) {
        return dashboardService.getTopShifts(factoryId);
    }

    @GetMapping("/monthly-condemnation")
    @Operation(summary = "Returns monthly farm condemnations by factory",
            description = "Aggregates monthly total condemnations for a given year")
    @ApiResponse(responseCode = "200", description = "Monthly condemnation data returned successfully")
    public List<ComparativeDashboard.MonthlyRanking> getMonthlyCondemnationByFactory(
            @RequestParam int factoryId,
            @RequestParam int year
    ) {
        return dashboardService.getMonthlyCondemnationByFactory(factoryId, year);
    }

    @GetMapping("/technical-failures-by-line")
    @Operation(summary = "Returns technical failures aggregated by line",
            description = "Shows the ranking of technical failures for each production line")
    @ApiResponse(responseCode = "200", description = "Technical failures by line returned successfully")
    public List<FailuresDashboard.ReasonRanking> getTechnicalFailuresByLine(
            @RequestParam int factoryId
    ) {
        return dashboardService.getTechnicalFailuresByLine(factoryId);
    }

}
