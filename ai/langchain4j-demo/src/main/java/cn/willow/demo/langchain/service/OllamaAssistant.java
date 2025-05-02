package cn.willow.demo.langchain.service;

import dev.langchain4j.service.spring.AiService;

import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;

//@AiService(wiringMode = EXPLICIT, chatModel = "ollamaChatModel", tools = "functionCallingTools",
//        chatMemoryProvider = "chatMemoryProvider")
//public interface OllamaAssistant extends Assistant {
//}