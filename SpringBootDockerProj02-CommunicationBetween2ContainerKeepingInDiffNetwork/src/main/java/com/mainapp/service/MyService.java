package com.mainapp.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mainapp.dto.StudentDto;
import com.mainapp.entity.StudentEntity;
import com.mainapp.repository.StudentRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional(rollbackOn=Exception.class)
public class MyService {
			
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private StudentRepository studentRepository;

	public StudentEntity save(String realPath, MultipartFile multipartFile,StudentDto studentDto) throws IllegalStateException, IOException {
		
		//db logic
		String filename = UUID.randomUUID().toString()+LocalDateTime.now().toString().replace(":","a")+multipartFile.getOriginalFilename();
		
		StudentEntity map = modelMapper.map(studentDto, StudentEntity.class);
		map.setId(0);
		map.setFilename(filename);
		map.setDatatime(LocalDateTime.now().toString());
		
		StudentEntity save = studentRepository.save(map);
		
		//file logic
		File file = Paths.get(realPath,filename).toFile();
		multipartFile.transferTo(file);
		
		return save;
	}

	public List<StudentEntity> findall() {
		List<StudentEntity> list = studentRepository.findAll();
		return list;
	}
	
	public void deleteById(int id,String filename,String realPath) {
		//delete from db
		studentRepository.deleteById(id);
		
		//delete from folder
		File file = new File(realPath+File.separator+filename);
		file.delete();
		
	}
	
	public Optional<StudentEntity> findById(int id) {
		return studentRepository.findById(id);
	}

	public StudentEntity save(String realPath, MultipartFile multipartFile, StudentDto studentDto, int id, String oldFileName) throws IllegalStateException, IOException {
		
		if(multipartFile!=null && !multipartFile.isEmpty()) {
			//new file
			String filename = UUID.randomUUID().toString()+LocalDateTime.now().toString().replace(":","a")+multipartFile.getOriginalFilename();
			
			StudentEntity map = modelMapper.map(studentDto, StudentEntity.class);
			map.setId(id);
			map.setFilename(filename);
			map.setDatatime(LocalDateTime.now().toString());
			StudentEntity save = studentRepository.save(map);
			
			//old deleted
			new File(realPath+File.separator+oldFileName).delete();
			
			//new uploaded
			File file = Paths.get(realPath,filename).toFile();
			multipartFile.transferTo(file);
			System.out.println("************** IF ***************");
			return save;
		}else {
			//old file
			StudentEntity map = modelMapper.map(studentDto, StudentEntity.class);
			map.setId(id);
			map.setFilename(oldFileName);
			map.setDatatime(LocalDateTime.now().toString());
			StudentEntity save = studentRepository.save(map);
			System.out.println("*********** ELSE ***********");
			return save;
		}
		
		
		
		
		
		
	}
	
	
	
	
	
	

}
