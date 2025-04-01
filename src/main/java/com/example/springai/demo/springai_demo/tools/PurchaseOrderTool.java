package com.example.springai.demo.springai_demo.tools;

import org.springframework.ai.tool.annotation.ToolParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PurchaseOrderTool {
	
	public void createBitcoinPurchaseOrder(@ToolParam(description = "Bitcoin amount") Integer bitcoinAmount, 
			@ToolParam(description = "Current bitcoin euros price") Long currentBitcoinEurosPrice) {
		log.info("Create a purchase order for {} btc with each btc at {} euros price", bitcoinAmount, currentBitcoinEurosPrice);
	}
}
