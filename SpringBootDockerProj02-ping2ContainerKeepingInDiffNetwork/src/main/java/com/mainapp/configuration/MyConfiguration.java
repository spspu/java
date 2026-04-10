package com.mainapp.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages= {"com.mainapp"})
public class MyConfiguration {
	
	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
