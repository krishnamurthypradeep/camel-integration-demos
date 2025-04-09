package com.myapp.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.myapp.camel.bean.RecipientsBean;
import com.myapp.camel.config.MyAggregator;

@Component
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
		
		
		from("timer:test?repeatCount=4")
		.setBody(constant("Order1"))
		.setHeader("orderId",constant("78999"))
		.to("activemq:queue:orders");
				
		
//		from("file:data/products?include=order-.*.xml&noop=true&autoCreate=false&directoryMustExist=true")
//		.bean("", null)
		
//		from("file:data/products?include=order-.*.xml&noop=true&autoCreate=false&directoryMustExist=true")
//		.process(e ->{
//		String message=	 e.getIn().getBody(String.class);
//		RecipientsBean bean = new RecipientsBean();
//		 String list[]= bean.process(message);
//		 e.getOut().setBody(list);
//		});
		
//		from("activemq:queue:activeOrders")
//		.unmarshall().pgp(new File("privatekey"),"")
//		.log("")
		
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
