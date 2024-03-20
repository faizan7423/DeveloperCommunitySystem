package com.dcs.swagger;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class Swagger {
	// Configures a grouped OpenAPI for public APIs
	@Bean
	public GroupedOpenApi publicApi() {
		return GroupedOpenApi.builder().group("public-apis").pathsToMatch("/**").build();
	}
	// Configures the global OpenAPI documentation for the entire application
	@Bean
    public OpenAPI apiDocConfig() {
        return new OpenAPI()
                .info(new Info());
    }

}