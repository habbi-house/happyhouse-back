package com.ssafy.web.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ssafy.web.service.JwtService;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtInterceptor implements HandlerInterceptor {
	private static final String HEADER_AUTH = "Authorization";

	@Autowired
	private JwtService jwtService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("인터셉팅!!!!");
		System.out.println(request.getRequestURI());
		// axios의 Preflight OPTION요청을 거르기 위함
		if (HttpMethod.OPTIONS.matches(request.getMethod())) {
			return true;
		}

		String bearer = request.getHeader(HEADER_AUTH);
		String accessToken = null;
		String refreshToken = null;
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if("refreshToken".equals(cookie.getName())){
					refreshToken = cookie.getValue();
				} else if("accessToken".equals(cookie.getName())){
					accessToken = cookie.getValue();
				} 
			}		
		}
		
		if(bearer != null && !"".equals(bearer)) {
			final String token = request.getHeader(HEADER_AUTH).split(" ")[1];
			try {
				if (token != null && !"".equals(bearer) && jwtService.isUsable(token)) {
					return true;
				}
			}catch(ExpiredJwtException e) {
				try {
					if(refreshToken != null && jwtService.isUsable(refreshToken)) {
						response.sendError(444, "기존 토큰이 만료되었습니다. 해당 토큰을 가지고 새 토큰을 발급받습니다..");	
						return false;
					}
				}catch(Exception reLogin) {
					System.out.println(reLogin.getMessage());
				}
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		Cookie accessCookie = new Cookie("accessToken", null);
		accessCookie.setMaxAge(0);
		accessCookie.setPath("/");
		response.addCookie(accessCookie);
		
		Cookie refreshCookie = new Cookie("refreshToken", null);
		refreshCookie.setMaxAge(0);
		refreshCookie.setPath("/");
		response.addCookie(refreshCookie);
		response.sendError(445, "모든 토큰이 만료되었습니다. 다시 로그인해주세요.");
		
		return false;
	}
}