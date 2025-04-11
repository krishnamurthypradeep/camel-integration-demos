package com.myapp.camel.routes;

import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.file.GenericFileOperationFailedException;
import org.apache.camel.http.base.HttpOperationFailedException;
import org.apache.camel.model.dataformat.CsvDataFormat;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

//@Component

@AllArgsConstructor
public class CsvToRestEndPointRoute extends RouteBuilder {

	private CsvDataFormat csvDataFormatAddressUpdate;
	
	@Override
	public void configure() throws Exception {
		
		errorHandler(defaultErrorHandler().log(log));
		onException(GenericFileOperationFailedException.class)
		.handled(true)
		.log(LoggingLevel.ERROR,"File Component Failed due to ${exception.message}")
		.doTry()
		.process(e ->e.getContext().getRouteController().stopRoute("csv-to-rest"))
		.doCatch(Exception.class)
		.log(LoggingLevel.ERROR,"Could Not Stop Route")
		.end();
		
		onException(HttpOperationFailedException.class,SocketTimeoutException.class)
		.handled(true)
		.log(LoggingLevel.ERROR,"File to patch ${exception.message}")
		.maximumRedeliveries(3)
		.redeliveryDelay(5000)
		.end();
	
		ExecutorService executorService = Executors.newCachedThreadPool();
		
	        from("file:data/products1?include=order-.*.csv&noop=true&autoCreate=false&directoryMustExist=true") // Reads CSV from input directory
	            .routeId("csv-to-rest")
	            .unmarshal(csvDataFormatAddressUpdate)
	            .split(body())
	            .streaming()
	            .executorService(executorService)
	            .bean("linesToObjectMapper", "validate")
	            .bean("linesToObjectMapper", "map")
	            .setProperty("orderId", simple("${body.id}"))
	            .marshal().json()
	            
	            .log("Recieved Order : ${body}")
	            .toD("rest:patch:order/${exchangeProperty.orderId}?host=http://localhost:8080");
	            
		
	}
}
