package com.vaddi.spring_deepseek_ai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LLMConfig {

	@Bean
	public ChatClient openAIChatClient(OpenAiChatModel chatModel) {
		return ChatClient.create(chatModel);
	}
	
	@Bean
	public ChatClient ollamaLocalChatModel(OllamaChatModel chatModel) {
		return ChatClient.create(chatModel);
	}
}
