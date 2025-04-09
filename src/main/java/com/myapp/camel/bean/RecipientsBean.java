package com.myapp.camel.bean;

import org.apache.camel.language.xpath.XPath;
import org.springframework.stereotype.Component;

@Component("resolver")
public class RecipientsBean {
	
	public String[] process(@XPath("/order/customer/@category") String category) {
		
		if(isHighValueCustomer(category)) {
			return new String[] {"activemq:queue:accounting1","activemq:queue:production1"};
		
	}
		else {
			return new String[] {"activemq:queue:accounting1"};
		
	}
	}
	
	
	private boolean isHighValueCustomer(String type) {
		return type.equals("HighValue");
	}

}
