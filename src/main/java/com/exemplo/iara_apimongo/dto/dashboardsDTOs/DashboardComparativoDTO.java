package com.exemplo.iara_apimongo.dto.dashboardsDTOs;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardComparativoDTO {
    private String titulo;
    private List<String> periodos;
    private List<Integer> falhasTecnicas;
    private List<Integer> condenasGranja;
    private TotaisDTO totais;
    private List<RankingMesDTO> rankingMeses;

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    public static class TotaisDTO {
        private int totalCondenasGranja;
        private int totalFalhasTecnicas;
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    public static class RankingMesDTO {
        private String mes;
        private int total;
    }
}
