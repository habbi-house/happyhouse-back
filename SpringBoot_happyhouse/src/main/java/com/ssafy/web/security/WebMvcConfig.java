package com.ssafy.web.security;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;
import com.ssafy.web.interceptor.AuthenticationInterceptor;
import com.ssafy.web.interceptor.AuthorizationInterceptor;
import com.ssafy.web.interceptor.CSRFInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	private static final String[] EXCLUDE_PATHS = {
	        "/user/signUp",
	        "/user/login",
	        "/user/kakao",
//	        "/board/**",
//	        "/search/**",
	        "/error"
	    };
	 
//	    @Autowired
//	    private JwtInterceptor jwtInterceptor;
//	 
//	    @Override
//	    public void addInterceptors(InterceptorRegistry registry) {
//	        registry.addInterceptor(jwtInterceptor)
//	                        .addPathPatterns("/**")
//	                        .excludePathPatterns(EXCLUDE_PATHS);
//	    }
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		// 로그인 되어 있는 사용자만 허용
		registry.addInterceptor(new AuthenticationInterceptor())
				.addPathPatterns("/user/**")
				.addPathPatterns("/board/create")
				.addPathPatterns("/board/**/update")
				.addPathPatterns("/board/**/delete")
				.excludePathPatterns("/user/login")
				.excludePathPatterns("/user/signUp")
				.excludePathPatterns("/user/kakao");
		
		// 사용자가 접근 가능한 자원인지에 대한 권한 체크
		registry.addInterceptor(new AuthorizationInterceptor())
			.addPathPatterns("/user/**")
			.excludePathPatterns("/user/login")
			.excludePathPatterns("/user/signUp")
			.excludePathPatterns("/user/logout")
			.excludePathPatterns("/user/kakao");
		
		// CSRF 토큰 검증
		registry.addInterceptor(new CSRFInterceptor())
		.addPathPatterns("/board/create")
		.addPathPatterns("/board/**/update")
		.addPathPatterns("/board/**/delete");
		
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
