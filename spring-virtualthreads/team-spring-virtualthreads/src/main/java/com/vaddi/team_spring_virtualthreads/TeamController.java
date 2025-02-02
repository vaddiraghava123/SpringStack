package com.vaddi.team_spring_virtualthreads;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class TeamController {
	
	
	private RestClient restClient;
	
	public TeamController(RestClient.Builder builder) {
		this.restClient = builder.baseUrl("http://localhost:8081")
		.build();
	}
	
	@GetMapping("/")
	public List<String> getPlayersForTeam() {
		log.info("Running on {}", Thread.currentThread());
		return restClient.get()
				.uri("/players")
				.retrieve()
				.toEntity(List.class)
				.getBody();
	}
}
