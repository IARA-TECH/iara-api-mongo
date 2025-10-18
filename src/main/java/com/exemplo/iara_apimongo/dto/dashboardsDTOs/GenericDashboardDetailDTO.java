package com.exemplo.iara_apimongo.dto.dashboardsDTOs;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenericDashboardDetailDTO {

    private String titulo;
    private int total;
    private double taxaMedia;
    private int comparativoAnterior;

    private List<DashboardFalhasDTO.RankingMotivoDTO> rankingMotivosFalhas;
    private List<DashboardGranjaDTO.RankingMotivoDTO> rankingMotivosGranjas;

    private List<DashboardGranjaDTO.PorFabricaDTO> porFabrica;

    private DashboardFalhasDTO.EvolucaoMensalDTO evolucaoMensal;

    public DashboardFalhasDTO toDashboardFalhasDTO() {
        return DashboardFalhasDTO.builder()
                .titulo(titulo)
                .total(total)
                .taxaMedia(taxaMedia)
                .comparativoAnterior(comparativoAnterior)
                .rankingMotivos(rankingMotivosFalhas)
                .evolucaoMensal(evolucaoMensal)
                .build();
    }

    public DashboardGranjaDTO toDashboardGranjaDTO() {
        return DashboardGranjaDTO.builder()
                .titulo(titulo)
                .total(total)
                .taxaMedia(taxaMedia)
                .comparativoAnterior(comparativoAnterior)
                .rankingMotivos(rankingMotivosGranjas)
                .porFabrica(porFabrica)
                .evolucaoMensal(evolucaoMensal)
                .build();
    }
}
