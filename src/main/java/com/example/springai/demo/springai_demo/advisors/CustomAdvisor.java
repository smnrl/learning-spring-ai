package com.example.springai.demo.springai_demo.advisors;

import java.util.Arrays;

import org.springframework.ai.chat.client.advisor.api.AdvisedRequest;
import org.springframework.ai.chat.client.advisor.api.AdvisedResponse;
import org.springframework.ai.chat.client.advisor.api.CallAroundAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAroundAdvisorChain;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomAdvisor implements CallAroundAdvisor {

	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}

	@Override
	public int getOrder() {
		return 0;
	}

	@Override
	public AdvisedResponse aroundCall(AdvisedRequest advisedRequest, CallAroundAdvisorChain chain) {
		AdvisedResponse originalResponse = chain.nextAroundCall(this.beforeCall(advisedRequest));
		return this.afterCall(originalResponse);
	}

	private AdvisedRequest beforeCall(AdvisedRequest advisedRequest) {
		String inputUpperCase = advisedRequest.userText().toUpperCase();
		log.info("Input transformed {}", inputUpperCase);
		AdvisedRequest request = AdvisedRequest.from(advisedRequest).userText(inputUpperCase).build();
		log.info("Peticion al modelo {}", request);
		return request;
	}
	
	private AdvisedResponse afterCall(AdvisedResponse advisedResponse) {
		log.info("Orignal response {}", advisedResponse);
		String textOutputLowerCase = advisedResponse.response().getResult().getOutput().getText().toLowerCase();
		
		return AdvisedResponse.builder()
			.response(new ChatResponse(Arrays.asList(new Generation(new AssistantMessage(textOutputLowerCase)))))
			.adviseContext(advisedResponse.adviseContext())
			.build();
		
	}

}
