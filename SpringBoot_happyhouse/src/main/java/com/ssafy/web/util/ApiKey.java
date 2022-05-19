package com.ssafy.web.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApiKey {
	@Value("${kakao-key}")
	private String key;

    public String getKey() {
    	return this.key;
    }
}
