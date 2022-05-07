package com.ssafy.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.ssafy.web.vo.UserVO;

public class AuthenticationInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession(false);

		if (session != null) {
			UserVO user = (UserVO) session.getAttribute("user");
			if (user != null) {
				return true;
			}
		}

		FlashMap flashMap = new FlashMap();
		flashMap.put("ok", false);
		flashMap.put("msg", "접근 권한이 없습니다.");
		FlashMapManager flashMapManager = RequestContextUtils.getFlashMapManager(request);
		flashMapManager.saveOutputFlashMap(flashMap, request, response);

		response.sendRedirect("/user/login");
		return false;
	}

}