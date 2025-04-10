package com.myapp.camel.bean;

import org.apache.camel.Body;
import org.apache.camel.RoutingSlip;
import org.springframework.stereotype.Component;

import com.myapp.camel.dto.Order;

@Component("routingSlip")
public class RoutingSlipBean {

	@RoutingSlip
	public String identifyRoute(@Body Order order) {
		
		if("High".equalsIgnoreCase(order.getPriority())) {
			return "activemq:queue:highpriorityorders";
		} else {
			return "activemq:queue:normalpriorityorders";
		}
	}
}
