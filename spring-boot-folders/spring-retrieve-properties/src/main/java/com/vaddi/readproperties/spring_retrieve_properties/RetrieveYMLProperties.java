package com.vaddi.readproperties.spring_retrieve_properties;
/**
 * Retrieve yml properties for structured and complex hierarchical data
 */
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "app")
@Data
@Setter
@Getter
public class RetrieveYMLProperties {

	private String name;
	private String version;
	private Details details;

	public static class Details {
		private String owner;
		private String email;

		// Getters and Setters
		public String getOwner() { return owner; }
		public void setOwner(String owner) { this.owner = owner; }

		public String getEmail() { return email; }
		public void setEmail(String email) { this.email = email; }
	}	
}
