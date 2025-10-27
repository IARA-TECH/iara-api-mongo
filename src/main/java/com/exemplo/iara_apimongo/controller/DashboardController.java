package com.exemplo.iara_apimongo.controller;

import com.exemplo.iara_apimongo.dto.dashboardsDTOs.*;
import com.exemplo.iara_apimongo.services.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
@Tag(name = "Dashboard", description = "Dashboard queries")
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/comparativo")
    @Operation(summary = "Returns the comparison of technical failures and condemnations of the farm",
            description = "Returns periods, technical failures, farm condemnations, totals, and monthly ranking")
    @ApiResponse(responseCode = "200", description = "Comparative dashboard returned successfully")
    public DashboardComparativoDTO getComparativo() {
        return dashboardService.getComparativo();
    }

    @GetMapping("/turnos")
    @Operation(summary = "Returns shift data",
            description = "Includes quantity per shift and monthly evolution of each shift")
    @ApiResponse(responseCode = "200", description = "Shift dashboard returned successfully")
    public DashboardTurnosDTO getTurnos() {
        return dashboardService.getTurnos();
    }

    @GetMapping("/falhas")
    @Operation(summary = "Returns technical failures dashboard",
            description = "Includes total failures, average rate, previous comparison, reasons ranking, and monthly evolution")
    @ApiResponse(responseCode = "200", description = "Technical failures dashboard returned successfully")
    public DashboardFalhasDTO getFalhas() {
        return dashboardService.getFalhas();
    }

    @GetMapping("/granja")
    @Operation(summary = "Returns the farm condemnations dashboard",
            description = "Includes total, average rate, previous comparison, reasons ranking, and monthly evolution")
    @ApiResponse(responseCode = "200", description = "Farm dashboard returned successfully")
    public DashboardGranjaDTO getGranja() {
        return dashboardService.getGranja();
    }
}
