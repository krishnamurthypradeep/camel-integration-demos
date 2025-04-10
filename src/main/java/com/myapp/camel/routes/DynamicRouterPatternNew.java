package com.myapp.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class DynamicRouterPatternNew extends RouteBuilder{

	// direct | seda
	// direct components are synchronous in memory routes
	
	@Override
	public void configure() throws Exception {
		
		
		from("direct:orders")
		.dynamicRouter(method("dynamicRouterConfig", "route"));
		//.to("mock:result");
		
	}
}
