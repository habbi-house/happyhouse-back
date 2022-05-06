package com.ssafy.web;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;
import com.ssafy.web.interceptor.AuthorizationInterceptor;
import com.ssafy.web.interceptor.LoginInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		// 로그인 되어 있는 사용자만 허용
		registry.addInterceptor(new LoginInterceptor())
				.addPathPatterns("/user/**")
				.addPathPatterns("/board/create")
				.addPathPatterns("/board/**/update")
				.addPathPatterns("/board/**/delete")
				.excludePathPatterns("/user/login")
				.excludePathPatterns("/user/signIn");
		
		// 사용자가 접근 가능한 자원인지에 대한 권한 체크
		registry.addInterceptor(new AuthorizationInterceptor())
			.addPathPatterns("/user/**")
			.excludePathPatterns("/user/login")
			.excludePathPatterns("/user/signIn");
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
