package com.vaddi.spring_rag_ollama_starter;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class QueryRequest {

	private String query;
	private String conversationId;
}
