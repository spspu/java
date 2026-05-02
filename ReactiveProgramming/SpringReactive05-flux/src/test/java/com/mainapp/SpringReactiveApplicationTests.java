package com.mainapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mainapp.service.FluxService;

@SpringBootTest
class SpringReactiveApplicationTests {
	
	@Autowired
	private FluxService fluxService;
	
	@Test
	public void test() throws InterruptedException {
		
		fluxService.testService();
		
		fluxService.getFlux().subscribe(System.out::println);
		
		fluxService.fruitsFlux().subscribe(System.out::println);
		
		
		
		
	}

}
