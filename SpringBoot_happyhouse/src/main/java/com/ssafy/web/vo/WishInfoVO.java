package com.ssafy.web.vo;

import java.math.BigInteger;

import lombok.Data;

@Data
public class WishInfoVO {
	private BigInteger aptCode;
	private String apartmentName;
	
	private String dongCode;
	private String address;
	private String src;
	
}