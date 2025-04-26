
package com.vaddi.rag.ollama;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {

    private final ChatModel chatModel;

    public ChatClientConfig(ChatModel chatModel) {
        this.chatModel = chatModel;
    }
    
    @Bean
    public ChatClient chatClient() {
    	
    	OllamaOptions options = createOllamaOptions("llama3.2:1b");
    	ChatOptions chatOptions1 = options;
    	
        return ChatClient.builder(chatModel).defaultOptions(chatOptions1).build();
    }
    
	private OllamaOptions createOllamaOptions(String string) {
		 return new OllamaOptions.Builder()
	                .model(string) 
	                .build();
	}
}
