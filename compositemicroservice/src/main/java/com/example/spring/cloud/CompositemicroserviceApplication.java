package com.example.spring.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CompositemicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompositemicroserviceApplication.class, args);
    }
}
