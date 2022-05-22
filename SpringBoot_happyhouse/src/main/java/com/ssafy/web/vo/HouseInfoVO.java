package com.ssafy.web.vo;

import java.math.BigInteger;

import lombok.Data;

@Data
public class HouseInfoVO {
	private BigInteger aptCode;
	private int buildYear;
	
	private String roadName;
	private String roadNameBonbun;
	private String roadNameBubun;
	private String roadNameSeq;
	private String roadNameBasementCode;
	private String roadNameCode;
	
	private String dong;
	private String bonbun;
	private String bubun;
	private String sigunguCode;
	private String eubmyundongCode;
	private String dongCode;
	private String landCode;
	private String apartmentName;
	private String jibun;
	private String lng;
	private String lat;
}
