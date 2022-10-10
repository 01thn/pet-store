package com.cinemastore.privateservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
@EnableKafka
@EnableDiscoveryClient
@EnableScheduling
public class PrivateServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrivateServiceApplication.class, args);
    }

}
