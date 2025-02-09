package com.vaddi.spring_deepseek_ai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OllamaLocalController {

private final ChatClient chatClient;
	
	public OllamaLocalController(@Qualifier("ollamaLocalChatModel")ChatClient chatClient) {
		this.chatClient = chatClient;
	}
	
	@GetMapping("/deepseek/local/{m}")
	public String chatPrompt(@PathVariable String m) {
		
		return chatClient
				.prompt()
				.user(m)
				.call()
				.content();
	}
}
