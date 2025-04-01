package com.example.springai.demo.springai_demo.controllers;

import java.lang.reflect.Method;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.definition.ToolDefinition;
import org.springframework.ai.tool.function.FunctionToolCallback;
import org.springframework.ai.tool.method.MethodToolCallback;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springai.demo.springai_demo.tools.BitcoinTool;
import com.example.springai.demo.springai_demo.tools.CriptomarketRequest;
import com.example.springai.demo.springai_demo.tools.CriptomarketService;
import com.example.springai.demo.springai_demo.tools.PurchaseOrderTool;

@RestController
@RequestMapping("/tool-calling")
public class ToolCallingController {

	private ChatClient chatClient;

	public ToolCallingController(ChatClient.Builder chatClientBuilder) {
		this.chatClient = chatClientBuilder.build();
	}
	
	@GetMapping("/")
    String standard() {
        return this.chatClient.prompt()
            .user("Give me the current bitcoin price")
            .call()
            .content();
    }

	@GetMapping("/btc")
	String toolCallingBtcPrice() {
		
		return this.chatClient.prompt().user("Give me the current bitcoin price").tools(new BitcoinTool()).call()
				.content();
	}
	
	@GetMapping("/btc-purchase-order")
	String toolCallingBtcPriceSavePurchaseOrder() {
		
		Method method = ReflectionUtils.findMethod(PurchaseOrderTool.class, "createBitcoinPurchaseOrder", Integer.class, Long.class);
		ToolCallback toolCallback = MethodToolCallback.builder()
		    .toolDefinition(ToolDefinition.builder(method)
		            .description("Create bitcoin purchase order of bitcoin amount at current bitcoin euros price")
		            .build())
		    .toolMethod(method)
		    .toolObject(new PurchaseOrderTool())
		    .build();
		
		return this.chatClient.prompt().user("Give me the current bitcoin price in euros and create a bitcoin purchase order of 10 bitcoins at this current bitcoin euros price")
				.tools(new BitcoinTool())
				.tools(toolCallback)
				.call()
				.content();
	}
	
	@GetMapping("/function")
	String toolCallingDynamicFunction() {
		
		ToolCallback toolCallback = FunctionToolCallback
			    .builder("criptomarket", new CriptomarketService())
			    .description("Create bitcoin market purchase")
			    .inputType(CriptomarketRequest.class)
			    .build();
		
		return this.chatClient.prompt()
				.user("Create bitcoin market purchase")
				.tools(toolCallback)
				.call()
				.content();
	}
}
