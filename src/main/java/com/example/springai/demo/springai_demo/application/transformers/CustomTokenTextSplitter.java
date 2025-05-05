package com.example.springai.demo.springai_demo.application.transformers;

import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.stereotype.Component;

@Component
public class CustomTokenTextSplitter {

	public List<Document> splitCustomized(List<Document> documents) {
        TokenTextSplitter splitter = new TokenTextSplitter(10, 5, 2, 15, true);
        return splitter.apply(documents);
    }
	
}
