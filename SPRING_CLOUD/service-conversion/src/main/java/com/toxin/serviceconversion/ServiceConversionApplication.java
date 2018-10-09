package com.toxin.serviceconversion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.toxin.serviceconversion")
public class ServiceConversionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceConversionApplication.class, args);
	}

}
