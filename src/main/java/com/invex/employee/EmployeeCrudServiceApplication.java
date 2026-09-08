package com.invex.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@ComponentScan("com.invex")
@EnableWebMvc
@SpringBootApplication
public class EmployeeCrudServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeCrudServiceApplication.class, args);
	}

}
