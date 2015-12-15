package com.example.spring.cloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

	@Value("${client-greeting}") String clientGreeting;
	
	@RequestMapping("/msg")
	public String greetingMessage() {
		return clientGreeting;
	}
	
	
	@RequestMapping("/wel")
	public String welcomeMessage() {
		return "Hello Welcome to Sprin cloud example deployed on cloud foundry";
	}
	
}
