package com.myapp.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class FirstRoute extends RouteBuilder {
	
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
		.to("activemq:queue:javadslproducts2");
		
		
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

// Message brokers

// ActiveMq
// RabbitMQ
// IBMMQ

// Kafka