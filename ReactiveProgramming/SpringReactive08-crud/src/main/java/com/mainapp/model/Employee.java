package com.mainapp.model;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document
@Getter
@Setter
public class Employee {
	
	private String id;
	private String name;
	private String role;
	private double salary;
	
	private LocalDateTime createdAt;
	private String createdBy;
	private boolean active=true;
	private LocalDateTime updatedAt;
	private String updatedBy;

}
