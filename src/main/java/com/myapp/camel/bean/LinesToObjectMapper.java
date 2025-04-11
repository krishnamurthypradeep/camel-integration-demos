package com.myapp.camel.bean;


import java.util.List;

import org.springframework.stereotype.Component;

import com.myapp.camel.dto.Order;

@Component
public class LinesToObjectMapper {
    public void validate(List<String> lines) throws InvalidCustomerDataException {
        if(lines == null ){
throw new InvalidCustomerDataException("Invalid row must have 6 columns");
        }

        if(lines.get(0) == null || lines.get(0).isBlank()){
            throw new InvalidCustomerDataException("Invalid name, required field");
        }
        if(lines.get(1) == null || lines.get(1).isBlank()){
            throw new InvalidCustomerDataException("Invalid priority, required field");
        }
        

    }

    public Order map(List<String> lines){
      Order order = new Order();
      order.setId(Integer.valueOf(lines.get(0)));
      order.setName(lines.get(1));
      order.setPriority(lines.get(2));
      return order;
    }
}
