package cn.willow.demo.mcp.client;

import io.modelcontextprotocol.client.McpSyncClient;
import io.modelcontextprotocol.spec.McpSchema;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class DemoMcpClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoMcpClientApplication.class, args);
    }

    @Bean
    public CommandLineRunner chatbot(ChatClient.Builder chatClientBuilder, List<McpSyncClient> mcpSyncClients) {

        return args -> {
            SyncMcpToolCallbackProvider syncMcpToolCallbackProvider = new SyncMcpToolCallbackProvider(mcpSyncClients){
                @Override
                public ToolCallback[] getToolCallbacks() {
                    List<ToolCallback> toolCallbacks = new ArrayList<>();
                    for (McpSyncClient mcpClient : mcpSyncClients) {
                        for (McpSchema.Tool tool : mcpClient.listTools().tools()) {
                            System.out.println("registered tool: " + tool.name());
                            toolCallbacks.add(new CustomSyncMcpToolCallback(mcpClient, tool));
                        }
                    }
                    return toolCallbacks.toArray(new ToolCallback[0]);
                }
            };
            var chatClient = chatClientBuilder
                    .defaultSystem("You are useful assistant and can perform web searches Brave's search API to reply to your questions.")
                    .defaultTools(syncMcpToolCallbackProvider)
                    .defaultAdvisors(new MessageChatMemoryAdvisor(new InMemoryChatMemory()))
                    .build();
            // Start the chat loop
            System.out.println("\nI am your AI assistant.\n");
            try (Scanner scanner = new Scanner(System.in)) {
                while (true) {
                    System.out.print("\nUSER: ");
                    System.out.println("\nASSISTANT: " +
                            chatClient.prompt(scanner.nextLine()) // Get the user input
                                    .call()
                                    .content());
                }
            }

        };
    }
}
