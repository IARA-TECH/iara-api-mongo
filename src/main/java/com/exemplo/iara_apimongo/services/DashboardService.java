package com.exemplo.iara_apimongo.services;

import com.exemplo.iara_apimongo.dto.dashboardsDTOs.*;
import com.exemplo.iara_apimongo.model.Shift;
import com.exemplo.iara_apimongo.repository.ShiftRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final MongoTemplate mongoTemplate;
    private final ShiftRepository shiftRepository;

    private Map<String, String> shiftIdToNameMap = new HashMap<>();

    @PostConstruct
    public void init() {
        this.shiftIdToNameMap = shiftRepository.findAll().stream()
                .filter(shift -> shift.getId() != null && shift.getName() != null)
                .collect(Collectors.toMap(Shift::getId, Shift::getName, (a, b) -> a));
    }

    public DashboardComparativoDTO getComparativo() {
        List<String> periodos = Arrays.asList("Jan", "Feb", "Mar");
        List<Integer> falhasTecnicas = Arrays.asList(5, 3, 7);
        List<Integer> condenasGranja = Arrays.asList(10, 8, 12);

        DashboardComparativoDTO.TotaisDTO totais = new DashboardComparativoDTO.TotaisDTO(
                condenasGranja.stream().mapToInt(Integer::intValue).sum(),
                falhasTecnicas.stream().mapToInt(Integer::intValue).sum()
        );

        List<DashboardComparativoDTO.RankingMesDTO> rankingMeses = IntStream.range(0, periodos.size())
                .mapToObj(i -> new DashboardComparativoDTO.RankingMesDTO(periodos.get(i), falhasTecnicas.get(i) + condenasGranja.get(i)))
                .collect(Collectors.toList());

        return DashboardComparativoDTO.builder()
                .titulo("Comparativo Mensal")
                .periodos(periodos)
                .falhasTecnicas(falhasTecnicas)
                .condenasGranja(condenasGranja)
                .totais(totais)
                .rankingMeses(rankingMeses)
                .build();
    }

    public DashboardTurnosDTO getTurnos() {
        List<DashboardTurnosDTO.QuantidadePorTurnoDTO> quantidadePorTurno = getQuantidadeTotalPorTurno();
        DashboardTurnosDTO.EvolucaoMensalTurnosDTO evolucaoMensal = getEvolucaoMensalPorTurno();

        return DashboardTurnosDTO.builder()
                .titulo("Dashboard de Turnos")
                .quantidadePorTurno(quantidadePorTurno)
                .evolucaoMensal(evolucaoMensal)
                .build();
    }

    public DashboardFalhasDTO getFalhas() {
        GenericDashboardDetailDTO generic = getDashboardDetalhado();
        return generic.toDashboardFalhasDTO();
    }

    public DashboardGranjaDTO getGranja() {
        GenericDashboardDetailDTO generic = getDashboardDetalhado();
        return generic.toDashboardGranjaDTO();
    }

    private List<DashboardTurnosDTO.QuantidadePorTurnoDTO> getQuantidadeTotalPorTurno() {
        return Arrays.asList(
                new DashboardTurnosDTO.QuantidadePorTurnoDTO("Matutino", 15),
                new DashboardTurnosDTO.QuantidadePorTurnoDTO("Vespertino", 20),
                new DashboardTurnosDTO.QuantidadePorTurnoDTO("Noturno", 10)
        );
    }

    private DashboardTurnosDTO.EvolucaoMensalTurnosDTO getEvolucaoMensalPorTurno() {
        List<String> periodos = Arrays.asList("Jan", "Feb", "Mar");
        List<Integer> matutino = Arrays.asList(5, 6, 4);
        List<Integer> vespertino = Arrays.asList(7, 5, 8);
        List<Integer> noturno = Arrays.asList(3, 4, 2);

        return new DashboardTurnosDTO.EvolucaoMensalTurnosDTO(periodos, matutino, vespertino, noturno);
    }

    private GenericDashboardDetailDTO getDashboardDetalhado() {
        // Ranking para falhas
        List<DashboardFalhasDTO.RankingMotivoDTO> rankingFalhas = Arrays.asList(
                new DashboardFalhasDTO.RankingMotivoDTO("Motivo A", 5),
                new DashboardFalhasDTO.RankingMotivoDTO("Motivo B", 3)
        );

        // Ranking para granja
        List<DashboardGranjaDTO.RankingMotivoDTO> rankingGranjas = Arrays.asList(
                new DashboardGranjaDTO.RankingMotivoDTO("Motivo A", 7),
                new DashboardGranjaDTO.RankingMotivoDTO("Motivo B", 4)
        );

        // Por fábrica (somente granja)
        List<DashboardGranjaDTO.PorFabricaDTO> porFabrica = Arrays.asList(
                new DashboardGranjaDTO.PorFabricaDTO("Fábrica X", 7),
                new DashboardGranjaDTO.PorFabricaDTO("Fábrica Y", 8)
        );

        // Evolução mensal
        DashboardFalhasDTO.EvolucaoMensalDTO evolucaoMensal = new DashboardFalhasDTO.EvolucaoMensalDTO(
                Arrays.asList("Jan", "Feb", "Mar"),
                Arrays.asList(5, 3, 7)
        );

        return GenericDashboardDetailDTO.builder()
                .titulo("Dashboard Detalhado")
                .total(15)
                .taxaMedia(0.25)
                .comparativoAnterior(10)
                .rankingMotivosFalhas(rankingFalhas)
                .rankingMotivosGranjas(rankingGranjas)
                .porFabrica(porFabrica)
                .evolucaoMensal(evolucaoMensal)
                .build();
    }
}
