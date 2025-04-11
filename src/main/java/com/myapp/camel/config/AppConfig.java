package com.myapp.camel.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.JmsTransactionManager;

@Configuration
public class AppConfig {

	@Bean
	ActiveMQConnectionFactory activeMQConnectionFactory() {
		return new ActiveMQConnectionFactory("tcp://localhost:61616");
	}
	
	@Bean("jmsTransactionManager")
	JmsTransactionManager jmsTransactionManager(ActiveMQConnectionFactory connectionFactory) {
		return new JmsTransactionManager(connectionFactory);
	}
}
