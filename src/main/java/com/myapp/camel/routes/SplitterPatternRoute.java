package com.myapp.camel.routes;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//@Component

public class SplitterPatternRoute extends RouteBuilder {
	
	@Override
	public void configure() throws Exception {
		
		// from
		// to
		
		
		// from().
		// setHeader()
		//to()
		
		// Java DSL
		
		// FileProducer will write the message body to a file
		// ActiveMQProducer will map the camel message to jakarta.jms.Message
		
		// Iterator
		// Collection
		// NodeList
		
		// Consume Json Array, split this into individual order objects
		//ExecutorService executorService = Executors.newFixedThreadPool(20);
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		from("file:data/products?include=order-.*.json&noop=true&autoCreate=false&directoryMustExist=true")
		// List<Map>
		.unmarshal().json(JsonLibrary.Jackson,List.class)
		.split(body().tokenize("\n"))
		.streaming()
		
		.executorService(executorService)
		.marshal().json(JsonLibrary.Jackson) // Remarshal each part to JSOn String
		
		.to("activemq:queue:splitorders");
		
		
	//	from("file:","ftp:").filter().to("");
		
		//from("file").toD()
		
		//from("ftp").to()
		
		
		
//		from("activemq:queue:newactiveproducts")
//		.choice()
//		.when(header("fileType").isEqualTo("xml"))
//		.to("activemq:queue:xmlproducts")
//		.when(header("fileType").isEqualTo("json"))
//		.to("activemq:queue:jsonproducts")
//		.otherwise()
//		.to("activemq:queue:defaultproducts");
		
		// EIP CBR (Content Based Router)
		
	}

}

// Supply Chain Management
// Auto Manufacturing
// CSV
// XML

// WebInterface
// 


// Message brokers

// ActiveMq
// RabbitMQ
// IBMMQ

// Kafka