package com.example.springai.demo.springai_demo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.modelcontextprotocol.client.McpSyncClient;
import io.modelcontextprotocol.spec.McpError;
import io.modelcontextprotocol.spec.McpSchema.GetPromptRequest;
import io.modelcontextprotocol.spec.McpSchema.GetPromptResult;
import io.modelcontextprotocol.spec.McpSchema.ListPromptsResult;
import io.modelcontextprotocol.spec.McpSchema.ListResourcesResult;
import io.modelcontextprotocol.spec.McpSchema.ListToolsResult;
import io.modelcontextprotocol.spec.McpSchema.ReadResourceRequest;
import io.modelcontextprotocol.spec.McpSchema.ReadResourceResult;
import io.modelcontextprotocol.spec.McpSchema.Root;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class McpController {

	private ChatClient chatClient;
	
	private McpSyncClient mcpSyncClient;
	
	public McpController(ChatClient.Builder builder, ToolCallbackProvider tools, List<McpSyncClient> mcpSyncClients) {
		this.mcpSyncClient = mcpSyncClients
				.stream()
				.filter( client -> "mcp-server-demo".equals(client.getServerInfo().name()))
				.findFirst()
				.orElse(null);
		
		var tool = new SyncMcpToolCallbackProvider(mcpSyncClients);
        this.chatClient = builder
        		.defaultToolCallbacks(tool)
        		.build();
    }

	@GetMapping("/list-files")
	public String listFiles() {
		return chatClient.prompt()
				.user("Dime que ficheros tengo en la carpeta /projects/test")
				.call()
				.content();
	}
	
	@GetMapping("/list-users")
	public String listUsers() {
		return chatClient.prompt()
				.user("Dime que usuarios hay")
				.call()
				.content();
	}
	
	@GetMapping("/tools")
	public ListToolsResult getTools() {
		if(mcpSyncClient != null) {
			return mcpSyncClient.listTools();
		} else {
			return null;
		}
	}
	
	@GetMapping("/resources")
	public ListResourcesResult getResources() {
		if(mcpSyncClient != null) {
			return mcpSyncClient.listResources();
		} else {
			return null;
		}
	}
	
	@GetMapping("/resource")
	public ReadResourceResult getResource() {
		if(mcpSyncClient != null) {
			ReadResourceRequest resourceReq = new ReadResourceRequest("file://trends.txt");
			return mcpSyncClient.readResource(resourceReq);
		} else {
			return null;
		}
	}
	
	
	@GetMapping("/prompts")
	public ListPromptsResult getPrompts() {
		if(mcpSyncClient != null) {
			return mcpSyncClient.listPrompts();
		} else {
			return null;
		}
	}
	
	@GetMapping("/prompt")
	public GetPromptResult getPrompt() {
		if(mcpSyncClient != null) {
			Map<String, Object> promptParams = new HashMap<>();
			promptParams.put("name", "Juan");
			GetPromptRequest promptRequest = new GetPromptRequest("search-user-by-name", promptParams);
			return mcpSyncClient.getPrompt(promptRequest);
		} else {
			return null;
		}
	}
	
	@PostMapping("/root")
	public void addRoot() {
		if(mcpSyncClient != null) {
			try {
				mcpSyncClient.addRoot(new Root(System.getProperty("user.home") + "/springai", "Spring AI foler"));
//				mcpSyncClient.rootsListChangedNotification();
			} catch (McpError e) {
				log.error("MCPError {}", e.getMessage());
			}
		}
	}
	
}
