package com.cinemastore.privateservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PrivateServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrivateServiceApplication.class, args);
    }

}
