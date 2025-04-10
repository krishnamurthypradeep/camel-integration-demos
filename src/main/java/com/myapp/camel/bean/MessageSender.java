package com.myapp.camel.bean;

import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {
	
	
	private ProducerTemplate producerTemplate;

	public MessageSender(ProducerTemplate producerTemplate) {
		
		this.producerTemplate = producerTemplate;
	}
	
	
	public void sendToDirect(String body) {
		producerTemplate.sendBody("direct:start",body);
	}
	
	

}
