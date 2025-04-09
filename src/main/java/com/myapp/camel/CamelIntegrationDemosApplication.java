package com.myapp.camel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;


// EnableAutoConfiguration
// SpringBootConfiguration
// ComponentScan

@SpringBootApplication

public class CamelIntegrationDemosApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamelIntegrationDemosApplication.class, args);
	}

}
