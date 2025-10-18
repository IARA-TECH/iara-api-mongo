package com.exemplo.iara_apimongo.dto.dashboardsDTOs;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardTurnosDTO {
    private String titulo;
    private List<QuantidadePorTurnoDTO> quantidadePorTurno;
    private EvolucaoMensalTurnosDTO evolucaoMensal;

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    public static class QuantidadePorTurnoDTO {
        private String turno;
        private int quantidade;
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    public static class EvolucaoMensalTurnosDTO {
        private List<String> periodos;
        private List<Integer> matutino;
        private List<Integer> vespertino;
        private List<Integer> noturno;
    }
}
