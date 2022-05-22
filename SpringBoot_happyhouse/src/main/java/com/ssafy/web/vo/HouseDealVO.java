package com.ssafy.web.vo;

import java.math.BigInteger;

import lombok.Data;

@Data
public class HouseDealVO {
	private int no;
	
	private String dealAmount;
	private int dealYear;
	private int dealMonth;
	private int dealDay;
	
	private String area;
	private String floor;
	private String cancelDealType;
	
	private BigInteger aptCode;
}
