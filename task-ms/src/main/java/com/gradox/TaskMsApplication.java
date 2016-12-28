package com.gradox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@EnableAutoConfiguration
@ComponentScan
@EnableEurekaClient
@EnableOAuth2Client
@EnableCircuitBreaker	// DIFF
public class TaskMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskMsApplication.class, args);
	}
}
