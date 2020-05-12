package com.meritamerica.assignment6.springapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "com.meritamerica.assignment6.repos")
public class Assignment6Application {

	public static void main(String[] args) {
		SpringApplication.run(Assignment6Application.class, args);
	}

}
