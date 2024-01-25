package com.example.javaprojectspring_boot;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Tag(name = "Chat-Application Api")
public class JavaProjectSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaProjectSpringBootApplication.class, args);
	}

}
