package com.SEB_Pre_020.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Seb40PreProject020Application {

	public static void main(String[] args) {
		SpringApplication.run(Seb40PreProject020Application.class, args);
	}

}
