package com.myapp.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class XMLToXSLTRoute extends RouteBuilder {
	
	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		
		from("file:data/products?include=orders-.*.xml&noop=true&autoCreate=false&directoryMustExist=true")
		.log("Recieved Raw XML ${body}")
		.to("xslt:xslt/transform.xml")
		.to("activemq:queue:xslt");
		
	}

}
