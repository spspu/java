package com.mainapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuple3;

@SpringBootTest
class SpringReactiveApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	public void test() {
		
		//combining Mono with zip
		Mono<String> m1 = Mono.just("Learn coding");
		Mono<String> m2 = Mono.just("Subscrime Me");
		Mono<Integer> m3 = Mono.just(21345);
		
		Mono<Tuple3<String,String,Integer>> zip = Mono.zip(m1,m2,m3);
		zip.subscribe(data->{
			System.out.println(data.getT1());
			System.out.println(data.getT2());
			System.out.println(data.getT3());
		});
		
		
		//compairing 2 mono
		Mono<Tuple2<String,String>> zipWith = m1.zipWith(m2);
		zipWith.subscribe(data->{
			System.out.println(data.getT1());
			System.out.println(data.getT2());
		});
		
		
		
		
		
		
		
	}

}
