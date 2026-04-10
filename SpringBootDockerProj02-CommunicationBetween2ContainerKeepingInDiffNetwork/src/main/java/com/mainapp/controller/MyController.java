package com.mainapp.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mainapp.dto.StudentDto;
import com.mainapp.entity.StudentEntity;
import com.mainapp.service.MyService;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class MyController {
		
	@Autowired
	private MyService myService;
	
	@GetMapping(path= {"welcome","home"})
	public String welcome(){
		return "index";
	}
	
	@GetMapping("/registration")
	public String registration() {
		return "registration";
	}
	
	@PostMapping("/registration")
	public String registration(@ModelAttribute StudentDto studentDto,Model model,RedirectAttributes redirectAttributes,HttpServletRequest request) throws IllegalStateException, IOException {
		//check file null or not
		if(studentDto.getMultipartFile()==null || studentDto.getMultipartFile().isEmpty()) {
			model.addAttribute("notupload","file must be upload");
			return "registration";
		}
		//check file 1mb limit exceed
		MultipartFile multipartFile = studentDto.getMultipartFile();
		long size = multipartFile.getSize();
		if(size>(1*1024*1024)) {
			model.addAttribute("fileError","file size must not exceed 1mb");
			return "registration";
		}
		//get path
		String realPath = request.getServletContext().getRealPath("images/");
//		String originalFilename = UUID.randomUUID().toString()+LocalDateTime.now().toString().replace(":","a")+studentDto.getMultipartFile().getOriginalFilename();
//		File file = Paths.get(realPath,originalFilename).toFile();
//		studentDto.getMultipartFile().transferTo(file);
		
		System.out.println("**********************************");
		
		StudentEntity save = myService.save(realPath,multipartFile,studentDto);
		System.out.println(save);
		
		redirectAttributes.addFlashAttribute("msg","Data Inserted");
		return "redirect:/home";
	}
	
	
	@GetMapping("/view")
	public String view(Model model) {
		
		List<StudentEntity> list = myService.findall();
		System.out.println(list);
		model.addAttribute("list",list);
		return "view";
	}
	
	@GetMapping("/deleteById")
	public String deleteById(@RequestParam int id,@RequestParam String filename,RedirectAttributes redirectAttributes,HttpServletRequest request) {
		
		String realPath = request.getServletContext().getRealPath("images/");
		
		myService.deleteById(id,filename,realPath);
		System.out.println("id="+id+", deleted succesfully");
		redirectAttributes.addFlashAttribute("msgDelete","deleted succesfully");
		return "redirect:/view";
	}
	
	@GetMapping("/updateById")
	public String updateById(@RequestParam int id,Model model) {
		
		Optional<StudentEntity> byId = myService.findById(id);
		StudentEntity studentEntity=byId.get();
		model.addAttribute("e",studentEntity);
		return "update";
	}
	
	@PostMapping("/updateById")
	public String updateById(@RequestParam int id,@RequestParam String oldFileName,@ModelAttribute StudentDto studentDto,Model model,HttpServletRequest request,RedirectAttributes redirectAttributes) throws IllegalStateException, IOException {
		
		String realPath = request.getServletContext().getRealPath("images/");
		MultipartFile multipartFile = studentDto.getMultipartFile();
		
		StudentEntity save = myService.save(realPath, multipartFile, studentDto,id,oldFileName);
//		System.out.println(save);
		
		redirectAttributes.addFlashAttribute("msgUpdate","Data Updated");
		return "redirect:/view";
	}
	
	@GetMapping("/download")
	public void download(@RequestParam String filename,HttpServletRequest request,HttpServletResponse response) throws IOException {

	    String realPath = request.getServletContext().getRealPath("images/");
	    Path path = Paths.get(realPath, filename);

	    if (Files.exists(path)) {

	        response.setContentType("application/pdf");
	        response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");

	        ServletOutputStream outputStream = response.getOutputStream();
	        Files.copy(path, outputStream);
	        outputStream.flush();
	        outputStream.close();

	        System.out.println("Download Success");

	    } else {
	        System.out.println("File Not Found");
	    }
	}
	
}
