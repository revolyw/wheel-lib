package cn.willow.demo.langchain.service;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
public interface Assistant {
    @SystemMessage("You are a polite assistant")
    Response<AiMessage> chat(@UserMessage String userMessage);

    @SystemMessage("You are a polite assistant")
    Response<AiMessage> chat(@MemoryId int memoryId, @UserMessage String userMessage);

    @SystemMessage("You are a polite assistant")
    Response<AiMessage> chat(@UserMessage dev.langchain4j.data.message.UserMessage userMessage);
}