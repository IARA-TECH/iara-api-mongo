package com.exemplo.iara_apimongo.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {

    @Bean
    public MongoClient mongoClient() {
        String host = EnvConfig.get("DB_HOST");
        String port = EnvConfig.get("DB_PORT");
        String dbName = EnvConfig.get("DB_NAME");
        String user = EnvConfig.get("DB_USER");
        String pass = EnvConfig.get("DB_PASSWORD");

        String connectionString;

        if (user != null && !user.isEmpty() && pass != null && !pass.isEmpty()) {
            connectionString = String.format(
                    "mongodb://%s:%s@%s:%s/%s",
                    user, pass, host, port, dbName
            );
        } else {
            connectionString = String.format(
                    "mongodb://%s:%s/%s",
                    host, port, dbName
            );
        }

        return MongoClients.create(connectionString);
    }
}

