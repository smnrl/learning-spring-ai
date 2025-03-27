package com.example.springai.demo.springai_demo.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prompt-template")
public class PromptTemplateController {

	private ChatModel chatModel;
	
	@Value("classpath:/static/prompts/system-message.st")
	private Resource systemResource;

	public PromptTemplateController(ChatModel chatModel) {
		this.chatModel = chatModel;
	}

	@GetMapping("/simple")
	String simplePromptTemplate(@RequestParam String adjective, @RequestParam String country) {

		PromptTemplate promptTemplate = new PromptTemplate("Give a {adjective} city from {country}");

		Prompt prompt = promptTemplate.create(Map.of("adjective", adjective, "country", country));

		return chatModel.call(prompt).getResult().getOutput().getText();
	}

	@GetMapping("/system")
	String systemPromptTemplate(@RequestParam String tema) {

		String userText = """
				Dame informacion sobre Barcelona.
				Responde por lo menos con como minimo 5 lineas.
				""";

		Message userMessage = new UserMessage(userText);

		String systemText = """
				Eres una asistente de IA que ayuda a la gente con informacion sobre ciudades.
				Tienes que responder con informacion de la ciudad sobre el tema {tema}.
				Responde como si estuvieras creando un blog de viajes
				""";

		SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(systemText);
		Message systemMessage = systemPromptTemplate.createMessage(Map.of("tema", tema));

		Prompt prompt = new Prompt(List.of(userMessage, systemMessage));

		return chatModel.call(prompt).getResult().getOutput().getText();
	}
	
	@GetMapping("/resource")
	String resourcePromptTemplate(@RequestParam String tema) {
		
		String userText = """
				Dame informacion sobre Londres.
				Responde por lo menos con como minimo 5 lineas.
				""";

		Message userMessage = new UserMessage(userText);
		
		SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(systemResource);
		
		Message systemMessage = systemPromptTemplate.createMessage(Map.of("tema", tema));

		Prompt prompt = new Prompt(List.of(userMessage, systemMessage));

		return chatModel.call(prompt).getResult().getOutput().getText();
	}

}
