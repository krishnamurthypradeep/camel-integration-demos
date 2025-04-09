package com.myapp.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.springframework.stereotype.Component;

import com.myapp.camel.dto.Product;

@Component
public class XmlToJsonRoutes extends RouteBuilder{
	
	@Override
	public void configure() throws Exception {
	
		JaxbDataFormat jaxb = new JaxbDataFormat();
		jaxb.setContextPath(Product.class.getPackageName());
		
		from("file:data/xmltojson?include=products-.*.xml&noop=true&autoCreate=false&directoryMustExist=true")
		.unmarshal(jaxb)
		.marshal().json().to("file:data/output");
		
		
		
	}

}
