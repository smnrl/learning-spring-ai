package com.example.springai.demo.springai_demo.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springai.demo.springai_demo.application.IngestionServicePort;
import com.example.springai.demo.springai_demo.application.readers.CustomTikaReader;

@RestController
@RequestMapping("/rag")
public class RagController {

	private ChatModel chatModel;
	
	private ChatClient chatClient;
	
	private VectorStore vectorStore;
	
	private IngestionServicePort ingestionService;
	
	@Autowired
	private CustomTikaReader tikaReader;
	
	public RagController(ChatClient.Builder builder, VectorStore vectorStore
			, ChatModel chatModel
			, IngestionServicePort ingestionService) {
        this.chatClient = builder.build();
        this.vectorStore = vectorStore;
        this.ingestionService = ingestionService;
        this.chatModel = chatModel;
    }

	@GetMapping("/no-rag")
	public String noRag() {
		return chatClient.prompt()
				.user("Dame un ejemplo de funcion especial hecho por Paradigma")
				.call()
				.content();
	}
	
	@PostMapping("/load-code")
	public void loadCode() {
		ingestionService.ingestCodeData(tikaReader.loadCode());
		
	}
	
	@GetMapping("/code")
	public String code() {
		return ChatClient.builder(chatModel)
		        .build().prompt()
		        .advisors(new QuestionAnswerAdvisor(vectorStore))
		        .user("Dame un ejemplo de funcion especial hecho por Paradigma")
		        .call()
		        .content();
	}

}
