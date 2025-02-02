package com.vaddi.multi_llm_models_ai_spring_demo;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeminiChatController {
	
	private final ChatClient chatClient;
	
	public GeminiChatController(@Qualifier("geminiAIChatClient") ChatClient chatClient) {
		this.chatClient = chatClient;
	}
	
	@GetMapping("/gemini")
	public String chatPrompt() {
		
		return chatClient
				.prompt("Summarize gemini api and benefits")
				.call()
				.content();
	}
}