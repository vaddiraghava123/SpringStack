package com.vaddi.spring_rag_ollama_starter;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class ChatResponse {

	private String responseId;
	private String response;
	
}
