package com.example.springai.demo.springai_demo.tools;

import java.util.function.Function;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CriptomarketService implements Function<CriptomarketRequest, CriptomarketResponse> {

	@Override
	public CriptomarketResponse apply(CriptomarketRequest criptomarketRequest) {
		CriptomarketResponse criptomarketResponse = new CriptomarketResponse("BTC", Integer.valueOf(2), Long.valueOf(80000l), Long.valueOf(160000l));
		log.info("Creating bitcoin purchase order with function criptomarketservice {}", criptomarketResponse);
		return criptomarketResponse;
	}

}
