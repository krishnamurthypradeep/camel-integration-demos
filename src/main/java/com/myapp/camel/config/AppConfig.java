package com.myapp.camel.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.apache.camel.model.dataformat.CsvDataFormat;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.JmsTransactionManager;

@Configuration
public class AppConfig {
	
	@Bean("csvDataFormatAddressUpdate")
    CsvDataFormat csvDataFormatAddressUpdate(){
        CsvDataFormat csvDataFormatAddressUpdate = new CsvDataFormat();
        csvDataFormatAddressUpdate.setDelimiter(",");
        csvDataFormatAddressUpdate.setSkipHeaderRecord("true");
        return csvDataFormatAddressUpdate;
    }

	@Bean
	ActiveMQConnectionFactory activeMQConnectionFactory() {
		return new ActiveMQConnectionFactory("tcp://localhost:61616");
	}
	
	@Bean("jmsTransactionManager")
	JmsTransactionManager jmsTransactionManager(ActiveMQConnectionFactory connectionFactory) {
		return new JmsTransactionManager(connectionFactory);
	}
	
//	@Bean
//     ServletRegistrationBean<CamelHttpTransportServlet> camelServlet() {
//        ServletRegistrationBean<CamelHttpTransportServlet> servlet = new ServletRegistrationBean<>(
//            new CamelHttpTransportServlet(), "/services/*");
//        servlet.setName("CamelServlet");
//        return servlet;
//    }
}
