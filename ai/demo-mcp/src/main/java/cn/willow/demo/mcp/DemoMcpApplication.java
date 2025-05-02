package cn.willow.demo.mcp;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoMcpApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoMcpApplication.class, args);
    }

    // MethodToolCallbackProvider utils
    // 将 @Tools 转换为 MCP server 使用的可操作 callbacks。
    @Bean
    public ToolCallbackProvider weatherTools(WeatherService weatherService) {
        return  MethodToolCallbackProvider.builder()
                .toolObjects(weatherService)
                .build();
    }
}
