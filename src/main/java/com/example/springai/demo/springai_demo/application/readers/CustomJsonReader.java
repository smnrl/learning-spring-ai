package com.example.springai.demo.springai_demo.application.readers;

import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.JsonReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class CustomJsonReader {

	private final Resource resource;
	
	CustomJsonReader(@Value("classpath:/static/docs/trends.json") Resource resource) {
        this.resource = resource;
    }
	
	public List<Document> loadJson() {
        JsonReader jsonReader = new JsonReader(this.resource, "etiqueta", "content");
        return jsonReader.get();
	}
}
