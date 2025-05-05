package com.example.springai.demo.springai_demo.application;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ParadigmaSpecialService {

	public void callingSpecialService() {
		log.info("This is the implementation for the special service with Paradigma rules");
	}
}
