package com.example.springai.demo.springai_demo.tools;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.web.client.RestClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BitcoinTool {

	@Tool(description = "Get the current price of bitcoin cripto currency in euros")
	Long getBitcoinPriceInEuros() {

		BitcoinInfo bitcoinInfo = null;
		String apiResponse = "";
		try {
			apiResponse = RestClient.create().get().uri(new URI("https://cex.io/api/last_price/BTC/EUR")).retrieve()
					.body(String.class);

			bitcoinInfo = new ObjectMapper().readValue(apiResponse, BitcoinInfo.class);

		} catch (URISyntaxException | JsonProcessingException e) {
			e.printStackTrace();
		}

		log.info("The current bitcoin price in euros is {}", bitcoinInfo.getLprice());
		return bitcoinInfo.getLprice();
	}
	
}