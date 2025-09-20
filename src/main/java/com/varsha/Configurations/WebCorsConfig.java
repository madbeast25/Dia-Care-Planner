package com.varsha.Configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebCorsConfig implements WebMvcConfigurer {
	
	@Value("${FRONT_END_URL}")
	private String url;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		               .allowedHeaders("*")
				       .allowedOrigins(url)
				       .allowCredentials(true)
				       .allowedMethods("GET","POST","PUT","PATCH","DELETE","OPTIONS")
				       .maxAge(3600);
	}

	
}
