package cn.willow.demo.mcp.client;

import io.modelcontextprotocol.client.McpSyncClient;
import io.modelcontextprotocol.spec.McpSchema;
import org.springframework.ai.mcp.SyncMcpToolCallback;
import org.springframework.ai.model.ModelOptionsUtils;
import org.springframework.ai.tool.definition.DefaultToolDefinition;
import org.springframework.ai.tool.execution.ToolExecutionException;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

/**
 * @author willow
 * @since 2025/5/2
 */
public class CustomSyncMcpToolCallback extends SyncMcpToolCallback {
    private McpSyncClient mcpClient;
    private McpSchema.Tool tool;

    public CustomSyncMcpToolCallback(McpSyncClient mcpClient, McpSchema.Tool tool) {
        super(mcpClient, tool);
        this.mcpClient = mcpClient;
        this.tool = tool;
    }

    @Override
    public String call(String functionInput) {
        Map<String, Object> arguments = ModelOptionsUtils.jsonToMap(functionInput);
        McpSchema.CallToolResult response = this.mcpClient.callTool(new McpSchema.CallToolRequest(this.tool.name(), arguments));
        if (response.isError() != null && response.isError()) {
            throw new ToolExecutionException(new DefaultToolDefinition(tool.name(), tool.description(), Objects.toString(tool.inputSchema())), new IllegalStateException("Error calling tool: " + response.content()));
        }
        System.out.println("Brave Search API response: " + response.content()); // 添加日志记录
        return ModelOptionsUtils.toJsonString(response.content());
    }
}
