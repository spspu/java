package com.mainapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mainapp.entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity,Integer> {

}
