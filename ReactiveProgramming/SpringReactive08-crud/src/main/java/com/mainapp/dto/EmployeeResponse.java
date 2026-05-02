package com.mainapp.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeResponse {
	
	private String id;
	private String name;
	private String role;
	private double salary;
	private String status;
	private String createdBy;
	

}
