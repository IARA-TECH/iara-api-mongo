package com.exemplo.iara_apimongo.config;

import com.exemplo.iara_apimongo.config.EnvConfig;
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
            throw new IllegalStateException("Environment variable DB_URI not found!");
        }
        return MongoClients.create(uri);
    }

}
