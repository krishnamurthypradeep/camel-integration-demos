package com.myapp.camel.routes;

import java.util.List;

import org.apache.camel.Predicate;
import org.apache.camel.builder.RouteBuilder;
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
		from("file:data/products?fileName=order-1.csv&noop=true")
		// List<Map>
		.unmarshal().csv() // turns csv 
		.split(body()) // process each row
		.filter((Predicate) exchange -> {
    List<?> row = exchange.getIn().getBody(List.class);
    return !"id".equalsIgnoreCase(String.valueOf(row.get(0)));
		})
		// row is mapped to order object
		.process(e ->{
		List<String> row =	 e.getIn().getBody(List.class);
		Order order = new Order();
		order.setId(Integer.valueOf(row.get(0)));
		
		order.setName(row.get(1));
		order.setPriority(row.get(2));
		e.getIn().setBody(order);
			// triggering routing slip
		}).marshal().json()
		
		.bean("routingSlip");
		
		
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