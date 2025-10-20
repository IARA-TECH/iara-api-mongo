package com.exemplo.iara_apimongo.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {

    @Bean
    public MongoClient mongoClient() {
        String uri = EnvConfig.get("MONGO_URI");
        if (uri == null || uri.isEmpty()) {
            throw new IllegalStateException("Variável de ambiente DB_URI não encontrada!");
        }
        return MongoClients.create(uri);
    }

}
