package com.example.springai.demo.springai_demo.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springai.demo.springai_demo.application.domain.TravelRecommendation;

@RestController
public class TravelController {

	private ChatClient chatClient;
	
	/*
	 * Comentar el Qualifier con la propiedad spring.ai.chat.client.enabled=false
	 * Esto se debe a que al no existir el ChatClient.Builder por defecto, no se
	 * creara el bean correspondiente en ChatClientConfig
	 * 
	 */
	@Autowired
	@Qualifier("param-chat-client")
	private ChatClient paramChatClient;
	
	public TravelController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/travel-recommendation")
    String travelRecommendation(@RequestBody String userInput) {
        return this.chatClient.prompt()
            .user(userInput)
            .call()
            .content();
    }
	
    @GetMapping("/travel-recommendation-population")
    String travelRecommendationPopulation(@RequestBody String userInput, @RequestParam Long population) {
        return this.paramChatClient.prompt()
        	.system(sp -> sp.param("population", population))
            .user(userInput)
            .call()
            .content();
    }
    
    @GetMapping("/travel-recommendation/chat-response")
    ChatResponse travelRecommendationResponse(@RequestBody String userInput) {
        return this.chatClient.prompt()
            .user(userInput)
            .call()
            .chatResponse();
    }
    
    @GetMapping("/travel-recommendation/entity")
    TravelRecommendation travelRecommendationEntity(@RequestBody String userInput) {
        return this.chatClient.prompt()
            .user(userInput)
            .call()
            .entity(TravelRecommendation.class);
    }
    
}
