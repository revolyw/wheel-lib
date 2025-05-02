package cn.willow.demo.langchain.component.llm;

import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author willow
 * @since 2025/3/7
 */
@Configuration
public class LLMConfiguration  {
    private static final String API_KEY = System.getProperty("DASH_SCOPE_API_KEY");
    private static final String MODEL_NAME = "qwen-plus";
    @Bean
    public ChatLanguageModel qwenChatModel() {
        return QwenChatModel.builder()
                .apiKey(API_KEY)
                .modelName(MODEL_NAME)
                .build();
    }

    @Bean
    public ChatMemoryProvider chatMemoryProvider(@Autowired ChatMemoryStore chatMemoryStore) {
        return memoryId -> MessageWindowChatMemory.builder()
                .id(memoryId)
                .maxMessages(20)
                .chatMemoryStore(chatMemoryStore)
                .build();
    }
}
