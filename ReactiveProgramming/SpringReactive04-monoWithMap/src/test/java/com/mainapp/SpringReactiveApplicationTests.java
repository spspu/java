package com.mainapp;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuple3;

@SpringBootTest
class SpringReactiveApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	public void test() throws InterruptedException {
		
		//map(), flatMap() & flatMapMany()
		Mono<String> m1 = Mono.just("Learn coding");
		Mono<String> m2 = Mono.just("Subscrime Me");
		Mono<Integer> m3 = Mono.just(21345);
		
		//map() convert data in uppper case
		Mono<String> resultMapMono = m1.map(String::toUpperCase);
		resultMapMono.subscribe(System.out::println);
		
		Mono<String[]> flatMap = m1.flatMap(valueM1->Mono.just(valueM1.split("")));
		flatMap.subscribe(data->{
			for(String s:data) {
				System.out.println(s);
			}
		});
		
		System.out.println("==============");
		
		Flux<String> flatMapMany = m1.flatMapMany(valueM1->Flux.just(valueM1.split("")));
		flatMapMany.subscribe(data->{
			System.out.println(data);
		});
		
		
		
		System.out.println(Thread.currentThread().getName());
		//mono concat
		Flux<String> concatWith = m1.concatWith(m2)
				.log()
				.delayElements(Duration.ofMillis(2000));
		concatWith.subscribe(System.out::println);
		
		Thread.sleep(3000);
		System.out.println("terminated main thread");
		
		
		
		
		
		
		
		
		
	}

}
