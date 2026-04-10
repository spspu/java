package com.mainapp.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class StudentDto {
	
	private MultipartFile multipartFile;
	private String title;
	private String description;
}
