package com.myapp.camel.routes;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@CamelSpringBootTest
@SpringBootTest

public class DynamicRouterTest {
	
	@Autowired
    private CamelContext camelContext;

	
	@Autowired
	private ProducerTemplate producerTemplate;
	
	@EndpointInject("mock:result")
	MockEndpoint mockResultEndPoint;
	
	@EndpointInject("mock:a")
	MockEndpoint mockAEndPoint;
	
	@Test
	public void testDynamicRoute() throws Exception {
		
		
		mockAEndPoint.expectedBodiesReceived("Camel");
		
		mockResultEndPoint.expectedBodiesReceived("Hello Camel1");
		
		producerTemplate.sendBody("direct:start","Camel");
		
		mockResultEndPoint.assertIsSatisfied();
		
	}

}
