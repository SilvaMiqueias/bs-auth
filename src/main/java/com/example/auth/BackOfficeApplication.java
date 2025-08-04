package com.example.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BackOfficeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackOfficeApplication.class, args);
	}

}
