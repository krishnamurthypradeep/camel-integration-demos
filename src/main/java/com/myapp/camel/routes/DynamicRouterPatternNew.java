package com.myapp.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.springframework.stereotype.Component;

import com.myapp.camel.dto.Order;

//@Component
public class DynamicRouterPatternNew extends RouteBuilder{

	// direct | seda
	// direct components are synchronous in memory routes
	
	@Override
	public void configure() throws Exception {
		
		BindyCsvDataFormat bindy = new BindyCsvDataFormat(Order.class);
        JacksonDataFormat json = new JacksonDataFormat(Order.class);

        from("file:data/products?include=order-.*.csv&noop=true&autoCreate=false&directoryMustExist=true")
            .routeId("csv-dynamic-router")
            .unmarshal(bindy)
            .split(body())
            .convertBodyTo(Order.class) 
            .marshal(json)
            .dynamicRouter(method("dynamicRouterConfig", "route"));
		
		
//		from("direct:orders")
//		.dynamicRouter(method("dynamicRouterConfig", "route"));
//		//.to("mock:result");
//		
	}
}
