package com.mainapp.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class MyController {

	@GetMapping("/mono")
    public Mono<List<String>> testMono() {
        return Mono.just(Arrays.asList("hello"));
    }

    @GetMapping("/flux")
    public Mono<List<String>> testFlux() {
        List<String> fruits = Arrays.asList("apple", "mango", "papaya");
        return Mono.just(fruits);
    }
}