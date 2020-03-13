package com.pfa.microserviceoffers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class MicroserviceOffersApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceOffersApplication.class, args);
	}
	@RequestMapping("/hello")
	public String hello()
	{
		return "hello";
	}
}
