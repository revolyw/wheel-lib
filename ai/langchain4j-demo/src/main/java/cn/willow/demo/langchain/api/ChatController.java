package cn.willow.demo.langchain.api;

import cn.willow.demo.langchain.service.Assistant;
import com.google.common.collect.Lists;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author willow
 * @since 2025/3/5
 */
@RestController
public class ChatController {
    private Assistant assistant;
    @Autowired
    public ChatController(Assistant qwenAssistant, Assistant ollamaAssistant) {
        this.assistant = "prd".equals(System.getProperty("spring.profiles.active")) ? qwenAssistant : ollamaAssistant;
    }

    @Autowired
    private EmbeddingStore<TextSegment> customEmbeddingStore;

    @GetMapping("/chat")
    public String model(@RequestParam(value = "memoryId", defaultValue = "0") int memoryId,
                        @RequestParam(value = "message", defaultValue = "Hello") String message) {
        Response<AiMessage> response = assistant.chat(memoryId, message);

        return response.content().text();
    }

    @GetMapping("/chat-with-pdf")
    public String loadWithPdf(@RequestParam(value = "message", defaultValue = "Hello") String message) {
        // 加载PDF文件至内存中
        List<Document> documents = FileSystemDocumentLoader.loadDocuments("/Users/willow/Downloads/rag");
        EmbeddingStoreIngestor.ingest(documents, customEmbeddingStore);
        Response<AiMessage> chat = assistant.chat(message);
        return chat.content().text();
    }
}
