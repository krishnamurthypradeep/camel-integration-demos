package com.myapp.camel.bean;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.jsonpath.JsonPath;
import org.springframework.stereotype.Component;

@Component
public class JsonToObjectBean {
	
	public Map<String,Object> toMap(@JsonPath("$.id") Integer id, @JsonPath("$.name")String name, 
			@JsonPath("$.priority")String priority){
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("id",id);
		map.put("name",name);
		map.put("priority",priority);
		return map;
	}

}
