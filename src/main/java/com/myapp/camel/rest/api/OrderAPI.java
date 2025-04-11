package com.myapp.camel.rest.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.camel.dto.Order;

@RestController
public class OrderAPI {
	
 Logger logger =	LoggerFactory.getLogger(OrderAPI.class);

	@PatchMapping(path="/order/{id}")
	public ResponseEntity<Order> updateOrder(
			@PathVariable Integer id,@RequestBody Order order){
		
		logger.debug("Recieved Order "+order);
		return ResponseEntity.ok(order);
	}
	
}
