package com.ssafy.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
		
		// 접근 권한을 체크
		registry.addInterceptor(new AuthorizationInterceptor())
			.addPathPatterns("/user/**")
			.addPathPatterns("/board/**/update")
			.addPathPatterns("/board/**/delete")
			.excludePathPatterns("/user/login")
			.excludePathPatterns("/user/signIn");
	}
}
