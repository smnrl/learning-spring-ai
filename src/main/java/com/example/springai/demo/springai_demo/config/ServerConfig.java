package com.example.springai.demo.springai_demo.config;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import io.modelcontextprotocol.server.McpServerFeatures;
import io.modelcontextprotocol.server.McpSyncServerExchange;
import io.modelcontextprotocol.spec.McpSchema;
import io.modelcontextprotocol.spec.McpSchema.Annotations;
import io.modelcontextprotocol.spec.McpSchema.GetPromptResult;
import io.modelcontextprotocol.spec.McpSchema.PromptMessage;
import io.modelcontextprotocol.spec.McpSchema.Role;
import io.modelcontextprotocol.spec.McpSchema.TextContent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class ServerConfig {

	@Bean
	ToolCallbackProvider userToolProvider(UserTools userTools) {
		return MethodToolCallbackProvider.builder().toolObjects(userTools).build();
	}

	@Bean
	List<McpServerFeatures.SyncResourceSpecification> myResources(
			@Value("classpath:/static/trends.txt") Resource resource) {
		var systemInfoResource = new McpSchema.Resource("file://trends.txt", "Trends",
				"Fichero con 3 tendencias tecnologicas del año 2025", "text/plain",
				new Annotations(Arrays.asList(Role.USER), Double.valueOf("0")));
		var resourceSpecification = new McpServerFeatures.SyncResourceSpecification(systemInfoResource,
				(exchange, request) -> {
					try {
						String jsonContent = resource.getContentAsString(StandardCharsets.UTF_8);
						return new McpSchema.ReadResourceResult(
								List.of(new McpSchema.TextResourceContents(request.uri(), "text/plain", jsonContent)));
					} catch (Exception e) {
						throw new RuntimeException("Failed to generate system info", e);
					}
				});
		return List.of(resourceSpecification);
	}

	@Bean
	List<McpServerFeatures.SyncPromptSpecification> myPrompts() {
		var prompt = new McpSchema.Prompt("search-user-by-name", "Prompt to search a user by name",
				List.of(new McpSchema.PromptArgument("name", "The user's name", true)));

		var promptSpecification = new McpServerFeatures.SyncPromptSpecification(prompt,
				(exchange, getPromptRequest) -> {
					String nameArgument = (String) getPromptRequest.arguments().get("name");
					if (nameArgument == null) {
						nameArgument = "friend";
					}
					var userMessage = new PromptMessage(Role.USER,
							new TextContent("Existe el usuario " + nameArgument + "?"));
					return new GetPromptResult("Prompt to search a user by name", List.of(userMessage));
				});
		return List.of(promptSpecification);
	}

	@Bean
	BiConsumer<McpSyncServerExchange, List<McpSchema.Root>> rootsChangeHandler() {
		return (exchange, roots) -> {
			log.info("Cambio en los roots recibido: {}", roots);
		};
	}
}
