package com.ssafy.web.service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class AwsS3Service {

	@Value("${cloud.aws.s3.bucket}")
	private String bucketName;

	@Autowired
	private AmazonS3Client amazonS3Client;

	public String uploadFile(MultipartFile file) {
		String newFileName = createFileName(file.getOriginalFilename());

		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentType(file.getContentType());
		metadata.setContentLength(file.getSize());

		try {
			// 지정된 Amazon S3 버킷에 metadata와 함께 생성한 request 객체를 업로드
			PutObjectRequest request = new PutObjectRequest(bucketName, newFileName, file.getInputStream(), metadata);
//			request.withCannedAcl(CannedAccessControlList.AuthenticatedRead); // 접근 권한 체크
			amazonS3Client.putObject(request);
			;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return amazonS3Client.getUrl(bucketName, newFileName).toString();
	}

	public void deleteFile(String fileName) {
		amazonS3Client.deleteObject(bucketName, fileName);
	}

	public String createFileName(String fileName) {
		return UUID.randomUUID().toString().concat("_" + fileName); // UUID + 확장자명
	}
}
