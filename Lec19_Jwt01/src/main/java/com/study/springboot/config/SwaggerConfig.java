package com.study.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@OpenAPIDefinition
public class SwaggerConfig {
	
	@Bean
	public OpenAPI api() {
		Info info = new Info().title("Restaurant info").version("v3").description("맛집정보");
		return new OpenAPI().components(new Components()).info(info);
	}
}
