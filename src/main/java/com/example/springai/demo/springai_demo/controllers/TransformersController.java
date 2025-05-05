package com.example.springai.demo.springai_demo.controllers;

import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springai.demo.springai_demo.application.transformers.CustomKeywordEnricher;
import com.example.springai.demo.springai_demo.application.transformers.CustomSummaryEnricher;
import com.example.springai.demo.springai_demo.application.transformers.CustomTokenTextSplitter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/transformers")
public class TransformersController {

	@Autowired
	private CustomTokenTextSplitter tokenTextSplitter;
	
	@Autowired
	private CustomKeywordEnricher keywordEnricher;
	
	@Autowired
	private CustomSummaryEnricher summaryEnricher;

	@GetMapping("/token")
	public List<Document> getDocumentsFromJsonReader() {
		return tokenTextSplitter.splitCustomized(DocumentUtils.getDocuments());
	}
	
	@GetMapping("/keyword")
	public List<Document> getKeywordData() {
		return keywordEnricher.enrichDocuments(DocumentUtils.getDocuments());
	}
	
	@GetMapping("/summary")
	public List<Document> getSummary() {
		return summaryEnricher.enrichDocuments(DocumentUtils.getDocuments());
	}

}
