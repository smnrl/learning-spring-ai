package com.example.springai.demo.springai_demo.controllers;

import java.util.List;

import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.Media;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OllamaController {

	private OllamaChatModel chatModel;

	public OllamaController(@Qualifier("llava-model") OllamaChatModel chatModel) {
		this.chatModel = chatModel;
	}

	@GetMapping("/multimodal")
	String multimodal() {

		ClassPathResource imageResource = new ClassPathResource("/static/multimodal.jpg");

		UserMessage userMessage = new UserMessage("Explicame lo que ves en la imagen",
				new Media(MimeTypeUtils.IMAGE_JPEG, imageResource));

		return chatModel.call(new Prompt(List.of(userMessage))).getResult().getOutput().getText();
	}

	@GetMapping("/city-name-generation")
	String cityNameGeneration() {

		return chatModel
				.call(new Prompt("Inventa 5 nombres de ciudades.",
						OllamaOptions.builder().model(OllamaModel.LLAMA3_2_1B).temperature(0.4).build()))
				.getResult().getOutput().getText();
	}

}
