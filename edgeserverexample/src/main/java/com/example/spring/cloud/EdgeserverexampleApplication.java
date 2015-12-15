package com.example.spring.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.stereotype.Controller;

@SpringBootApplication
@Controller
@EnableZuulProxy

public class EdgeserverexampleApplication {

    public static void main(String[] args) {
    	 new SpringApplicationBuilder(EdgeserverexampleApplication.class).web(true).run(args);
    }
}
