package com.example.springai.demo.springai_demo.application;

import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class IngestionService implements IngestionServicePort {

	private VectorStore vectorStore;

	public IngestionService(VectorStore vectorStore) {
		this.vectorStore = vectorStore;
	}
	
	@Override
	public void ingestCodeData(List<Document> codeDocuments) {
		vectorStore.accept(codeDocuments);
		log.info("Code data loaded in vectorStore");
	}
	
}
