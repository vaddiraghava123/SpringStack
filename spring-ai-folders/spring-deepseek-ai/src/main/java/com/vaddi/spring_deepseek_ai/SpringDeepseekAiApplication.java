package com.vaddi.spring_deepseek_ai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.vaddi.spring_deepseek_ai")
public class SpringDeepseekAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDeepseekAiApplication.class, args);
	}

}
