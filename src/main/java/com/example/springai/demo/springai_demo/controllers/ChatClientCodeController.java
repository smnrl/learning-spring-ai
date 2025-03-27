package com.example.springai.demo.springai_demo.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatClientCodeController {
	
	private ChatModel myChatModel;
	
	private ChatClient chatClient;
	
	public ChatClientCodeController(@Qualifier("chat-model") ChatModel myChatModel) {
        this.myChatModel = myChatModel;
        this.chatClient = ChatClient.create(this.myChatModel);
//        this.chatClient = ChatClient.builder(this.myChatModel).build(); //Mismo resultado que la linea anterior
    }

    @GetMapping("/chat-client-programmatically")
    String chatClientGeneration(@RequestBody String userInput) {
        return this.chatClient.prompt()
            .user(userInput)
            .call()
            .content();
    }

}
