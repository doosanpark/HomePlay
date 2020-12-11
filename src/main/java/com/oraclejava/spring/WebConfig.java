package com.oraclejava.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
	//빈 상태로 둬도 괜찮음.
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		registry.addResourceHandler("/webjars/**")
		.addResourceLocations("/webjars/");
		registry.addResourceHandler("/**")
		.addResourceLocations("classpath:/static/");
		
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}
}