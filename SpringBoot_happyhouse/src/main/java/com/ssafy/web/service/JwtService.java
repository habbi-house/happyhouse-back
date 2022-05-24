package com.ssafy.web.service;

import java.util.Map;

import com.ssafy.web.vo.UserVO;

public interface JwtService {
    <T> String create(String key, T data, String subject);
    String createRefreshToken();
    Map<String, Object> get(String key);
    long getMemberNo();
    String getMemberEmail();
    boolean isUsable(String jwt);
	Map<String, Object> getMember(String accessToken);
    
}