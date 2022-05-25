package com.ssafy.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.web.service.AwsS3Service;

@RestController
@RequestMapping("/s3")
public class AwsS3Controller {
	
	@Autowired
	private AwsS3Service awsS3Service;

	@PostMapping("/upload")
	public ResponseEntity<String> uploadImage(@RequestParam(value = "file") MultipartFile multipartFile) {
		try {
			String imgUrl = awsS3Service.uploadFile(multipartFile);
			return new ResponseEntity<String>(imgUrl, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping("/delete")
	public ResponseEntity<String> deleteImage(String imgUrl) {
		System.out.println(imgUrl);
		String fileName = imgUrl.substring(imgUrl.lastIndexOf("/") + 1);
		System.out.println(fileName);
		awsS3Service.deleteFile(fileName);			
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
