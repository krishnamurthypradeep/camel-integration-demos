package com.myapp.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class LoadBalancerEIP extends RouteBuilder {
	
	@Override
	public void configure() throws Exception {
	
//		from("direct:lb")
//		.loadBalance()
//		.roundRobin()
//		.to("activemq:queue:a")
//		.to("activemq:queue:b")
//		.to("activemq:queue:c");
		
		
		from("direct:lb")
		.setHeader("orderId", constant("order123"))
		.loadBalance()
		.sticky(header("orderId"))
		
		.to("activemq:queue:b")
		.to("activemq:queue:c")
		.to("activemq:queue:a");
	}

}
