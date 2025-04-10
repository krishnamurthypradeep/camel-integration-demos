package com.myapp.camel;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

import com.myapp.camel.bean.MessageSender;


// EnableAutoConfiguration
// SpringBootConfiguration
// ComponentScan

@SpringBootApplication

public class CamelIntegrationDemosApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamelIntegrationDemosApplication.class, args);
	}
	
	@Bean
	CommandLineRunner runner(MessageSender sender) {
		return args ->{
		sender.sendToDirect("Hi");
		
		};
	}

}
