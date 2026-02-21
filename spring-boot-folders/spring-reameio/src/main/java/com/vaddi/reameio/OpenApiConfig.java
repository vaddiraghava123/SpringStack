package com.vaddi.reameio;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Hello REST API",
                version = "1.0",
                description = "Sample Hello REST Service"
        )
)
public class OpenApiConfig {
}
