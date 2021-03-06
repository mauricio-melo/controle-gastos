package com.santander.controlegastos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ControleGastosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControleGastosApplication.class, args);
	}

}

