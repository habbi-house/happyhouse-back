package com.ssafy.web.aop;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ssafy.web.exception.UnauthorizedException;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<String> UnauthorizedException(UnauthorizedException e) {

        return new ResponseEntity<String>("계정 권한이 유효하지 않습니다.", HttpStatus.I_AM_A_TEAPOT);
    }
    
}