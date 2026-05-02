package com.mainapp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mainapp.dto.EmployeeRequest;
import com.mainapp.dto.EmployeeResponse;
import com.mainapp.service.EmployeeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	@Autowired
	private EmployeeService empSer;
	
	@PostMapping("/createEmployeeObj")
	public Mono<EmployeeResponse> createEmplyoeeObj(@RequestBody EmployeeRequest request,@RequestHeader("X-User") String user){
		return empSer.createEmployee(request, user);
	}
	
	@GetMapping("/getEmployeeById/{id}")
	public Mono<EmployeeResponse> getEmployeeById(@PathVariable("id") String id){
		return empSer.getById(id);
	}
	
	@GetMapping("/getAllEmployee")
	public Flux<EmployeeResponse> getAllEmployee(Optional<Double> salaryFilter){
		return empSer.getAllData(salaryFilter);
	}
	
	@PutMapping("/update/{id}")
	public Mono<EmployeeResponse> update(@PathVariable("id") String id,@RequestBody EmployeeRequest request,@RequestHeader("X-User") String user){
		return empSer.updateEmployee(id, request, user);
	}
	
	@DeleteMapping("/deleteDataOfEmp/{id}")
	public Mono<Void> deleteDataOfEmp(@PathVariable("id") String id,@RequestHeader("X-User") String user){
		return empSer.deleteData(id, user);
	}
	
	
	
	
	
	
	
	
	
}