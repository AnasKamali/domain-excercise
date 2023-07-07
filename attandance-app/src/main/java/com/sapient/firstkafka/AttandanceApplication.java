package com.sapient.firstkafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AttandanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AttandanceApplication.class, args);
	}

}
