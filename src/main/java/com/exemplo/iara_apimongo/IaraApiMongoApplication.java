package com.exemplo.iara_apimongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class IaraApiMongoApplication {

    public static void main(String[] args) {
        SpringApplication.run(IaraApiMongoApplication.class, args);
    }

}
