package com.example.springai.demo.springai_demo.application.readers;

import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class CustomTikaReader {

	private final Resource resource;
	
	private final Resource resourceCode;

	CustomTikaReader(@Value("classpath:/static/docs/BitcoinTool.java") Resource resource,
			@Value("classpath:/static/docs/ParadigmaSpecialService.java") Resource resourceCode) {
		this.resource = resource;
		this.resourceCode = resourceCode;
	}

	public List<Document> loadData() {
		TikaDocumentReader tikaDocumentReader = new TikaDocumentReader(this.resource);
		return tikaDocumentReader.read();
	}
	
	public List<Document> loadCode() {
		TikaDocumentReader tikaDocumentReader = new TikaDocumentReader(this.resourceCode);
		return tikaDocumentReader.read();
	}

}
