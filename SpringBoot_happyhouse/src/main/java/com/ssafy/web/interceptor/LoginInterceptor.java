package com.ssafy.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.ssafy.web.vo.UserVO;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession(false);
		
		if(session != null) {
			UserVO user = (UserVO) session.getAttribute("user");
			if(user != null) {
				return true;
			}
		}
		
		response.sendRedirect("/user/login");
		return false;
	}

}