package com.ssafy.web.aop;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.ssafy.web.exception.PostNotFoundException;

@Controller
public class ExceptionAdvice {

	@ExceptionHandler({ NoHandlerFoundException.class, PostNotFoundException.class })
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handlePageNotFound(NoHandlerFoundException e) {
		return "error";
	}
}
