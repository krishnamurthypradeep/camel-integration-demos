package com.myapp.camel.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Order {
	
	private Integer id;
	private String name;
	private String priority;

}
