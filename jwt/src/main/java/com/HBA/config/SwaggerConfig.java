package com.HBA.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI customOpenAPI() {
	    return new OpenAPI()
	            .components(new Components().addSecuritySchemes("bearerAuth",
	                    new SecurityScheme()
	                            .type(SecurityScheme.Type.HTTP)
	                            .scheme("bearer")
	                            .bearerFormat("JWT")
	                            .description("JWT token değerini giriniz.")))
	            .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
	            .info(new Info().title("Swagger Title").description("Swagger Tutorial"));
	}

}
