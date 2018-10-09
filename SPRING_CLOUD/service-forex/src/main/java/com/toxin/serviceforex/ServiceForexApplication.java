package com.toxin.serviceforex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ServiceForexApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceForexApplication.class, args);
	}

}
