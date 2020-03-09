package com.pfa.microserviceoffers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroserviceOffersApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceOffersApplication.class, args);
	}

}
