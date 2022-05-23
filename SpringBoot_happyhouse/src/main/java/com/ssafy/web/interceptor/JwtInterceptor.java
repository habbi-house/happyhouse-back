package com.ssafy.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ssafy.web.exception.UnauthorizedException;
import com.ssafy.web.service.JwtService;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    private static final String HEADER_AUTH = "Authorization";

    @Autowired
    private JwtService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
//    	Enumeration e = request.getHeaderNames();
//    	while(e.hasMoreElements()) {
//    		String rName = (String)e.nextElement();
//    		String rVal = request.getHeader(rName);
//    		System.out.println(rName + "==" + rVal);
//    	}
    	// axios의 Preflight OPTION요청을 거르기 위함
    	if (HttpMethod.OPTIONS.matches(request.getMethod())) {
            return true;
        }
    	
    	System.out.println(request.getHeader("HEADER_AUTH"));
        final String token = request.getHeader(HEADER_AUTH).split(" ")[1];

        if(token != null && jwtService.isUsable(token)){
            return true;
        } else {
            throw new UnauthorizedException();
        }
    }
}