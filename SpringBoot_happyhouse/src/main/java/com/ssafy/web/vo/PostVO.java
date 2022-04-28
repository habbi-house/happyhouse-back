package com.ssafy.web.vo;

import lombok.Data;

@Data
public class PostVO {
	/** 글 번호 **/
    private int code;
    
    /** 글 제목 **/
    private String title;
    
    /** 글 내용 **/
    private String content;
    
    /** 작성자 **/
    private String writer;
    
    /** 등록시간 **/
    private String reg_datetime;
    
    /** 조회수 **/
    private int hits;
    
    /*
     * 계층형 게시판을 위한 추가 필드
     * originNo, groupOrd, groupLayer 
     */
    
    /** 원글 번호 **/
    private int originNo;
    
    /** 원글(답글포함)에 대한 순서 **/
    private int groupOrd;
 
    /** 답글 계층 **/
    private int groupLayer;
}
