package com.myapp.camel.rest.api;

import org.springframework.stereotype.Service;

import jakarta.jws.WebService;

@WebService(endpointInterface = "com.myapp.camel.rest.api.OrderService")
@Service
public class OrderServiceImpl implements OrderService {

	@Override
	public String updateOrder(OrderRequest request) {
		// TODO Auto-generated method stub
		return "Order Processed";
	}

}
