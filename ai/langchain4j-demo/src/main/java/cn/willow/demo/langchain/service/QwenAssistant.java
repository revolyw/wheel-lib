package cn.willow.demo.langchain.service;

import dev.langchain4j.service.spring.AiService;

import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;

@AiService(wiringMode = EXPLICIT, chatModel = "qwenChatModel", tools = "functionCallingTools",
        chatMemoryProvider = "chatMemoryProvider", contentRetriever = "customEmbeddingStoreContentRetriever")
public interface QwenAssistant extends Assistant {
}