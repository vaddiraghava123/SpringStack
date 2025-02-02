package com.vaddi.ollama_ai_local_demo;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OllamaChatController {

	private final ChatModel chatModel;
	
	public OllamaChatController(ChatModel chatModel) {
		this.chatModel = chatModel;
	}
	
	@GetMapping("/")
	public String chatPrompt(@RequestParam String message) {
		
		return  chatModel.call(message);
	}
	
}
