package com.myapp.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedCaseInsensitiveMap;

import com.myapp.camel.dto.Order;

@Component
public class DBToActiveMQRoute extends RouteBuilder {
	
	// LinkedCaseInsend
	
	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		
		from("activemq:queue:highPriorityOrders")
        .routeId("consume-orders-mq-to-db")
        .transacted()
        .doTry()
       
           .bean("jsonToObjectBean", "toMap")
           .wireTap("activemq:queue:jmstodb")
            .to("sql:insert into orders (id,name,priority) values (:#id,:#name,:#priority)")
        .endDoTry()
        .doCatch(RuntimeException.class)
            .to("activemq:queue:deadletterchannel");
		
	}

}
