package com.ssafy.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;
import com.ssafy.web.interceptor.JwtInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	private static final String[] EXCLUDE_PATHS = {
	        "/user/login",
	        "/user/signup",
	        "/user/kakao",
	        "/user/logout",
	        "/user/refresh",
	        "/board",
	        "/board/list",
	        "/board/{id}",
	        "/search/**",
	        "/s3/upload",
	        "/s3/delete",
	        "/error"
	    };
	 
	    @Autowired
	    private JwtInterceptor jwtInterceptor;
	 
	    @Override
	    public void addInterceptors(InterceptorRegistry registry) {
	        registry.addInterceptor(jwtInterceptor)
	                        .addPathPatterns("/**")
	                        .excludePathPatterns(EXCLUDE_PATHS);
	    }
	    
	    @Override
		public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**")
			.allowedOrigins("http://localhost:8080")
			.allowedHeaders("*")
			.allowedMethods("*")
			.allowCredentials(true);
	}
	
	@Bean
	public FilterRegistrationBean<XssEscapeServletFilter> filterRegistrationBean() {
		FilterRegistrationBean<XssEscapeServletFilter> filterRegistration = new FilterRegistrationBean<>();
		filterRegistration.setFilter(new XssEscapeServletFilter());
		filterRegistration.setOrder(1);
		filterRegistration.addUrlPatterns("/*");
		return filterRegistration;
	}
}
