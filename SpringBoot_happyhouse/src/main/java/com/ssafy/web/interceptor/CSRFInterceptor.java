package com.ssafy.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.support.RequestContextUtils;

public class CSRFInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean isPost = request.getMethod().equals("POST");
		if(isPost) {
			HttpSession session = request.getSession(false);
			if(session != null) {
				String csrf_token = (String)request.getParameter("csrf_token");
				String CSRF_TOKEN=(String)session.getAttribute("CSRF_TOKEN");
				if(CSRF_TOKEN!=null && CSRF_TOKEN.equals(csrf_token)) {
					return true;
				}
			}
			
			System.out.println("CSRF 공격 감지!");
			FlashMap flashMap = new FlashMap();
			flashMap.put("ok", false);
			flashMap.put("msg", "CSRF 공격 주의!!");
			FlashMapManager flashMapManager = RequestContextUtils.getFlashMapManager(request);
			flashMapManager.saveOutputFlashMap(flashMap, request, response);
			response.sendRedirect("/");
			return false;
		} else {
			return true;
		}
	}

}
