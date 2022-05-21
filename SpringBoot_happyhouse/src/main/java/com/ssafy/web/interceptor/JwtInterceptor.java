package com.ssafy.web.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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
    	Enumeration e = request.getHeaderNames();
    	while(e.hasMoreElements()) {
    		String rName = (String)e.nextElement();
    		String rVal = request.getHeader(rName);
    		System.out.println(rName + "==" + rVal);
    	}
    	System.out.println(request.getHeader("Authorization"));
        final String token = request.getHeader(HEADER_AUTH).split(" ")[1];

        if(token != null && jwtService.isUsable(token)){
            return true;
        } else {
            throw new UnauthorizedException();
        }
    }
}