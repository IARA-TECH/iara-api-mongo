package com.exemplo.iara_apimongo.util.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class KeepTaskAlive {

    private final RestTemplate restTemplate = new RestTemplate();

    @Scheduled(fixedRate = 600000)
    public void ping() {
        try {
            restTemplate.getForObject("https://iara-api-mongo.onrender.com/health", String.class);
        } catch (Exception e) {
            System.out.println("Failure on ping API: " + e.getMessage());
        }
    }
}

