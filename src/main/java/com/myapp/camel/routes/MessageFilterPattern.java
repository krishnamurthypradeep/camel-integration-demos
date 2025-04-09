package com.myapp.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class MessageFilterPattern  extends RouteBuilder{
	
	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
	
		from("file:data/products?include=order-.*.xml&noop=true&autoCreate=false&directoryMustExist=true")
		
		.filter(xpath("/order/customer[@type='active']"))
		.to("activemq:queue:activeOrders");
		
	}

}
