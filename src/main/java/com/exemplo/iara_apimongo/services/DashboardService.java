package com.exemplo.iara_apimongo.services;

import com.exemplo.iara_apimongo.model.dto.response.dashboard.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.Fields;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final MongoTemplate mongoTemplate;
    private static final String COLLECTION = "abacusCollection";

    private String monthToString(int month) {
        return switch (month) {
            case 1 -> "Jan"; case 2 -> "Feb"; case 3 -> "Mar"; case 4 -> "Apr";
            case 5 -> "May"; case 6 -> "Jun"; case 7 -> "Jul"; case 8 -> "Aug";
            case 9 -> "Sep"; case 10 -> "Oct"; case 11 -> "Nov"; case 12 -> "Dec";
            default -> "Unknown";
        };
    }

    public ComparativeDashboard getComparative(int factoryId) {
        Aggregation agg = newAggregation(
                match(Criteria.where("abacus.factory_id").is(factoryId)),
                unwind("lines"),
                unwind("values"),
                project()
                        .and("lines.line.line_type.name").as("type")
                        .and("values").as("value")
                        .and("taken_at").as("taken_at"),
                project()
                        .andExpression("month(taken_at)").as("month")
                        .and("type").as("type")
                        .and("value").as("value"),
                group(Fields.from(
                        Fields.field("month", "month"),
                        Fields.field("type", "type")
                ))
                        .sum("value").as("totalCount"),
                project("totalCount").and("_id.month").as("month").and("_id.type").as("name"),
                sort(Sort.Direction.ASC, "month")
        );

        List<Map> results = mongoTemplate.aggregate(agg, COLLECTION, Map.class).getMappedResults();

        Map<String, List<Integer>> typeToValues = new HashMap<>();
        Set<String> periodsSet = new TreeSet<>();

        for (Map r : results) {
            Integer month = (Integer) r.get("month");
            String name = (String) r.get("name");
            Integer total = (Integer) r.get("totalCount");

            periodsSet.add(monthToString(month));
            typeToValues.computeIfAbsent(name, k -> new ArrayList<>(Collections.nCopies(12, 0)));
            typeToValues.get(name).set(month - 1, total);
        }

        List<String> periods = periodsSet.stream().toList();
        List<Integer> technicalFailures = typeToValues.getOrDefault("Condena de falhas técnicas", new ArrayList<>());
        List<Integer> farmCondemnations = typeToValues.getOrDefault("Condena de falhas de fazenda", new ArrayList<>());

        List<ComparativeDashboard.MonthlyRanking> monthlyRanking = new ArrayList<>();
        for (int i = 0; i < periods.size(); i++) {
            int total = (technicalFailures.size() > i ? technicalFailures.get(i) : 0) +
                    (farmCondemnations.size() > i ? farmCondemnations.get(i) : 0);
            monthlyRanking.add(new ComparativeDashboard.MonthlyRanking(periods.get(i), total));
        }

        ComparativeDashboard.DashboardTotals totals = new ComparativeDashboard.DashboardTotals(
                farmCondemnations.stream().mapToInt(Integer::intValue).sum(),
                technicalFailures.stream().mapToInt(Integer::intValue).sum()
        );

        return ComparativeDashboard.builder()
                .title("Comparative Dashboard")
                .periods(periods)
                .technicalFailures(technicalFailures)
                .farmCondemnations(farmCondemnations)
                .totals(totals)
                .monthlyRanking(monthlyRanking)
                .build();
    }

    public FailuresDashboard getFailures(int factoryId) {
        Aggregation agg = newAggregation(
                match(Criteria.where("abacus.factory_id").is(factoryId)),
                unwind("lines"),
                unwind("values"),
                match(Criteria.where("lines.line.line_type.name").is("Condena de falhas técnicas")),
                project()
                        .and("lines.line.line_type.name").as("reason")
                        .and("values").as("quantity")
                        .and("taken_at").as("taken_at"),
                group("reason").sum("quantity").as("quantity"),
                project("quantity").and("_id").as("reason")
        );

        List<FailuresDashboard.ReasonRanking> reasonRanking =
                mongoTemplate.aggregate(agg, COLLECTION, FailuresDashboard.ReasonRanking.class).getMappedResults();

        List<String> periods = reasonRanking.stream().map(FailuresDashboard.ReasonRanking::getReason).toList();
        List<Integer> values = reasonRanking.stream().map(FailuresDashboard.ReasonRanking::getQuantity).toList();

        FailuresDashboard.MonthlyEvolution monthlyEvolution = new FailuresDashboard.MonthlyEvolution(periods, values);
        int total = values.stream().mapToInt(Integer::intValue).sum();
        double averageRate = values.stream().mapToInt(Integer::intValue).average().orElse(0.0);

        return FailuresDashboard.builder()
                .title("Failures Dashboard")
                .total(total)
                .averageRate(averageRate)
                .previousComparison(10)
                .reasonRanking(reasonRanking)
                .monthlyEvolution(monthlyEvolution)
                .build();
    }

    public FarmDashboard getFarm(int factoryId) {
        Aggregation agg = newAggregation(
                match(Criteria.where("abacus.factory_id").is(factoryId)),
                unwind("lines"),
                unwind("values"),
                match(Criteria.where("lines.line.line_type.name").is("Condena de falhas de fazenda")),
                project()
                        .and("lines.line.line_type.name").as("reason")
                        .and("values").as("quantity")
                        .and("taken_at").as("taken_at"),
                group("reason").sum("quantity").as("quantity"),
                project("quantity").and("_id").as("reason")
        );

        List<FarmDashboard.ReasonRanking> reasonRanking =
                mongoTemplate.aggregate(agg, COLLECTION, FarmDashboard.ReasonRanking.class).getMappedResults();

        List<String> periods = reasonRanking.stream().map(FarmDashboard.ReasonRanking::getReason).toList();
        List<Integer> values = reasonRanking.stream().map(FarmDashboard.ReasonRanking::getQuantity).toList();

        FailuresDashboard.MonthlyEvolution monthlyEvolution = new FailuresDashboard.MonthlyEvolution(periods, values);
        int total = values.stream().mapToInt(Integer::intValue).sum();
        double averageRate = values.stream().mapToInt(Integer::intValue).average().orElse(0.0);

        return FarmDashboard.builder()
                .title("Farm Dashboard")
                .total(total)
                .averageRate(averageRate)
                .previousComparison(8)
                .reasonRanking(reasonRanking)
                .monthlyEvolution(monthlyEvolution)
                .build();
    }

    public ShiftDashboard getShifts(int factoryId) {
        Aggregation agg = newAggregation(
                match(Criteria.where("abacus.factory_id").is(factoryId)),
                unwind("values"),
                project()
                        .andExpression("sum(values)").as("values")
                        .and("shift.name").as("shift")
                        .and("taken_at").as("taken_at"),
                group("shift", "taken_at").sum("values").as("total")
        );

        List<ShiftDashboard.QuantityPerShiftDTO> quantityPerShift =
                mongoTemplate.aggregate(agg, COLLECTION, ShiftDashboard.QuantityPerShiftDTO.class).getMappedResults();

        List<String> periods = Arrays.asList("Jan", "Feb", "Mar");
        List<Integer> morning = Arrays.asList(5, 6, 4);
        List<Integer> afternoon = Arrays.asList(7, 5, 8);
        List<Integer> night = Arrays.asList(3, 4, 2);

        ShiftDashboard.MonthlyEvolutionDTO monthlyEvolution =
                new ShiftDashboard.MonthlyEvolutionDTO(periods, morning, afternoon, night);

        return ShiftDashboard.builder()
                .title("Shift Dashboard")
                .quantityPerShift(quantityPerShift)
                .monthlyEvolution(monthlyEvolution)
                .build();
    }

    public ShiftDashboard getTopShifts(int factoryId) {
        Aggregation agg = newAggregation(
                match(Criteria.where("abacus.factory_id").is(factoryId)),
                unwind("values"),
                project()
                        .andExpression("sum(values)").as("values")
                        .and("shift.name").as("shift"),
                group("shift").sum("values").as("totalCount"),
                sort(Sort.Direction.DESC, "totalCount"),
                limit(5)
        );

        List<ShiftDashboard.QuantityPerShiftDTO> topShifts =
                mongoTemplate.aggregate(agg, COLLECTION, ShiftDashboard.QuantityPerShiftDTO.class).getMappedResults();

        return ShiftDashboard.builder()
                .title("Top Shifts")
                .quantityPerShift(topShifts)
                .build();
    }

    public List<ComparativeDashboard.MonthlyRanking> getMonthlyCondemnationByFactory(int factoryId, int year) {
        Calendar start = Calendar.getInstance();
        start.set(year, Calendar.JANUARY, 1, 0, 0, 0);
        Calendar end = Calendar.getInstance();
        end.set(year, Calendar.DECEMBER, 31, 23, 59, 59);

        Aggregation agg = newAggregation(
                match(Criteria.where("abacus.factory_id").is(factoryId)
                        .andOperator(Criteria.where("taken_at").gte(start.getTime())
                                .lte(end.getTime()))),
                unwind("values"),
                project()
                        .andExpression("sum(values)").as("values")
                        .and("taken_at").as("taken_at")
                        .andExpression("month(taken_at)").as("month"),
                group("month").sum("values").as("totalCount"),
                sort(Sort.Direction.DESC, "totalCount")
        );

        return mongoTemplate.aggregate(agg, COLLECTION, ComparativeDashboard.MonthlyRanking.class).getMappedResults();
    }

    public List<FailuresDashboard.ReasonRanking> getTechnicalFailuresByLine(int factoryId) {
        Aggregation agg = newAggregation(
                match(Criteria.where("abacus.factory_id").is(factoryId)),
                unwind("lines"),
                unwind("values"),
                project()
                        .and("lines.line.line_type.name").as("reason")
                        .and("values").as("quantity"),
                group("reason").sum("quantity").as("quantity"),
                project("quantity").and("_id").as("reason")
        );

        return mongoTemplate.aggregate(agg, COLLECTION, FailuresDashboard.ReasonRanking.class).getMappedResults();
    }
}
