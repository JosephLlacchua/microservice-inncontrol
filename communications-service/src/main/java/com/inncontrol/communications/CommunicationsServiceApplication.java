package com.inncontrol.communications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CommunicationsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommunicationsServiceApplication.class, args);
	}

}
