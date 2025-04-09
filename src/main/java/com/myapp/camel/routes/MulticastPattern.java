package com.myapp.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MulticastPattern  extends RouteBuilder{
	
	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
	
		// apache camel is going to threadpool 
		
		from("activemq:queue:activeOrders")
		
		.multicast()
		.parallelProcessing()
		.stopOnException()
		.to("activemq:queue:accounts","activemq:queue:production");
		
//		from("activemq:queue:activeOrders")
//		.unmarshall().pgp(new File("privatekey"),"")
//		.log("")
		
	}

}
