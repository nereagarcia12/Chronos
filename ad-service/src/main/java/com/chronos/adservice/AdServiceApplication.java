package com.chronos.adservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AdServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdServiceApplication.class, args);
	}

}
