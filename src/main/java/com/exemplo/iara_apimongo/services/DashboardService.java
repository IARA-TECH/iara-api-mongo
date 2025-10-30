package com.exemplo.iara_apimongo.services;

import com.exemplo.iara_apimongo.model.database.Shift;
import com.exemplo.iara_apimongo.model.dto.response.dashboard.*;
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

    public ComparativeDashboard getComparative() {
        List<String> periods = Arrays.asList("Jan", "Feb", "Mar");
        List<Integer> technicalFailures = Arrays.asList(5, 3, 7);
        List<Integer> farmCondemnations = Arrays.asList(10, 8, 12);

        ComparativeDashboard.DashboardTotals totals = new ComparativeDashboard.DashboardTotals(
                farmCondemnations.stream().mapToInt(Integer::intValue).sum(),
                technicalFailures.stream().mapToInt(Integer::intValue).sum()
        );

        List<ComparativeDashboard.MonthlyRanking> monthlyRanking = IntStream.range(0, periods.size())
                .mapToObj(i -> new ComparativeDashboard.MonthlyRanking(
                        periods.get(i),
                        technicalFailures.get(i) + farmCondemnations.get(i)
                ))
                .collect(Collectors.toList());

        return ComparativeDashboard.builder()
                .title("Monthly Comparison")
                .periods(periods)
                .technicalFailures(technicalFailures)
                .farmCondemnations(farmCondemnations)
                .totals(totals)
                .monthlyRanking(monthlyRanking)
                .build();
    }

    public ShiftDashboard getShifts() {
        List<ShiftDashboard.QuantityPerShiftDTO> quantityPerShift = getTotalQuantityPerShift();
        ShiftDashboard.MonthlyEvolutionDTO monthlyEvolution = getMonthlyEvolutionPerShift();

        return ShiftDashboard.builder()
                .title("Shift Dashboard")
                .quantityPerShift(quantityPerShift)
                .monthlyEvolution(monthlyEvolution)
                .build();
    }

    public FailuresDashboard getFailures() {
        GenericDashboardDetail generic = getDetailedDashboard();
        return generic.toFailuresDashboard();
    }

    public FarmDashboard getFarm() {
        GenericDashboardDetail generic = getDetailedDashboard();
        return generic.toFarmDashboard();
    }

    private List<ShiftDashboard.QuantityPerShiftDTO> getTotalQuantityPerShift() {
        return Arrays.asList(
                new ShiftDashboard.QuantityPerShiftDTO("Morning", 15),
                new ShiftDashboard.QuantityPerShiftDTO("Afternoon", 20),
                new ShiftDashboard.QuantityPerShiftDTO("Night", 10)
        );
    }

    private ShiftDashboard.MonthlyEvolutionDTO getMonthlyEvolutionPerShift() {
        List<String> periods = Arrays.asList("Jan", "Feb", "Mar");
        List<Integer> morning = Arrays.asList(5, 6, 4);
        List<Integer> afternoon = Arrays.asList(7, 5, 8);
        List<Integer> night = Arrays.asList(3, 4, 2);

        return new ShiftDashboard.MonthlyEvolutionDTO(periods, morning, afternoon, night);
    }

    private GenericDashboardDetail getDetailedDashboard() {
        List<FailuresDashboard.ReasonRanking> failureRanking = Arrays.asList(
                new FailuresDashboard.ReasonRanking("Reason A", 5),
                new FailuresDashboard.ReasonRanking("Reason B", 3)
        );

        List<FarmDashboard.ReasonRanking> farmRanking = Arrays.asList(
                new FarmDashboard.ReasonRanking("Reason A", 7),
                new FarmDashboard.ReasonRanking("Reason B", 4)
        );

        FailuresDashboard.MonthlyEvolution monthlyEvolution = new FailuresDashboard.MonthlyEvolution(
                Arrays.asList("Jan", "Feb", "Mar"),
                Arrays.asList(5, 3, 7)
        );

        return GenericDashboardDetail.builder()
                .title("Detailed Dashboard")
                .total(15)
                .averageRate(0.25)
                .previousComparison(10)
                .failureReasonRanking(failureRanking)
                .farmReasonRanking(farmRanking)
                .monthlyEvolution(monthlyEvolution)
                .build();
    }
}
