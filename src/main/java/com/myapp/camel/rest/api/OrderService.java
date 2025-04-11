package com.myapp.camel.rest.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.myapp.camel.dto.Order;

import jakarta.jws.WebService;

@WebService
public interface OrderService {
	
	 String updateOrder(
			OrderRequest request) ;
}
