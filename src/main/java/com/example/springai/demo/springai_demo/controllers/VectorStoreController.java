package com.example.springai.demo.springai_demo.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.filter.Filter;
import org.springframework.ai.vectorstore.filter.Filter.Expression;
import org.springframework.ai.vectorstore.filter.FilterExpressionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/vector-store")
public class VectorStoreController {

	@Autowired
	private VectorStore vectorStore;

	@PostMapping("/load")
	public void load(String sourceFile) {
		Document document1 = new Document("Tendencia tecnologica 2025: La voz y los vídeos en la IA",
				Map.of("tendencia", "ia"));
		Document document2 = new Document(
				"Tendencia tecnologica 2025: Accesibilidad: desarrollando aplicaciones inclusivas",
				Map.of("tendencia", "accesibilidad"));
		Document document3 = new Document(
				"Tendencia tecnologica 2025: De Digital Native a AI Native: la nueva era de la IA en los negocios",
				Map.of("tendencia", "native"));
		Document document4 = new Document(
				"Tendencia tecnologica 2025: GreenOps: liderando el cambio hacia un futuro sostenible",
				Map.of("tendencia", "green"));
		Document document5 = new Document("Tendencia tecnologica 2025: Transformación impulsada por evidencias",
				Map.of("tendencia", "transformacion"));
		Document document6 = new Document("Tendencia tecnologica 2025: Liderazgo Sistémico Humanista",
				Map.of("tendencia", "humanista"));
		Document document7 = new Document("Tendencia tecnologica 2025: Industry Cloud Platforms",
				Map.of("tendencia", "cloud"));
		Document document8 = new Document("Tendencia tecnologica 2025: Agentic AI", Map.of("tendencia", "agentic"));
		Document document9 = new Document("Tendencia tecnologica 2025: WebAssembly (WASM)",
				Map.of("tendencia", "webassembly"));
		Document document10 = new Document("Tendencia tecnologica 2025: Soluciones Drag and Drop para IA generativa",
				Map.of("tendencia", "dragdrop"));

		List<Document> documents = Arrays.asList(document1, document2, document3, document4, document5, document6,
				document7, document8, document9, document10);
		this.vectorStore.add(documents);
	}

	@GetMapping("/similar-search")
	public List<Document> similarSearch() {
		SearchRequest request = SearchRequest.builder().query("Dime las tendencias tecnologicas actuales").topK(10)
				.build();
		return vectorStore.similaritySearch(request);
//		return vectorStore.similaritySearch("Dime las tendencias tecnologicas actuales");
	}

	@DeleteMapping("/delete-filtered")
	public List<Document> deleteDocumentsFiltered() {
		Filter.Expression filterExpression = new Filter.Expression(Filter.ExpressionType.EQ,
				new Filter.Key("tendencia"), new Filter.Value("dragdrop"));

		FilterExpressionBuilder builder = new FilterExpressionBuilder();
		Expression expression = builder.eq("tendencia", "cloud").build();
		try {
			vectorStore.delete(filterExpression);
			vectorStore.delete(expression);
		} catch (Exception e) {
			log.error("Invalid filter expression", e);
		}

		SearchRequest request = SearchRequest.builder().query("Dime las tendencias tecnologicas actuales").topK(10)
				.build();
		return vectorStore.similaritySearch(request);
	}

}
