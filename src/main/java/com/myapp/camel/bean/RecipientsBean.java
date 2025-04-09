package com.myapp.camel.bean;

import org.apache.camel.language.xpath.XPath;
import org.springframework.stereotype.Component;

// Camel Language Annotation
// @XPath
// @Bean
// @JsonPath
// @Simple
// @XQuery

// Exchange
// @Header
// @Body
// @Attachments
// 


@Component("resolver")
public class RecipientsBean {
	
	public String[] process(@XPath("/order/customer/@category") String category) {
		
		if(isHighValueCustomer(category)) {
			return new String[] {"activemq:queue:accounting2","activemq:queue:production2"};
		
	}
		else {
			return new String[] {"activemq:queue:accounting2"};
		
	}
	}
	
	
	private boolean isHighValueCustomer(String type) {
		return type.equals("HighValue");
	}

}
