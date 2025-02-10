package com.vaddi.readproperties.spring_retrieve_properties;

import org.springframework.beans.factory.annotation.Value;
/**
 * Retrieve the values from application.properties file
 */
import org.springframework.stereotype.Component;

@Component
public class RetrieveProperties {
	
	@Value("${app.version}")
	public String version;
	
	@Value("${app.welcome.message}")
	public String welcomeMessage;
	
	public String getMessage() {
		return "Version :" + version + " : message is : "+ welcomeMessage;
	}

}
