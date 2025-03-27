package com.example.springai.demo.springai_demo.application.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TravelRecommendation {

	private List<City> cities;
	
}
