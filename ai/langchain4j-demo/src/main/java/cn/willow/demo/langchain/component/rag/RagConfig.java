package cn.willow.demo.langchain.component.rag;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author willow
 * @since 2025/3/18
 */
@Configuration
public class RagConfig {
    /**
     * 向量存储检索器
     * @param customEmbeddingStore 向量存储
     * @return
     */
    @Bean
    public EmbeddingStoreContentRetriever customEmbeddingStoreContentRetriever(@Autowired EmbeddingStore<TextSegment> customEmbeddingStore){
        return EmbeddingStoreContentRetriever.from(customEmbeddingStore);
    }

    /**
     * 向量存储
     * @return
     */
    @Bean
    public EmbeddingStore<TextSegment> customEmbeddingStore(){
        InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();
        return embeddingStore;
    }
}
