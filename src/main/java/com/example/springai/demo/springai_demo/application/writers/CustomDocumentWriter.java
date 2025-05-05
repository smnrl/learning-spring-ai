package com.example.springai.demo.springai_demo.application.writers;

import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.document.MetadataMode;
import org.springframework.ai.writer.FileDocumentWriter;
import org.springframework.stereotype.Component;

@Component
public class CustomDocumentWriter {

	public void writeDocuments(List<Document> documents) {
		FileDocumentWriter writer = new FileDocumentWriter("./src/main/resources/static/docs/output.txt", true, MetadataMode.ALL, false);
		writer.accept(documents);
	}

}
