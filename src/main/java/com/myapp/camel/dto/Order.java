package com.myapp.camel.dto;

import java.io.Serializable;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@CsvRecord(separator = ",", skipFirstLine = true)
public class Order implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@DataField(pos = 1)
	private Integer id;
	@DataField(pos = 2)
	private String name;
	@DataField(pos = 3)
	private String priority;

}



