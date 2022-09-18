package com.ra.nontonfilm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class NontonFilmApplication {

	public static void main(String[] args) {
		SpringApplication.run(NontonFilmApplication.class, args);
	}
}
