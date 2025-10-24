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
        List<String> periods = Arrays.asList("Jan", "Feb", "Mar");
        List<Integer> technicalFailures = Arrays.asList(5, 3, 7);
        List<Integer> farmCondemnations = Arrays.asList(10, 8, 12);

        DashboardComparativoDTO.TotalsDTO totals = new DashboardComparativoDTO.TotalsDTO(
                farmCondemnations.stream().mapToInt(Integer::intValue).sum(),
                technicalFailures.stream().mapToInt(Integer::intValue).sum()
        );

        List<DashboardComparativoDTO.MonthlyRankingDTO> monthlyRanking = IntStream.range(0, periods.size())
                .mapToObj(i -> new DashboardComparativoDTO.MonthlyRankingDTO(
                        periods.get(i),
                        technicalFailures.get(i) + farmCondemnations.get(i)
                ))
                .collect(Collectors.toList());

        return DashboardComparativoDTO.builder()
                .title("Monthly Comparison")
                .periods(periods)
                .technicalFailures(technicalFailures)
                .farmCondemnations(farmCondemnations)
                .totals(totals)
                .monthlyRanking(monthlyRanking)
                .build();
    }

    public DashboardTurnosDTO getTurnos() {
        List<DashboardTurnosDTO.QuantityPerShiftDTO> quantityPerShift = getTotalQuantityPerShift();
        DashboardTurnosDTO.MonthlyEvolutionDTO monthlyEvolution = getMonthlyEvolutionPerShift();

        return DashboardTurnosDTO.builder()
                .title("Shift Dashboard")
                .quantityPerShift(quantityPerShift)
                .monthlyEvolution(monthlyEvolution)
                .build();
    }

    public DashboardFalhasDTO getFalhas() {
        GenericDashboardDetailDTO generic = getDetailedDashboard();
        return generic.toDashboardFalhasDTO();
    }

    public DashboardGranjaDTO getGranja() {
        GenericDashboardDetailDTO generic = getDetailedDashboard();
        return generic.toDashboardGranjaDTO();
    }

    private List<DashboardTurnosDTO.QuantityPerShiftDTO> getTotalQuantityPerShift() {
        return Arrays.asList(
                new DashboardTurnosDTO.QuantityPerShiftDTO("Morning", 15),
                new DashboardTurnosDTO.QuantityPerShiftDTO("Afternoon", 20),
                new DashboardTurnosDTO.QuantityPerShiftDTO("Night", 10)
        );
    }

    private DashboardTurnosDTO.MonthlyEvolutionDTO getMonthlyEvolutionPerShift() {
        List<String> periods = Arrays.asList("Jan", "Feb", "Mar");
        List<Integer> morning = Arrays.asList(5, 6, 4);
        List<Integer> afternoon = Arrays.asList(7, 5, 8);
        List<Integer> night = Arrays.asList(3, 4, 2);

        return new DashboardTurnosDTO.MonthlyEvolutionDTO(periods, morning, afternoon, night);
    }

    private GenericDashboardDetailDTO getDetailedDashboard() {
        List<DashboardFalhasDTO.ReasonRankingDTO> failureRanking = Arrays.asList(
                new DashboardFalhasDTO.ReasonRankingDTO("Reason A", 5),
                new DashboardFalhasDTO.ReasonRankingDTO("Reason B", 3)
        );

        List<DashboardGranjaDTO.ReasonRankingDTO> farmRanking = Arrays.asList(
                new DashboardGranjaDTO.ReasonRankingDTO("Reason A", 7),
                new DashboardGranjaDTO.ReasonRankingDTO("Reason B", 4)
        );

        List<DashboardGranjaDTO.ByFactoryDTO> byFactory = Arrays.asList(
                new DashboardGranjaDTO.ByFactoryDTO("Factory X", 7),
                new DashboardGranjaDTO.ByFactoryDTO("Factory Y", 8)
        );

        DashboardFalhasDTO.MonthlyEvolutionDTO monthlyEvolution = new DashboardFalhasDTO.MonthlyEvolutionDTO(
                Arrays.asList("Jan", "Feb", "Mar"),
                Arrays.asList(5, 3, 7)
        );

        return GenericDashboardDetailDTO.builder()
                .title("Detailed Dashboard")
                .total(15)
                .averageRate(0.25)
                .previousComparison(10)
                .failureReasonRanking(failureRanking)
                .farmReasonRanking(farmRanking)
                .byFactory(byFactory)
                .monthlyEvolution(monthlyEvolution)
                .build();
    }
}
