package com.vaddi.aws.springbootUploadIntoAWS.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/hello")
public class HelloSpringController {

	@Value("${message}")
	private String message;
	
	@GetMapping
	public String getWelcomeMessage() {
		return "Welcome Spring Boot application " + message;
	}
}
