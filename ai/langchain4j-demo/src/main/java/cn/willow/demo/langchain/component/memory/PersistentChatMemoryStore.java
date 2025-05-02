package cn.willow.demo.langchain.component.memory;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.ChatMessageDeserializer;
import dev.langchain4j.data.message.ChatMessageSerializer;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import jakarta.annotation.PreDestroy;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author willow
 * @since 2025/3/9
 */
@Component("chatMemoryStore")
public class PersistentChatMemoryStore implements ChatMemoryStore {
    private final DB db = DBMaker.fileDB("chat-memory.db").transactionEnable().fileLockDisable().make();
    private final Map<String, String> map = db.hashMap("messages", Serializer.STRING, Serializer.STRING).createOrOpen();
    @PreDestroy
    public void destroy() {
        System.out.println("destroy");
        db.close();
    }
    @Override
    public List<ChatMessage> getMessages(Object memoryId) {
        String json = map.get(Objects.toString(memoryId));
        List<ChatMessage> chatMessages = ChatMessageDeserializer.messagesFromJson(json);
        System.out.println("getMessages.memoryId:" + memoryId + ",json:" + json);
        return chatMessages;
    }

    /**
     * The updateMessages() method is called every time a new ChatMessage is added to the ChatMemory
     * @param memoryId The ID of the chat memory.
     * @param messages List of messages for the specified chat memory, that represent the current state of the {@link dev.langchain4j.memory.ChatMemory}.
     *                 Can be serialized to JSON using {@link dev.langchain4j.data.message.ChatMessageSerializer}.
     */
    @Override
    public void updateMessages(Object memoryId, List<ChatMessage> messages) {
        String json = ChatMessageSerializer.messagesToJson(messages);
        map.put(Objects.toString(memoryId), json);
        db.commit();
        //打印入参和输出
        System.out.println("updateMessages.memoryId:" + memoryId + ",json:" + json);
    }

    @Override
    public void deleteMessages(Object memoryId) {
        map.remove(Objects.toString(memoryId));
        db.commit();
        System.out.println("deleteMessages.memoryId:" + memoryId);
    }
}
