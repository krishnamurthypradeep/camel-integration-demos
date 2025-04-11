package com.myapp.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.springframework.stereotype.Component;

import com.myapp.camel.dto.Order;

//@Component

public class RoutingSlipPatternRoute extends RouteBuilder {
	
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
	
	        BindyCsvDataFormat bindy = new BindyCsvDataFormat(Order.class);
	        JacksonDataFormat json = new JacksonDataFormat(Order.class);
	        from("file:data/products?include=order-.*.csv&noop=true&autoCreate=false&directoryMustExist=true") // Reads CSV from input directory
	            .routeId("csv-to-amq-routingSlip")
	            .unmarshal(bindy)
	            .split(body()) // Split each CSV row
	            .process(exchange -> {
	                Order order = exchange.getIn().getBody(Order.class);
	                String destination;
	                switch (order.getPriority().toUpperCase()) {
	                    case "HIGH":
	                        destination = "activemq:queue:highPriorityOrders";
	                        break;
	                    case "MEDIUM":
	                        destination = "activemq:queue:mediumPriorityOrders";
	                        break;
	                    case "LOW":
	                    default:
	                        destination = "activemq:queue:lowPriorityOrders";
	                        break;
	                }
	                exchange.getIn().setHeader("mySlip", destination);
	            })
	            .marshal(json)
	            .routingSlip(header("mySlip")); 
		
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

// RoutingSlip vs  DynamicRouter
// has to identify
// endpoints



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