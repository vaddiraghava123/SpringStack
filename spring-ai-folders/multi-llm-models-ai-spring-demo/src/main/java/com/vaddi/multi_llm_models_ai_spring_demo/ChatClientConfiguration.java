package com.vaddi.multi_llm_models_ai_spring_demo;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.vertexai.gemini.VertexAiGeminiChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfiguration {
	
	@Bean
	public ChatClient openAIChatClient(OpenAiChatModel chatModel) {
		return ChatClient.create(chatModel);
	}
	
	@Bean
	public ChatClient geminiAIChatClient(VertexAiGeminiChatModel chatModel) {
		return ChatClient.create(chatModel);
	}


}
