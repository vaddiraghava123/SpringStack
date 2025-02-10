package com.vaddi.readproperties.spring_retrieve_properties;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PropertiesController {
	
	//retrieve values from application.properties file - method1
	@Value("${app.welcome.message}")
	private String welcomeMessage = null;
	
	
	
	//retrieve values from application.properties file - method2
	private final RetrieveProperties retreiveProperties ;
	
	//Retrieve yml properties for structured and complex hierarchical data using @configurationProperties(prefix='app')
	private final RetrieveYMLProperties retrieveYMLProperties ;
	
	//For large YAML files, flexible for dynamic retrieval.
	@Autowired
	Environment environment;

	public PropertiesController(RetrieveProperties retreiveProperties,
			RetrieveYMLProperties retrieveYMLProperties,
			Environment environment) {
		this.retreiveProperties = retreiveProperties;
		this.retrieveYMLProperties = retrieveYMLProperties;
		this.environment = environment;
	}
	

	@GetMapping("/prop/config")
	public String getConfig() {
		//retrieve values from application.properties file - method1
		System.out.println("Welcome message is :: "+ welcomeMessage);
		
		//retrieve values from application.properties file - method2
		return retreiveProperties.getMessage();
	}

	@GetMapping("/yml/config")
	public String getConfigYML() {
		return retrieveYMLProperties.getDetails().getEmail();
	}
	
	@GetMapping("/dynamic/yml/config")
	public String getComplexYML() {
		String owner = environment.getProperty("app.details.owner");
		String ports = environment.getProperty("app.endpoints");
		List<String> portValues = ports != null ? Arrays.asList(ports.split(",")):List.of();
		
		return "Values are:" + owner +" :: "+ portValues;
	}

}

