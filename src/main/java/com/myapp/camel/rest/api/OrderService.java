package com.myapp.camel.rest.api;

import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService
public interface OrderService {
	
	 String updateOrder(
			 @WebParam(name = "order") OrderRequest request) ;
}
