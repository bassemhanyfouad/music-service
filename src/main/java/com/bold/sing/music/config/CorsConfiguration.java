package com.bold.sing.music.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		//TODO Add PROD Cors-Settings, don't allow everything
		registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods("*")
				.allowCredentials(true);
	}


}
