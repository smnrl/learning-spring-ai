package com.example.springai.demo.springai_demo.application;

import java.util.List;

import org.springframework.ai.document.Document;

public interface IngestionServicePort {
	
	void ingestCodeData(List<Document> codeDocuments);
}
