package com.vaddi.spring_hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping
public class SpringHelloApplication {
	
	@GetMapping("/hello/{name}")
	public String welcomeMessage(@PathVariable String name) {
		return "Hello "+ name + " :Welcome to CI/CD using Jenkins , Docker , K8 ";
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringHelloApplication.class, args);
	}

}
