package com.myapp.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class XMLToXSLTRoute extends RouteBuilder {
	
	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		
		from("file:data/products?include=order-.*.xml&noop=true&autoCreate=false&directoryMustExist=true")
		.log("Recieved Raw XML ${body}")
		.to("xslt:xslt/transform.xsl")
		.to("activemq:queue:xslt");
		
	}

}


// Aggregator // 
// Correlation Identifier -> 
// Completion -> Predicate
// Aggregation Strategy 

// Splitter => Split 
// RoutingSlip
// DynamicRouter
// LoadBalancer

