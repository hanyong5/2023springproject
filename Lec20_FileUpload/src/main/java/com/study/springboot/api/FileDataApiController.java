package com.study.springboot.api;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.study.springboot.service.FileDataService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FileDataApiController {
	
	private final FileDataService fileDataService;
	
	@PostMapping("/file")
	@CrossOrigin
	public ResponseEntity<?> upLoadImage(@RequestParam("image") MultipartFile file) throws IOException
	{
		String uploadImage= fileDataService.uploadImageSystem(file);
		return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
	}
}
