package com.mainapp.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mainapp.dto.EmployeeRequest;
import com.mainapp.dto.EmployeeResponse;
import com.mainapp.model.Employee;
import com.mainapp.repository.EmployeeRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository empRepo;
	
	public Mono<EmployeeResponse> createEmployee(EmployeeRequest request,String user){
		Employee emp = new Employee();
		emp.setName(request.getName());
		emp.setRole(request.getRole());
		emp.setSalary(request.getSalary());
		emp.setCreatedBy(user);
		emp.setCreatedAt(LocalDateTime.now());
		return empRepo.save(emp).map(this::toResponse);
	}
	
	public Mono<EmployeeResponse> getById(String id){
		return empRepo.findById(id)
				.filter(Employee::isActive)
				.switchIfEmpty(Mono.error(new RuntimeException("Employee Not Found"+id)))
				.map(this::toResponse);
	}
	
	public Flux<EmployeeResponse> getAllData(Optional<Double> salaryFilter){
		Flux<Employee> empData = salaryFilter.map(empRepo::findBySalaryGreaterThan).orElse(empRepo.findAll());
		return empData.filter(Employee::isActive).map(this::toResponse);
		
	}
	
	public Mono<EmployeeResponse> updateEmployee(String id,EmployeeRequest request,String user){
		return empRepo.findById(id).filter(Employee::isActive).switchIfEmpty(Mono.error(new RuntimeException("Employee Not Found!"+id)))
				.flatMap(existingEmp->{
					existingEmp.setName(request.getName());
					existingEmp.setRole(request.getRole());
					existingEmp.setSalary(request.getSalary());
					existingEmp.setUpdatedAt(LocalDateTime.now());
					existingEmp.setUpdatedBy(user);
					return empRepo.save(existingEmp);
				})
				.map(this::toResponse);
	}
	
	public Mono<Void> deleteData(String id,String user){
		return empRepo.findById(id).filter(Employee::isActive)
				.switchIfEmpty(Mono.error(new RuntimeException("Employee Not Found"+id)))
				.flatMap(emp->{
					emp.setActive(false);
					emp.setUpdatedBy(user);
					emp.setUpdatedAt(LocalDateTime.now());
					return empRepo.save(emp);
				})
				.then();
	}
	
	
	private EmployeeResponse toResponse(Employee emp) {
		EmployeeResponse empRes=new EmployeeResponse();
		empRes.setId(emp.getId());
		empRes.setName(emp.getName());
		empRes.setRole(emp.getRole());
		empRes.setSalary(emp.getSalary());
		empRes.setCreatedBy(emp.getCreatedBy());
		empRes.setStatus(emp.isActive()?"ACTIVE":"INACTIVE");
		return empRes;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
