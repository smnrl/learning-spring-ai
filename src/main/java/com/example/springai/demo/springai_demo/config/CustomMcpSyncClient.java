package com.example.springai.demo.springai_demo.config;

import java.time.Duration;
import java.util.Arrays;

import org.springframework.ai.mcp.customizer.McpSyncClientCustomizer;
import org.springframework.stereotype.Component;

import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.spec.McpSchema.ClientCapabilities;
import io.modelcontextprotocol.spec.McpSchema.ClientCapabilities.RootCapabilities;
import io.modelcontextprotocol.spec.McpSchema.Root;

@Component
public class CustomMcpSyncClient implements McpSyncClientCustomizer {
	
    @Override
    public void customize(String serverConfigurationName, McpClient.SyncSpec spec) {

        // Customize the request timeout configuration
        spec.requestTimeout(Duration.ofSeconds(30));
        
        RootCapabilities rootCapabilities = new RootCapabilities(true);
        spec.capabilities(new ClientCapabilities(null, rootCapabilities, null));
        // Sets the root URIs that this client can access.
        spec.roots(Arrays.asList(new Root(System.getProperty("user.home") + "/out", "Out foler")));

    }
}
