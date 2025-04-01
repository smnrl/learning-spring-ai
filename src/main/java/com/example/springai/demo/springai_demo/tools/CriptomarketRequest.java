package com.example.springai.demo.springai_demo.tools;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CriptomarketRequest {

	private String currency;
	private Integer amount;
	
}
