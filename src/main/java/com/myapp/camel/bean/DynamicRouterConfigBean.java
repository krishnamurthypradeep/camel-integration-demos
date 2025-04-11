package com.myapp.camel.bean;

import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

import com.myapp.camel.dto.Order;

@Component("dynamicRouterConfig")
public class DynamicRouterConfigBean {
	
	public String route(Exchange exchange) {
		System.out.println("******** "+exchange.getIn().getBody().getClass().getSimpleName());
		
        Order order = exchange.getIn().getBody(Order.class);
        
        System.out.println("Order **** "+order);
        String priority = order.getPriority().toUpperCase();

        switch (priority) {
            case "HIGH":
                return "activemq:queue:orders.high";
            case "MEDIUM":
                return "activemq:queue:orders.medium";
            case "LOW":
                return "activemq:queue:orders.low";
            default:
                return null; // End routing
        }
    }
	
////	public String route(String body, @Header(Exchange.SLIP_ENDPOINT) String previous) {
////		return identifyRoute(body,previous);
////	}
//	// ActiveMq, RabbitMq, IBM MQ
//	
//	public String route(String body) {
//		
//		if(body.contains("modified")) {
//			return "activemq:queue:modifiedorders";
//		} 
//		else if(body.contains("cancelled")) {
//			return "activemq:queue:cancelledorders";
//		}
//		else {
//			return null;
//		}
//		
//	}
//	
//public String identifyRoute(String body, String previous) {
//		
//		
//	if (previous == null) {
//		return "mock://a";
//	} else if("mock://a".equals(previous)) {
//		return "language://simple: Hello ${body}";
//	} else {
//		return null;
//	}
//	
//	
//	}

}
