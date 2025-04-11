package com.myapp.camel.routes;

import java.net.SocketTimeoutException;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.http.base.HttpOperationFailedException;
import org.springframework.stereotype.Component;

@Component


public class SoapToActiveMqRoute extends RouteBuilder {

	
	
	@Override
	public void configure() throws Exception {
		
		errorHandler(defaultErrorHandler().log(log));
		
		
		onException(HttpOperationFailedException.class,SocketTimeoutException.class)
		.handled(true)
		.log(LoggingLevel.ERROR,"File to patch ${exception.message}")
		.maximumRedeliveries(3)
		.redeliveryDelay(5000)
		.end();
	
		
		
	        from("cxf:/orders?serviceClass=com.myapp.camel.rest.api.OrderService") // Reads CSV from input directory
	            
	            
	            .log("Recieved Order : ${body}")
	            .to("activemq:queue:soaporderqueue");
	            
		
	}
}
