package com.example.SpringBootAWSS3Demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
@EnableWebMvc
@ComponentScan(basePackages="com.example")

public class SpringBootAwss3DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAwss3DemoApplication.class, args);
	}

}
