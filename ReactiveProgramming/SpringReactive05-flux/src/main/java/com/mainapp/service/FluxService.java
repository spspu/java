package com.mainapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;

@Service
public class FluxService {
	
	public void testService() {
		System.out.println("flux testing service ");
	}

	public Flux<String> getFlux() {
		return Flux.just("Ankit","Durgesh","Ravi","Gautam");
	}
	
	public Flux<String> fruitsFlux(){
		List<String> fruitsName = List.of("Mango","Apple");
		return Flux.fromIterable(fruitsName);
	}
	
	
	
	
	
	
	
	

}
