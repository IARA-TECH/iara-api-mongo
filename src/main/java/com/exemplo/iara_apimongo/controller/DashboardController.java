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
@Tag(name = "Dashboard", description = "Consultas aos dashboards")
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/comparativo")
    @Operation(summary = "Retorna o comparativo de falhas técnicas e condenas da granja",
            description = "Retorna períodos, falhas técnicas, condenas da granja, totais e ranking mensal")
    @ApiResponse(responseCode = "200", description = "Dashboard comparativo retornado com sucesso")
    public DashboardComparativoDTO getComparativo() {
        return dashboardService.getComparativo();
    }

    @GetMapping("/turnos")
    @Operation(summary = "Retorna dados por turnos",
            description = "Inclui quantidade por turno e evolução mensal de cada turno")
    @ApiResponse(responseCode = "200", description = "Dashboard de turnos retornado com sucesso")
    public DashboardTurnosDTO getTurnos() {
        return dashboardService.getTurnos();
    }

    @GetMapping("/falhas")
    @Operation(summary = "Retorna dashboard de falhas técnicas",
            description = "Inclui total de falhas, taxa média, comparativo anterior, ranking de motivos e evolução mensal")
    @ApiResponse(responseCode = "200", description = "Dashboard de falhas retornado com sucesso")
    public DashboardFalhasDTO getFalhas() {
        return dashboardService.getFalhas();
    }

    @GetMapping("/granja")
    @Operation(summary = "Retorna dashboard das condenas pela granja",
            description = "Inclui total, taxa média, comparativo anterior, ranking de motivos, quantidade por fábrica e evolução mensal")
    @ApiResponse(responseCode = "200", description = "Dashboard da granja retornado com sucesso")
    public DashboardGranjaDTO getGranja() {
        return dashboardService.getGranja();
    }
}
