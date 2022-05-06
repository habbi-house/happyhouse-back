package com.ssafy.web.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.ssafy.web.vo.PostVO;
import com.ssafy.web.vo.UserVO;

public class AuthorizationInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession(false);
		if(session != null) {
			UserVO user = (UserVO)session.getAttribute("user");
			if(user != null) {
				Map pathVariables = (Map)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
				if(pathVariables.get("id").equals(user.getNo())) {
					return true;
				}

			}
			
		}

		FlashMap flashMap = new FlashMap();
		flashMap.put("ok", false);
		flashMap.put("msg", "접근 권한이 없습니다.");
		FlashMapManager flashMapManager = RequestContextUtils.getFlashMapManager(request);
		flashMapManager.saveOutputFlashMap(flashMap, request, response);
		
		response.sendRedirect("/");
		return false;
	}

}
