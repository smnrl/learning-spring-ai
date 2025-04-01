package com.example.springai.demo.springai_demo.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.ai.converter.MapOutputConverter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/structured-output")
public class StructuredOutputController {

	private ChatClient chatClient;

	public StructuredOutputController(ChatClient.Builder chatClientBuilder) {
		this.chatClient = chatClientBuilder.build();
	}

	@GetMapping("/map")
	Map<String, Object> getStandardOutputMap() {
		
		MapOutputConverter outputConverter = new MapOutputConverter();

		String format = outputConverter.getFormat();
		String template = """
				Dame una lista de las 3 criptomonedas mas importantes con su nombre, su abreviatura y una breve descripcion.
				Ejemplo: Bitcoin -> "abreviatura": "BTC", "descripcion": "la criptomoneda mas importante".
				{format}
				""";
		
		PromptTemplate promptTemplate = new PromptTemplate(template, Map.of("format", format));
		Prompt prompt = new Prompt(promptTemplate.createMessage());
		
		return chatClient.prompt(prompt)
				.call()
				.entity(outputConverter);
	}
	
	@GetMapping("/list")
	List<String> getStandardOutputList() {
		
		ListOutputConverter listOutputConverter = new ListOutputConverter(new DefaultConversionService());

		String format = listOutputConverter.getFormat();
		String template = """
		        Dame una lista de 5 criptomonedas
		        {format}
		        """;
		Prompt prompt = new PromptTemplate(template, Map.of("format", format)).create();

		return chatClient.prompt(prompt)
				.call()
				.entity(listOutputConverter);
	}
}
