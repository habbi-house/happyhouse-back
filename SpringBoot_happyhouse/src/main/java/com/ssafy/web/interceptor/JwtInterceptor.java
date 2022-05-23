package com.ssafy.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ssafy.web.exception.UnauthorizedException;
import com.ssafy.web.service.JwtService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtInterceptor implements HandlerInterceptor {
	private static final String HEADER_AUTH = "Authorization";

	@Autowired
	private JwtService jwtService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// axios의 Preflight OPTION요청을 거르기 위함
		if (HttpMethod.OPTIONS.matches(request.getMethod())) {
			return true;
		}

		final String token = request.getHeader(HEADER_AUTH).split(" ")[1];

		try {
			if (token != null && jwtService.isUsable(token)) {
				return true;
			} else {
				throw new UnauthorizedException();
			}
		} catch(ExpiredJwtException e) {
			response.sendError(444, "기존 토큰이 만료되었습니다. 해당 토큰을 가지고 get-newtoken링크로 이동해주세요.");
		} catch(Exception e) {
			response.sendError(445, "모든 토큰이 만료되었습니다. 다시 로그인해주세요.");
		}
		
		return false;
	}
}