package com.example.springai.demo.springai_demo.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.ai.document.Document;

public class DocumentUtils {

	private static List<Document> documents = Arrays.asList(
			new Document("Tendencia tecnologica 2025: La voz y los vídeos en la IA", Map.of("tendencia", "ia")),
			new Document("Tendencia tecnologica 2025: Accesibilidad: desarrollando aplicaciones inclusivas",
					Map.of("tendencia", "accesibilidad")),
			new Document(
					"Tendencia tecnologica 2025: De Digital Native a AI Native: la nueva era de la IA en los negocios",
					Map.of("tendencia", "native")),
			new Document("Tendencia tecnologica 2025: GreenOps: liderando el cambio hacia un futuro sostenible",
					Map.of("tendencia", "green")),
			new Document("Tendencia tecnologica 2025: Transformación impulsada por evidencias",
					Map.of("tendencia", "transformacion")),
			new Document("Tendencia tecnologica 2025: Liderazgo Sistémico Humanista", Map.of("tendencia", "humanista")),
			new Document("Tendencia tecnologica 2025: Industry Cloud Platforms", Map.of("tendencia", "cloud")),
			new Document("Tendencia tecnologica 2025: Agentic AI", Map.of("tendencia", "agentic")),
			new Document("Tendencia tecnologica 2025: WebAssembly (WASM)", Map.of("tendencia", "webassembly")),
			new Document("Tendencia tecnologica 2025: Soluciones Drag and Drop para IA generativa",
					Map.of("tendencia", "dragdrop")));
	
	public static List<Document> getDocuments() {
		return documents;
	}

}
