package com.myapp.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class RecipientListPattern  extends RouteBuilder{
	
	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
	
		// apache camel is going to threadpool 
		
		from("file:data/products?include=order-.*.xml&noop=true&autoCreate=false&directoryMustExist=true")
		
		.setHeader("recipients",method("resolver","process"))
		.recipientList(header("recipients"));
		
//		from("activemq:queue:activeOrders")
//		.unmarshall().pgp(new File("privatekey"),"")
//		.log("")
		
	}

}
// Simple
// Xpath
// Json
// Csv


// Recipient List Pattern
