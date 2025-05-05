package com.example.springai.demo.springai_demo.application.transformers;

import java.util.List;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.model.transformer.SummaryMetadataEnricher;
import org.springframework.ai.model.transformer.SummaryMetadataEnricher.SummaryType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SummaryMetadataConfig {

    @Bean
    SummaryMetadataEnricher summaryMetadata(ChatModel chatmodel) {
        return new SummaryMetadataEnricher(chatmodel,
            List.of(SummaryType.PREVIOUS, SummaryType.CURRENT, SummaryType.NEXT));
    }
	
}
