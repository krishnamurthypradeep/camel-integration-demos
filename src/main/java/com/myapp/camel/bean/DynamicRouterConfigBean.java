package com.myapp.camel.bean;

import org.apache.camel.Exchange;
import org.apache.camel.Header;
import org.springframework.stereotype.Component;

@Component("dynamicRouter")
public class DynamicRouterConfigBean {
	
	public String route(String body, @Header(Exchange.SLIP_ENDPOINT) String previous) {
		return identifyRoute(body,previous);
	}
	
public String identifyRoute(String body, String previous) {
		
		
	if (previous == null) {
		return "mock://a";
	} else if("mock://a".equals(previous)) {
		return "language://simple: Hello ${body}";
	} else {
		return null;
	}
	
	
	}

}
