package com.example.springai.demo.springai_demo.config;

import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MultimodalConfig {

	@Bean("llava-model")
	OllamaChatModel ollamaLlava(OllamaApi ollamaApi) {
		return OllamaChatModel.builder()
			.ollamaApi(ollamaApi)
			.defaultOptions(OllamaOptions.builder().model(OllamaModel.LLAVA).temperature(0.9).build())
			.build();
	}
	
	@Primary
	@Bean("chat-model")
	OllamaChatModel ollamaChat(OllamaApi ollamaApi) {
		return OllamaChatModel.builder()
			.ollamaApi(ollamaApi)
			.defaultOptions(OllamaOptions.builder().model(OllamaModel.LLAMA3_2_1B).build())
			.build();
	}
}
