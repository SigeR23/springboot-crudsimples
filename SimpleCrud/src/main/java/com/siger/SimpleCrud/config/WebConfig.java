package com.siger.SimpleCrud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.siger.SimpleCrud.thymeleaf.SimpleCrudDialect;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Bean
	public SimpleCrudDialect simpleCrudDialect() {
		return new SimpleCrudDialect();
	}
}
