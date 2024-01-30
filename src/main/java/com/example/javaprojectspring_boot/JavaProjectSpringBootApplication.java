package com.example.javaprojectspring_boot;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		servers = @Server(
				url = "http://213.230.64.93:8181"
		)
)
@Tag(name = "Chat-Application Api")
public class JavaProjectSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaProjectSpringBootApplication.class, args);
	}

}
