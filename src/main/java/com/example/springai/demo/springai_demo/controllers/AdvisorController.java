package com.example.springai.demo.springai_demo.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springai.demo.springai_demo.advisors.CustomAdvisor;

@RestController
@RequestMapping("/advisor")
public class AdvisorController {

	private ChatClient chatClient;
	
	private ChatClient memoryChatClient;

	public AdvisorController(ChatClient.Builder chatClientBuilder) {
		
		this.chatClient = chatClientBuilder
				.build();
		this.memoryChatClient = chatClientBuilder.clone()
				.defaultAdvisors(new MessageChatMemoryAdvisor(new InMemoryChatMemory()))
				.build();
	}
	
	@GetMapping("/")
    String standard(@RequestBody String userInput) {
        return this.chatClient.prompt()
            .user(userInput)
            .call()
            .content();
    }
	
	@GetMapping("/memory")
    String memory(@RequestBody String userInput) {
        return this.memoryChatClient.prompt()
            .user(userInput)
            .call()
            .content();
    }
	
	@GetMapping("/custom")
    String custom(@RequestBody String userInput) {
        return this.chatClient.prompt()
        	.advisors(new CustomAdvisor())
            .user(userInput)
            .call()
            .content();
    }
	
}
