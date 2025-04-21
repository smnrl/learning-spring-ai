package com.example.springai.demo.springai_demo.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springai.demo.springai_demo.application.IngestionServicePort;

@RestController
@RequestMapping("/rag")
public class RagController {

	private ChatModel chatModel;
	
	private ChatClient chatClient;
	
	private VectorStore vectorStore;
	
	private IngestionServicePort ingestionService;
	
	public RagController(ChatClient.Builder builder, VectorStore vectorStore
			, ChatModel chatModel
			, IngestionServicePort ingestionService) {
        this.chatClient = builder.build();
        this.vectorStore = vectorStore;
        this.ingestionService = ingestionService;
        this.chatModel = chatModel;
    }
	
	@PostMapping("/load-data")
	public void loadData() {
		ingestionService.ingestData();
	}

	@GetMapping("/no-rag")
	public String noRag() {
		return chatClient.prompt()
				.user("que me puedes decir de los aranceles de EEUU en el año 2025")
				.call()
				.content();
	}
	
	@GetMapping("/rag")
	public String rag() {
		return ChatClient.builder(chatModel)
		        .build().prompt()
		        .advisors(new QuestionAnswerAdvisor(vectorStore))
		        .user("que me puedes decir de los aranceles de EEUU en el año 2025")
		        .call()
		        .content();
	}

}
