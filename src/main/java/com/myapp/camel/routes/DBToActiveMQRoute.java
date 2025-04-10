package com.myapp.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionException;

import com.myapp.camel.dto.Order;

import io.vavr.collection.Map;

@Component
public class DBToActiveMQRoute extends RouteBuilder {
	
	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		
		from("sql:select * from orders?consumer.onConsume=delete from orders where id=:#id&transacted=true")
		.doTry()
		.process(e->{
		
		Map<String,Object>	 row= e.getIn().getBody(Map.class);
		Order order = new Order();
		 order.setId((Integer)row.get("id").get());
		 order.setName((String)row.get("name").get());
		 order.setName((String)row.get("priority").get());
		 e.getIn().setBody(order);
		}).marshal(new JacksonDataFormat(Order.class))
		.to("activemq:queue:processorders")
		.endDoTry()
		.doCatch(TransactionException.class)
		.to("activemq:queue:deadletterchannel")
		;
		
	}

}
