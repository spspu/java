package com.mainapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mainapp.service.FluxService;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest
class SpringReactiveApplicationTests {
	
	@Autowired
	private FluxService fluxService;
	
	@Test
	public void test() {
		
		Flux<String> mapFlux = fluxService.mapFlux();
		StepVerifier.create(mapFlux).expectNext("Ankit","Mukesh").verifyComplete();
		
		Flux<String> filterFlux = fluxService.filterFlux();
		StepVerifier.create(filterFlux).expectNextCount(3).verifyComplete();
		
		Flux<String> flatMapFlux = fluxService.flatMapFlux();
		StepVerifier.create(flatMapFlux).expectNextCount(4).verifyComplete();
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
