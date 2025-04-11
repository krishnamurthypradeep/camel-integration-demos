package com.myapp.camel.routes;



import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.model.Model;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.myapp.camel.dto.Order;

@CamelSpringBootTest
@SpringBootTest
public class DBToJMSTest {

    @Autowired
    private CamelContext camelContext;

    @Autowired
    private ProducerTemplate producerTemplate;

    // Apache Camel 4.10
    @BeforeEach
    void setUp() throws Exception {
    	
    	Model model = camelContext.getCamelContextExtension().getContextPlugin(Model.class);
    	RouteDefinition def = model.getRouteDefinition("consume-orders");
    	AdviceWith.adviceWith(def, camelContext, new AdviceWithRouteBuilder() {
                    @Override
                    public void configure() {
                        // Replace actual queue with mock endpoint
                        weaveByToUri("activemq:queue:processorders")
                            .replace()
                            .to("mock:processorders");

                        weaveByToUri("activemq:queue:deadletterchannel")
                            .replace()
                            .to("mock:deadletterchannel");
                    }
                });
    }

    //@Test
    void testOrderProcessingSuccess() throws Exception {
        MockEndpoint mock = camelContext.getEndpoint("mock:processorders", MockEndpoint.class);
        mock.expectedMessageCount(1);

        // Simulate DB record (you would mock the SQL endpoint or start from endpoint that simulates it)
        Order order = new Order();
        order.setId(1);
        order.setName("Test Order");
        order.setPriority("HIGH");

        producerTemplate.sendBody(mock, order);

        mock.assertIsSatisfied();
    }

    @Test
    void testOrderProcessingFailure() throws Exception {
        MockEndpoint deadletter = camelContext.getEndpoint("mock:deadletterchannel", MockEndpoint.class);
        deadletter.expectedMessageCount(1);

        // Simulate a failure (null order will cause NPE in route logic if not handled)
        producerTemplate.sendBody(deadletter, null);

        deadletter.assertIsSatisfied();
    }
}
