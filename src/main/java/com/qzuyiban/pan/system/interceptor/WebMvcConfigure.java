package com.qzuyiban.pan.system.interceptor;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;



@Configuration
public class WebMvcConfigure extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {		

		registry.addInterceptor(new InterceptorConfig()).addPathPatterns("/**").excludePathPatterns("/test1", "/login.html");		

	}
}
