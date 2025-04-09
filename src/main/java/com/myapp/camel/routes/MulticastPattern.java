package com.myapp.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MulticastPattern  extends RouteBuilder{
	
	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
	
		from("activemq:queue:activeOrders")
		
		.multicast()
		.to("activemq:queue:accounts","activemq:queue:production");
		
	}

}
