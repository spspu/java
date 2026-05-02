package com.mainapp.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.mainapp.model.Employee;

import reactor.core.publisher.Flux;

@Repository
public interface EmployeeRepository extends ReactiveCrudRepository<Employee,String>{
	
	Flux<Employee> findBySalaryGreaterThan(double salary);
	
}
