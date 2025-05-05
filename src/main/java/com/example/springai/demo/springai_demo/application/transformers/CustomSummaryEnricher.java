package com.example.springai.demo.springai_demo.application.transformers;

import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.model.transformer.SummaryMetadataEnricher;
import org.springframework.stereotype.Component;

@Component
public class CustomSummaryEnricher {
	
	private final SummaryMetadataEnricher enricher;

	CustomSummaryEnricher(SummaryMetadataEnricher enricher) {
        this.enricher = enricher;
    }

    public List<Document> enrichDocuments(List<Document> documents) {
        return this.enricher.apply(documents);
    }

}
