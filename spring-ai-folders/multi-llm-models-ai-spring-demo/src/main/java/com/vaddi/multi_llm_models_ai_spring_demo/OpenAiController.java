package com.vaddi.multi_llm_models_ai_spring_demo;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.PromptChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenAiController {

private final ChatClient chatClient;
	
	public OpenAiController(@Qualifier("openAIChatClient") ChatClient chatClient) {
		this.chatClient = chatClient;
	}
	
	@GetMapping("/openai")
	public String chatPrompt() {
		
		return chatClient
				.prompt()
				.user("Summarize open AI and benefits")
				.call()
				.content();
	}
}
