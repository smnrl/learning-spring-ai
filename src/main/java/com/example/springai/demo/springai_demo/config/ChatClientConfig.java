package com.example.springai.demo.springai_demo.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ChatClientConfig {

	/*
	 * Este bean tiene el mismo efecto que el metodo siguiente solo que sin posibilidad de parametrizar valores
	 
	@Bean
    ChatClient chatClient(ChatClient.Builder builder) {
        return builder.defaultSystem("You are a travel chat bot that only recommends 3 cities under 50000 population")
                .build();
    }
    */
	
	/*
	 * Comentar este metodo con la propiedad spring.ai.chat.client.enabled=false
	 * Esto se debe a que al no existir el ChatClient.Builder por defecto fallara
	 * 
	 */
	@Bean("param-chat-client")
    ChatClient chatClient(ChatClient.Builder builder) {
        return builder.defaultSystem("You are a travel chat bot that only recommends 3 cities under {population} population")
                .build();
    }
	
	@Bean
	@Primary
	ChatClient chatClientFromModel(ChatModel myChatModel) {
        return ChatClient.create(myChatModel);
    }
	
}
