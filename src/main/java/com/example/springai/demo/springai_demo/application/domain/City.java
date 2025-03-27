package com.example.springai.demo.springai_demo.application.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {

	private String name;
	
	private Long population;
}
