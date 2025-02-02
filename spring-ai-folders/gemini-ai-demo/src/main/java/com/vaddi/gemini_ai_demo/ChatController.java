package com.vaddi.gemini_ai_demo;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {
	
	private final ChatClient chatClient;
	
	public ChatController(ChatClient.Builder builder) {
		this.chatClient = builder.build();
	}
	
	@GetMapping
	public String chatPrompt(@RequestParam String message) {
		
		return chatClient
				.prompt(message)
				.call()
				.content();
	}
}