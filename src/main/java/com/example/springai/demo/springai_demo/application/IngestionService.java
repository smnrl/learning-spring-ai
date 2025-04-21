package com.example.springai.demo.springai_demo.application;

import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class IngestionService implements IngestionServicePort {

	private VectorStore vectorStore;
	@Value("classpath:/static/docs/aranceles2025.pdf")
	private Resource arancelesPdf;

	public IngestionService(VectorStore vectorStore) {
		this.vectorStore = vectorStore;
	}
	
	@Override
	public void ingestData() {
		
//		var pdfReader = new ParagraphPdfDocumentReader(arancelesPdf);
		var pdfReader = new PagePdfDocumentReader(arancelesPdf);
		TextSplitter textSplitter = new TokenTextSplitter();
		vectorStore.accept(textSplitter.apply(pdfReader.get()));
		log.info("Data loaded in vectorStore");
	}
	
	
}
