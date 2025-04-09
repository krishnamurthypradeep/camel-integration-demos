package com.myapp.camel.dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@XmlRootElement
@Data
public class Product {
	
	private String company;
	
	private String name;
	
	private double price;

}
