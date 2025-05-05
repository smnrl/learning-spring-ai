package com.example.springai.demo.springai_demo.application.transformers;

import java.util.List;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.document.Document;
import org.springframework.ai.model.transformer.KeywordMetadataEnricher;
import org.springframework.stereotype.Component;

@Component
public class CustomKeywordEnricher {

	private final ChatModel chatModel;

	CustomKeywordEnricher(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public List<Document> enrichDocuments(List<Document> documents) {
        KeywordMetadataEnricher enricher = new KeywordMetadataEnricher(this.chatModel, 2);
        return enricher.apply(documents);
    }
	
}
