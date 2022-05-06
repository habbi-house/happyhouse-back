package com.ssafy.web.exception;

public class PostNotFoundException extends RuntimeException {

	public PostNotFoundException() {
	
	}
	
	public PostNotFoundException(String msg) {
		super(msg);
	}
}
