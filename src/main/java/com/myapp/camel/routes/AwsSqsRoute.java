package com.myapp.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component

public class AwsSqsRoute extends RouteBuilder {
	
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
		
		from("file:data/input?noop=true")
		.setHeader("fileType",simple("${file:ext}"))
		.to("aws2-sqs:products");
		
		
	//	from("file:","ftp:").filter().to("");
		
		//from("file").toD()
		
		//from("ftp").to()
		
		// k_pradeep@live.com
		
		
		
		from("aws2-sqs:products")
		.choice()
		.when(header("fileType").isEqualTo("xml"))
		.to("aws2-sqs:xmlproducts")
		.when(header("fileType").isEqualTo("json"))
		.to("aws2-sqs:jsonproducts")
		.otherwise()
		.to("aws2-sqs:defaultproducts");
		
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