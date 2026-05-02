package com.mainapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import reactor.core.publisher.Mono;

@SpringBootTest
class SpringReactiveApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	public void test() {
		
		Mono<String> errorMono = Mono.error(new RuntimeException("Error !!"));
		
		//create mono
		Mono<String> m = Mono
				.just("testing")
				.log()
				.then(errorMono);
		//consumer mono
		m.subscribe(System.out::println);
	}

}
