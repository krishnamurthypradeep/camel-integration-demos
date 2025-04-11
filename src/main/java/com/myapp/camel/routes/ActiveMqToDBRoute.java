package com.myapp.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedCaseInsensitiveMap;

import com.myapp.camel.dto.Order;

//@Component
public class ActiveMqToDBRoute extends RouteBuilder {
	
	// LinkedCaseInsend
	
	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		 JacksonDataFormat orderJsonFormat = new JacksonDataFormat(Order.class);
		from("sql:select id, name, priority from orders?consumer.onConsume=delete from orders where id=:#id&transacted=true")
        .routeId("consume-orders")
        .doTry()
       
        
            .process(exchange -> {
            	// throw new RuntimeException("Simulated failure");
                LinkedCaseInsensitiveMap<?> row = exchange.getIn().getBody(org.springframework.util.LinkedCaseInsensitiveMap.class);
                if (row == null) {
                    throw new IllegalStateException("Row is null");
                }
                Order order = new Order();
                order.setId((Integer) row.get("id"));
                order.setName((String) row.get("name"));
                order.setPriority((String) row.get("priority"));
                exchange.getIn().setBody(order);
            })
            .marshal(orderJsonFormat)
            .to("activemq:queue:processorders")
        .endDoTry()
        .doCatch(RuntimeException.class)
            .to("activemq:queue:deadletterchannel");
		
	}

}
