package com.vaddi.openchat_ai_spring_demo;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.PromptChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenAiController {

private final ChatClient chatClient;
	
	public OpenAiController(ChatClient.Builder builder) {
		this.chatClient = builder
				//.defaultAdvisors(new MessageChatMemoryAdvisor(new InMemoryChatMemory()))
				.defaultAdvisors(new PromptChatMemoryAdvisor(new InMemoryChatMemory()))
				.build();
	}
	
	@GetMapping
	public String chatPrompt(@RequestParam String message) {
		
		return chatClient
				.prompt()
				.user(message)
				.call()
				.content();
	}
}
