package com.gradox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

//@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
@EnableEurekaClient
@EnableOAuth2Client
public class CommentsMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommentsMsApplication.class, args);
	}
}
