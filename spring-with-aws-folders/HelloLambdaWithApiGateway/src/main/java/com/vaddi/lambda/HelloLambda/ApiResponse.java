package com.vaddi.lambda.HelloLambda;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {

	private Integer statusCode;
	private String body;
	
	private Map<String,String> headers;
}
