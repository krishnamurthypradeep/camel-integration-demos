package com.myapp.camel.routes;

import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.camel.test.spring.junit5.MockEndpoints;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@CamelSpringBootTest
@SpringBootTest
@MockEndpoints
public class DynamicRouterTestNew {
	
	

	
	@Autowired
	private ProducerTemplate producerTemplate;
	
	@Autowired
	@EndpointInject("mock:activemq:queue:modifiedorders")
	MockEndpoint mockModifiedOrders;
	
	@Autowired
	@EndpointInject("mock:activemq:queue:cancelledorders")
	MockEndpoint mockCancelledOrders;
	
	@Test
	public void testModifiedOrderRoute() throws Exception {
		
		mockModifiedOrders.expectedMessageCount(1);
		producerTemplate.sendBody("direct:orders","Order Id 1111 - updated");
		mockModifiedOrders.assertIsSatisfied();
		
	}

}
