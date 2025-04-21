package com.example.springai.demo.springai_demo.controllers;

import java.util.List;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.embedding.EmbeddingRequest;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.ai.ollama.OllamaEmbeddingModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/embedding")
public class EmbeddingController {

	private OllamaApi ollamaApi;

	private OllamaEmbeddingModel embeddingModel;

	@Autowired
	private EmbeddingModel autoEmbeddingModel;

	public EmbeddingController() {
		ollamaApi = new OllamaApi();
		embeddingModel = OllamaEmbeddingModel.builder().ollamaApi(ollamaApi)
				.defaultOptions(OllamaOptions.builder().model(OllamaModel.LLAMA3_2_1B).build()).build();
	}

	@GetMapping("/plain")
	public EmbeddingResponse embedd() {
		return embeddingModel.call(new EmbeddingRequest(List.of("Tendencias tecnologicas 2025", "Tendencia tecnologica IA", "Tendencia tecnologica accesibilidad"),
				OllamaOptions.builder().model(OllamaModel.LLAMA3_2_1B).truncate(false).build()

		));
	}

	@GetMapping("/auto")
	public EmbeddingResponse embed() {
		return autoEmbeddingModel.embedForResponse(List.of("Tendencias tecnologicas 2025", "Tendencia tecnologica IA", "Tendencia tecnologica accesibilidad"));
	}

}
