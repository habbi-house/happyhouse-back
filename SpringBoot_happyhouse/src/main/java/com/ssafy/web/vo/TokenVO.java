package com.ssafy.web.vo;


import lombok.Data;

@Data
public class TokenVO {

	private int no;
	private String email;
	private String refreshToken;
}
