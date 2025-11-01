//package com.exemplo.iara_apimongo.util;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.bson.Document;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.aggregation.Aggregation;
//import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
//import org.springframework.data.mongodb.core.aggregation.AggregationResults;
//import org.springframework.stereotype.Component;
//
//import java.io.InputStream;
//import java.util.List;
//
//@Component
//public class MongoAggregationUtils {
//
//    private final MongoTemplate mongoTemplate;
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    public MongoAggregationUtils(MongoTemplate mongoTemplate) {
//        this.mongoTemplate = mongoTemplate;
//    }
//
//    public <T> List<T> runAggregation(String fileName, String collection, Class<T> resultType) {
//        try {
//            InputStream input = new ClassPathResource("aggregations/" + fileName).getInputStream();
//            List<Document> pipelineDocs = objectMapper.readValue(input, new TypeReference<>() {});
//            Aggregation aggregation = Aggregation.newAggregation((AggregationOperation) pipelineDocs.stream()
//                    .map(AggregationOperation -> context -> AggregationOperation)
//                    .toList());
//            AggregationResults<T> results = mongoTemplate.aggregate(aggregation, collection, resultType);
//            return results.getMappedResults();
//        } catch (Exception e) {
//            throw new RuntimeException("Erro ao executar agregação: " + fileName, e);
//        }
//    }
//}
