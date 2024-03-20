package com.dcs;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DeveloperCommunityServiceApplication {

	public static void main(String[] args) {
		//method to start spring boot application
		SpringApplication.run(DeveloperCommunityServiceApplication.class, args);
		System.out.println("Successfull");
	}
    //Bean declaration for ModelMapper
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();

	}

}
