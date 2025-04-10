package com.myapp.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.myapp.camel.bean.RecipientsBean;
import com.myapp.camel.config.MyAggregator;

//@Component
public class AggregatorPattern  extends RouteBuilder{
	
	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
	
		// apache camel is going to threadpool 
		
		// service activator pattern (EIP)
		
		
		// ApplicationContext is container maintained by spring
		
		// Step1: requestor looksup registry
		// Step2: Registry getBean from ApplicationContext
		from("activemq:queue:orders")
		// correlation identifier is header("orderId")
		.aggregate(header("orderId"),new MyAggregator())
		.completionSize(3)
		.completionTimeout(5000)
		.to("activemq:queue:combinedmessages");
		
		
		
	}

}

// ApplicationContextRegistry  with spring
// OsgiServiceRegistry 
// CdiBeanregistry  with Micronaut | Quarkus
// SimpleRegistry

// Simple
// Xpath
// Json
// Csv


// Recipient List Pattern
