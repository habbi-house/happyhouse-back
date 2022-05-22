package com.ssafy.web.service;

import java.util.Map;

public interface JwtService {
    <T> String create(String key, T data, String subject);
    Map<String, Object> get(String key);
    long getMemberId();
    long getMemberNo();
    boolean isUsable(String jwt);
    
}