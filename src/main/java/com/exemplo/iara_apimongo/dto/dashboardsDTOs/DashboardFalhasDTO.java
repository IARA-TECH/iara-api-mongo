package com.exemplo.iara_apimongo.dto.dashboardsDTOs;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardFalhasDTO {
    private String titulo;
    private int total;
    private double taxaMedia;
    private int comparativoAnterior;
    private List<RankingMotivoDTO> rankingMotivos;
    private EvolucaoMensalDTO evolucaoMensal;

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    public static class RankingMotivoDTO {
        private String motivo;
        private int quantidade;
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    public static class EvolucaoMensalDTO {
        private List<String> periodos;
        private List<Integer> valores;
    }
}

