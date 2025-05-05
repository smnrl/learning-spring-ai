package com.example.springai.demo.springai_demo.application.readers;

import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.TextReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class CustomTextReader {

	private final Resource resource;

	CustomTextReader(@Value("classpath:/static/docs/text-source.txt") Resource resource) {
		this.resource = resource;
	}

	public List<Document> loadText() {
		TextReader textReader = new TextReader(this.resource);
		textReader.getCustomMetadata().put("filename", "text-source.txt");

		return textReader.read();
	}
}
